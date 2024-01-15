package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import Models.CTSV;
import Models.CTXH;
import Models.Khoa;
import Models.QTV;
import Util.HandleExeption;
import Util.JDBCUtil;

public class CTXHDAO {
	public CTXHDAO() {
		// TODO Auto-generated constructor stub
	}
	private static final String Select_ALL_CTXH = "SELECT * from ctxh";
	private static final String Select_CTXH_MaHD = "SELECT * from ctxh where MaHD= ?";
	private static final String Select_CTXH_TimeHD = "SELECT * FROM ctxh\r\n"
			+ "WHERE TgDienRa > CURRENT_DATE;";
	
	private static final String Update_CTXH ="UPDATE ctxh\r\n"
			+ "SET TenHD=?, Diem = ?, NoiDung = ?, TgDienRa = ?, TgKetThuc = ?, TrangThai = ?,\r\n"
			+ "WHERE MaHD = ? ;\r\n";
	
	private static final String Insert_CTXH = "INSERT INTO khoa (TenHD,Diem,NoiDung,TgDienRa,TgKeThuc,TrangThai)\r\n"
			+ "VALUES (?, ?, ?, ?, ?, ?);\r\n  ";
			
	
			
	public void insertCTXH(String TrangThai,String TenKhoa) throws SQLException {
		
        System.out.println("lay oke");
        String matt= null;
        String id_tk = null;
        
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Insert_CTXH)) {
       	 System.out.println(preparedStatement);
           preparedStatement.setString(1, TenKhoa );
           preparedStatement.setString(2, TrangThai );
           System.out.println(preparedStatement);
           preparedStatement.executeUpdate();
       } catch (SQLException exception) {
       	HandleExeption.printSQLException(exception);
       }
    }
	
	public Khoa selectCTXH(String MaKhoa) {
		Khoa khoa = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_CTXH_MaHD);) {
            preparedStatement.setString(1, MaKhoa);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {	
            	khoa = new Khoa();
            	khoa.setMaKhoa(MaKhoa);
            	khoa.setTenKhoa(rs.getNString(2));
            	khoa.setTrangThai(rs.getNString(3));        
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return khoa;
    }
	
	public List<CTXH> selectCTXH_TimeHD() {
		List<CTXH> ctxhs = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_CTXH_TimeHD);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	String MaHD = rs.getString(1);
                String TenHD = rs.getString(2);
                String Diem = rs.getString(3);
                String NoiDung = rs.getNString(4);
                LocalDateTime TgDienRa = rs.getTimestamp(5).toLocalDateTime();
                LocalDateTime TgKetThuc = rs.getTimestamp(6).toLocalDateTime();
                String TrangThai = rs.getString(7);
                ctxhs.add(new CTXH(MaHD,TenHD,Diem,NoiDung,TgDienRa,TgKetThuc,TrangThai));
                
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return ctxhs;
    }
	
	public void upDate_CTXH(String MaKhoa,String TrangThai,String tenKhoa) {
        
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_CTXH);) {
            preparedStatement.setString(1,  tenKhoa);
            preparedStatement.setString(2,  TrangThai);
            preparedStatement.setString(3,  MaKhoa);
     
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
            // Step 4: Process the ResultSet object.
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
       
	}
}
