package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.DangKiCTXH;
import Models.XacNhanGXN;
import Util.HandleExeption;
import Util.JDBCUtil;

public class DangKiCTXHDAO {
	public DangKiCTXHDAO() {
			
	}
	
	private static final String Select_All_Request_HDCTXH = "SELECT d.ID, d.ThoiGianDki, d.MSSV, c.TenHD, c.Diem " +
            "FROM dki_tham_gia_ctxh d " +
            "LEFT JOIN ctxh c ON d.MaHD = c.MaHD " +
            "WHERE d.TrangThai = 'Chưa duyệt'";
	
	private static final String Select_Request_DKICTXH_ID = "SELECT d.ID, d.ThoiGianDki, d.MSSV, c.TenHD, c.Diem " +
            "FROM dki_tham_gia_ctxh d " +
            "LEFT JOIN ctxh c ON d.MaHD = c.MaHD " +
            "WHERE d.ID = ?";
	
	private static final String Update_Request_DKICTXH_ID = "UPDATE dki_tham_gia_ctxh SET "
			+ "ThoiGianPhanHoi = ?, TieuDe = ?, MaCTSV = ?, "
			+ "NdPhanHoi = ?, TrangThai = ? WHERE id = ?;";
	
	private static final String Confirm_CTXH_ID = "UPDATE dki_tham_gia_ctxh SET "
			+ "MaCTSV = ?, TrangThai = ? WHERE id = ?;";
	
	private static final String Select_All_UnConfirmed_HDCTXH = "SELECT d.ID, d.ThoiGianDki, d.MSSV, c.TenHD, c.Diem " +
            "FROM dki_tham_gia_ctxh d " +
            "LEFT JOIN ctxh c ON d.MaHD = c.MaHD " +
            "WHERE d.TrangThai = 'Đã duyệt'";
	
	public List<DangKiCTXH> SelectAllRequestsHDCTXH() {
		List<DangKiCTXH> dkictxhs = new ArrayList < > ();
		
        try (Connection connection = JDBCUtil.getConnection();      	
            PreparedStatement preparedStatement = connection.prepareStatement(Select_All_Request_HDCTXH);) {
            System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	String ID = rs.getString("ID");
            	Date thoigiandangki = rs.getDate("ThoiGianDki");
            	String mssv = rs.getString("MSSV");
                String tenHoatDong = rs.getString("TenHD");
                String diem = rs.getString("diem");
                String trangThai = "Chưa Duyệt";
                dkictxhs.add(new DangKiCTXH(ID, tenHoatDong, diem, mssv, thoigiandangki, trangThai));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return dkictxhs;
    }
	
	public List<DangKiCTXH> SelectAllUnConfirmedHDCTXH() {
		List<DangKiCTXH> dkictxhs = new ArrayList < > ();
		
        try (Connection connection = JDBCUtil.getConnection();      	
            PreparedStatement preparedStatement = connection.prepareStatement(Select_All_UnConfirmed_HDCTXH);) {
            System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	String ID = rs.getString("ID");
            	Date thoigiandangki = rs.getDate("ThoiGianDki");
            	String mssv = rs.getString("MSSV");
                String tenHoatDong = rs.getString("TenHD");
                String diem = rs.getString("diem");
                String trangThai = "Đang chờ xác nhận";
                dkictxhs.add(new DangKiCTXH(ID, tenHoatDong, diem, mssv, thoigiandangki, trangThai));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return dkictxhs;
    }
	
	public DangKiCTXH SelectDKICTXHRequestID(String id) {
		DangKiCTXH dkictxh = null;
		try (Connection connection = JDBCUtil.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(Select_Request_DKICTXH_ID);) {
	            preparedStatement.setString(1, id);
	            System.out.println(preparedStatement);
	            
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {	
	            	dkictxh = new DangKiCTXH();
	            	dkictxh.setID(id);
	            	dkictxh.setThoiGianDangKi(rs.getDate("ThoiGianDKi"));
	            	dkictxh.setMssv(rs.getString("Mssv"));
	            	dkictxh.setTenHoatDong(rs.getString("TenHD"));
	            	dkictxh.setDiem(rs.getString("diem"));
	            	dkictxh.setTrangThai("Chưa Duyệt");
	            }
	        } catch (SQLException exception) {
	            HandleExeption.printSQLException(exception);
	        }
		return dkictxh;
	}
	
	public boolean UpdateRequestDKICTXH(String id, Date thoiGianPhanHoi, String tieuDe, String MaCTSV, String noiDungPhanHoi) {
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_Request_DKICTXH_ID);) {
            preparedStatement.setDate(1, thoiGianPhanHoi);
            preparedStatement.setString(2, tieuDe);
            preparedStatement.setString(3, MaCTSV);
            preparedStatement.setString(4, noiDungPhanHoi);
            preparedStatement.setString(5, "Đã duyệt");
            preparedStatement.setString(6, id);
     
            System.out.println(preparedStatement);
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated > 0;
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
            return false;
        }
	}
	
	public boolean ConfirmCTXH(String id,String MaCTSV, String trangThai) {
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Confirm_CTXH_ID);) {
            preparedStatement.setString(1, MaCTSV);
            preparedStatement.setString(2, trangThai);
            preparedStatement.setString(3, id);
     
            System.out.println(preparedStatement);
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated > 0;
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
            return false;
        }
	}
}
