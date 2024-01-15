
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import Models.XacNhanNganhNghe;
import Util.HandleExeption;
import Util.JDBCUtil;

public class XacNhanNganhNgheDAO {
	
	public XacNhanNganhNgheDAO() {
		
	}
	
	private static final String Select_All_Request_XNNN = "SELECT d.ID, d.NamHoc, d.HocKy, d.MSSV, d.ThoiGianDki, l.TenLyDo " +
            "FROM dki_xn_nganh_nghe d " +
            "LEFT JOIN LyDo l ON d.MaLyDo = l.MaLyDo " +
            "WHERE d.TrangThai = 'Chưa duyệt'";
	
	private static final String Select_Request_XNNN_ID = "SELECT d.NamHoc, d.HocKy, d.MSSV, d.ThoiGianDki, l.TenLyDo " +
            "FROM dki_xn_nganh_nghe d " +
            "LEFT JOIN LyDo l ON d.MaLyDo = l.MaLyDo " +
            "WHERE d.ID = ?";
	
	private static final String Update_Request_XNNN_ID = "UPDATE dki_xn_nganh_nghe SET "
			+ "ThoiGianPhanHoi = ?, TieuDe = ?, MaCTSV = ?, "
			+ "NdPhanHoi = ?, TrangThai = ? WHERE id = ?;";
	
	public List<XacNhanNganhNghe> SelectAllRequestsXNNN() {
		List<XacNhanNganhNghe> xnnns = new ArrayList < > ();

        try (Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Select_All_Request_XNNN);) {
            System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	int ID = rs.getInt("ID");
                String namHoc = rs.getString("NamHoc");
                String hocKy = rs.getString("HocKy");
                int mssv = rs.getInt("MSSV");
                Date thoigiandangki = rs.getDate("ThoiGianDki");
                String tenLyDo = rs.getString("TenLyDo");
                String trangThai = "Chưa Duyệt";
                xnnns.add(new XacNhanNganhNghe(ID, thoigiandangki, namHoc, hocKy, mssv, tenLyDo, trangThai));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return xnnns;
    }
	
	public XacNhanNganhNghe SelectXNNNRequestID(String id) {
		int ID = Integer.parseInt(id);
		XacNhanNganhNghe xnnn = null;
		try (Connection connection = JDBCUtil.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(Select_Request_XNNN_ID);) {
	            preparedStatement.setString(1, id);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {	
	            	xnnn = new XacNhanNganhNghe();
	            	xnnn.setID(ID);
	            	xnnn.setNamHoc(rs.getString(1));
	            	xnnn.setHocKy(rs.getString(2));
	            	xnnn.setMssv(rs.getInt(3));
	            	xnnn.setThoiGianDangKi(rs.getDate(4));
	            	xnnn.setTenLyDo(rs.getNString(5));;
	            	xnnn.setTrangThai("Chưa Duyệt");
	            }
	        } catch (SQLException exception) {
	            HandleExeption.printSQLException(exception);
	        }
		return xnnn;
	}
	
	public boolean UpdateRequestXNNN(String id, Date thoiGianPhanHoi, String tieuDe, String MaCTSV, String noiDungPhanHoi) {        
		try (Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Update_Request_XNNN_ID);) {
            preparedStatement.setDate(1, thoiGianPhanHoi);
            preparedStatement.setString(2, tieuDe);
            preparedStatement.setString(3, MaCTSV);
            preparedStatement.setString(4, noiDungPhanHoi);
            preparedStatement.setString(5, "Đã duyệt");
            preparedStatement.setString(6, id);
     
            System.out.println(preparedStatement);
            int rowUpdateds = preparedStatement.executeUpdate();
            return rowUpdateds > 0;
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
            return false;
        }
	}
}
