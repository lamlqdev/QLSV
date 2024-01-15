package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.CTSV;
import Models.LoaiGiay;
import Models.QTV;
import Models.LyDo;
import Util.HandleExeption;
import Util.JDBCUtil;

public class LyDoDAO {
	public LyDoDAO() {
		
	}
	private static final String Select_ALL_LyDO = "SELECT * from lydo";
	private static final String Select_LyDo_MaLyDo = "SELECT * from lydo where MaLyDo= ?";
	
	private static final String Update_LyDo ="UPDATE lydo\r\n"
			+ "SET TenLyDo=?, SET NoiDung=?, SET TrangThai=?\r\n"
			+ "WHERE MaLyDo = ? ;\r\n";
	
	private static final String Insert_LyDo = "INSERT INTO lydo (TenLyDo, NoiDung, TrangThai)\r\n"
			+ "VALUES (?, ?, ?);\r\n  ";
	
	public boolean insertLyDo(String TrangThai,String TenLyDo,String NoiDung) throws SQLException {		
        try (Connection connection = JDBCUtil.getConnection(); 
           PreparedStatement preparedStatement = connection.prepareStatement(Insert_LyDo)) {
       	   System.out.println(preparedStatement);
           preparedStatement.setString(1, TenLyDo);
           preparedStatement.setString(2, NoiDung);
           preparedStatement.setString(3, TrangThai);
           System.out.println(preparedStatement);
           int rowUpdated = preparedStatement.executeUpdate();
           return rowUpdated > 0;
       } catch (SQLException exception) {
       		HandleExeption.printSQLException(exception);
       		return false;
       }
    }
	
	public LyDo selectLyDo(String MaLyDo) {
		LyDo lydo = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_LyDo_MaLyDo);) {
            preparedStatement.setString(1, MaLyDo);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {	
            	lydo = new LyDo();
            	lydo.setMaLyDo(MaLyDo);
            	lydo.setTenLyDo(rs.getString(2));
            	lydo.setNoiDung(rs.getString(3));
            	lydo.setTrangThai(rs.getString(4));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return lydo;
    }
	
	public List<LyDo> selectallLyDo() {
		List<LyDo> lydos = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_ALL_LyDO);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String MaLyDo = rs.getString(1);
                String TenLyDo = rs.getString(2);
                String NoiDung = rs.getString(3);
                String TrangThai = rs.getString(4);
                lydos.add(new LyDo(MaLyDo,TenLyDo,NoiDung,TrangThai));
                
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return lydos;
    }
	
	public void upDate_LyDo(String maLyDo,String tenLyDo, String noiDung, String trangThai) {
        
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_LyDo);) {
            preparedStatement.setString(1,  tenLyDo);
            preparedStatement.setString(2,  noiDung);
            preparedStatement.setString(3,  trangThai);
            preparedStatement.setString(4,  maLyDo);
     
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
            // Step 4: Process the ResultSet object.
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
       
	}
}
