package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.XacNhanGXN;
import Util.HandleExeption;
import Util.JDBCUtil;

public class XacNhanGXNDAO {
	
	public XacNhanGXNDAO() {
		
	}
	
	private static final String Select_All_Request_GXN = "SELECT d.ID, d.ThoiGianDki, d.MSSV, l.TenLoaiGiay, d.SoLuong, d.STT " +
            "FROM dki_giay_xac_nhan d " +
            "LEFT JOIN loai_giay l ON d.MaLoaiGiay = l.MaLoaiGiay " +
            "WHERE d.TrangThai = 'Chưa duyệt'";
	
	private static final String Select_All_Accepted_GXN = "SELECT d.ID, d.ThoiGianDki, d.MSSV, l.TenLoaiGiay, d.SoLuong, d.STT " +
            "FROM dki_giay_xac_nhan d " +
            "LEFT JOIN loai_giay l ON d.MaLoaiGiay = l.MaLoaiGiay " +
            "WHERE d.TrangThai = 'Đã duyệt'";
	
	private static final String Select_All_Request_GXNID = "SELECT d.ID, d.ThoiGianDki, d.MSSV, l.TenLoaiGiay, d.SoLuong, d.STT " +
            "FROM dki_giay_xac_nhan d " +
            "LEFT JOIN loai_giay l ON d.MaLoaiGiay = l.MaLoaiGiay " +
            "WHERE d.TrangThai = 'Chưa duyệt' AND d.MaLoaiGiay = ?";
	
	private static final String Select_Request_GXN_ID = "SELECT d.ThoiGianDKi, d.MSSV, l.TenLoaiGiay, d.SoLuong, d.STT " +
            "FROM dki_giay_xac_nhan d " +
            "LEFT JOIN loai_giay l ON d.MaLoaiGiay = l.MaLoaiGiay " +
            "WHERE d.ID = ?";
	
	private static final String Update_Request_GXN_ID = "UPDATE dki_giay_xac_nhan SET "
			+ "ThoiGianPhanHoi = ?, TieuDe = ?, MaCTSV = ?, "
			+ "NdPhanHoi = ?, TrangThai = ? WHERE id = ?;";
	
	private static final String Update_Confirm_GXN_ID = "UPDATE dki_giay_xac_nhan SET "
			+ "TrangThai = 'Đã nhận' WHERE id = ?;";
	
	public List<XacNhanGXN> SelectAllAcceptedGXN() {
		List<XacNhanGXN> gxns = new ArrayList < > ();
		try (Connection connection = JDBCUtil.getConnection();    	
	            PreparedStatement preparedStatement = connection.prepareStatement(Select_All_Accepted_GXN);) {
	            System.out.println(preparedStatement);
	            
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	            	String ID = rs.getString("ID");
	            	Date thoigiandangki = rs.getDate("ThoiGianDki");
	            	String mssv = rs.getString("MSSV");
	                String tenLoaiGiay = rs.getString("TenLoaiGiay");
	                String soLuong = rs.getString("SoLuong");
	                String stt = rs.getString("STT");
	                String trangThai = "Đã Duyệt";
	                gxns.add(new XacNhanGXN(ID, tenLoaiGiay, mssv, thoigiandangki, soLuong, stt, trangThai));
	            }
	        } catch (SQLException exception) {
	            HandleExeption.printSQLException(exception);
	        }
	        return gxns;
	}
	public List<XacNhanGXN> SelectAllRequestsGXN(String maLoaiGiay) {
		List<XacNhanGXN> gxns = new ArrayList < > ();
		String sql = Select_All_Request_GXNID;
		if (maLoaiGiay == null) {
			sql = Select_All_Request_GXN;	
		}
		if (maLoaiGiay != null) {
			if (maLoaiGiay.equals("xemTatCa")) {
				sql = Select_All_Request_GXN;	
			}
		}
        try (Connection connection = JDBCUtil.getConnection();
        	
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
        	if (sql == Select_All_Request_GXNID) {
        		preparedStatement.setString(1, maLoaiGiay);
        	}
            System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	String ID = rs.getString("ID");
            	Date thoigiandangki = rs.getDate("ThoiGianDki");
            	String mssv = rs.getString("MSSV");
                String tenLoaiGiay = rs.getString("TenLoaiGiay");
                String soLuong = rs.getString("SoLuong");
                String stt = rs.getString("STT");
                String trangThai = "Chưa Duyệt";
                gxns.add(new XacNhanGXN(ID, tenLoaiGiay, mssv, thoigiandangki, soLuong, stt, trangThai));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return gxns;
    }
	
	public XacNhanGXN SelectGXNRequestID(String id) {
		XacNhanGXN gxn = null;
		try (Connection connection = JDBCUtil.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(Select_Request_GXN_ID);) {
	            preparedStatement.setString(1, id);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {	
	            	gxn = new XacNhanGXN();
	            	gxn.setID(id);
	            	gxn.setThoiGianDangKi(rs.getDate("ThoiGianDKi"));
	            	gxn.setMssv(rs.getString("Mssv"));
	            	gxn.setTenLoaiGiay(rs.getString("TenLoaiGiay"));
	            	gxn.setSoLuong(rs.getString("SoLuong"));
	            	gxn.setStt(rs.getString("STT"));
	            	gxn.setTrangThai("Chưa Duyệt");
	            }
	        } catch (SQLException exception) {
	            HandleExeption.printSQLException(exception);
	        }
		return gxn;
	}
	
	public boolean UpdateRequestGXN(String id, Date thoiGianPhanHoi, String tieuDe, String MaCTSV, String noiDungPhanHoi) {
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_Request_GXN_ID);) {
            preparedStatement.setDate(1, thoiGianPhanHoi);
            preparedStatement.setString(2, tieuDe);
            preparedStatement.setString(3, MaCTSV);
            preparedStatement.setString(4, noiDungPhanHoi);
            preparedStatement.setString(5, "Đã duyệt");
            preparedStatement.setString(6, id);    
            System.out.println(preparedStatement);
            
            int rowsAffected = preparedStatement.executeUpdate();           
            return rowsAffected > 0;
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
            return false;
        }
	}
	
	public boolean UpdateConfirmGXN(String id) {
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_Confirm_GXN_ID);) {
            preparedStatement.setString(1, id);    
            System.out.println(preparedStatement);
            
            int rowsAffected = preparedStatement.executeUpdate();           
            return rowsAffected > 0;
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
            return false;
        }
	}
}
