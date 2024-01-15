package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.HoatDongCTXH;
import Util.HandleExeption;
import Util.JDBCUtil;

public class HoatDongCTXHDAO {
	public HoatDongCTXHDAO() {
		
	}
	
	private static final String Select_All_HDCTXH = "SELECT MaHD, TenHD, Diem, NoiDung, TgDienRa, TgKetThuc FROM ctxh ";
	
	private static final String Select_HDCTXH_ID = "SELECT TenHD, Diem, NoiDung, TgDienRa, TgKetThuc FROM ctxh WHERE MaHD = ?";

	private static final String Update_HDCTXH_ID = "UPDATE ctxh SET  TenHD = ?, Diem = ?, NoiDung = ?, TgDienRa = ?, TgKetThuc = ? WHERE (MaHD = ?)";
	
	private static final String Insert_HDCTXH = "INSERT INTO ctxh (TenHD, Diem, NoiDung, TgDienRa, TgKetThuc) VALUES (?, ?, ?, ?, ?)";
	public List<HoatDongCTXH> SelectAllHDCTXH() {
		List <HoatDongCTXH> hdctxhs = new ArrayList < > ();
		
        try (Connection connection = JDBCUtil.getConnection();      	
            PreparedStatement preparedStatement = connection.prepareStatement(Select_All_HDCTXH);) {
            System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            	String maHoatDong = rs.getString("MaHD");
            	String tenHoatDong = rs.getString("TenHD");
            	String diem = rs.getString("Diem");
            	String noiDung = rs.getString("NoiDung");
            	Date thoiGianDienRa = rs.getDate("TgDienRa");
            	Date thoiGianKetThuc = rs.getDate("TgKetThuc");
            	hdctxhs.add(new HoatDongCTXH(maHoatDong, tenHoatDong, diem, noiDung, thoiGianDienRa, thoiGianKetThuc));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return hdctxhs;
    }
	
	public HoatDongCTXH SelectHDCTXH(String maHoatDong) {
		HoatDongCTXH hdctxh = null;
		try (Connection connection = JDBCUtil.getConnection();      	
		            PreparedStatement preparedStatement = connection.prepareStatement(Select_HDCTXH_ID);) {
			preparedStatement.setString(1, maHoatDong);
	 		System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();
         
            while (rs.next()) {
            	hdctxh = new HoatDongCTXH();
            	hdctxh.setMaHoatDong(maHoatDong);
            	hdctxh.setTenHoatDong(rs.getNString("TenHD"));
            	hdctxh.setDiem(rs.getString("Diem"));
            	hdctxh.setNoiDung(rs.getString("NoiDung"));
            	hdctxh.setThoiGianDienRa(rs.getDate("TgDienRa"));
            	hdctxh.setThoiGianKetThuc(rs.getDate("TgKetThuc"));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
		return hdctxh;
	}
	
	public boolean UpdateHDCTXH(String maHoatDong, String tenHoatDong, String diem, String noiDung, Date thoiGianDienRa, Date thoiGianKetThuc) {
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_HDCTXH_ID);) {
            preparedStatement.setNString(1, tenHoatDong);
            preparedStatement.setString(2, diem);
            preparedStatement.setNString(3, noiDung);
            preparedStatement.setDate(4, thoiGianDienRa);
            preparedStatement.setDate(5, thoiGianKetThuc);
            preparedStatement.setString(6, maHoatDong);
     
            System.out.println(preparedStatement);
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated > 0;
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
            return false;
        }
	}
	
	public boolean InsertHDCTXH(String tenHoatDong, String diem, String noiDung, String thoiGianDienRa, String thoiGianKetThuc) {
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_HDCTXH);) {
            preparedStatement.setNString(1, tenHoatDong);
            preparedStatement.setString(2, diem);
            preparedStatement.setNString(3, noiDung);
            preparedStatement.setString(4, thoiGianDienRa);
            preparedStatement.setString(5, thoiGianKetThuc);
     
            System.out.println(preparedStatement);
            int rowUpdated = preparedStatement.executeUpdate();
            return rowUpdated > 0;
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
            return false;
        }
	}
}
