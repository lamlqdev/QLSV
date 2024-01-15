package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Khoa;
import Models.KhoaHoc;
import Util.HandleExeption;
import Util.JDBCUtil;

public class KhoaHocDAO {

	public KhoaHocDAO() {
		// TODO Auto-generated constructor stub
	}
	private static final String Select_ALL_KH = "SELECT * from khoahoc";
	private static final String Select_KHOA_MaKH= "SELECT * from khoahoc where MaKhoaHoc= ?";
	
	private static final String Update_KH ="UPDATE khoahoc\r\n"
			+ "SET TenKH=N?,NamBD= ?,NamKT=? \r\n"
			+ "WHERE MaKhoaHoc = ? ;\r\n";
	
	private static final String Insert_KH = "INSERT INTO khoahoc ( TenKhoa,NamBD,NamKT)\r\n"
			+ "VALUES (?, ? ,?,?);\r\n  ";
			
	
			
	public String insertKH(String  TenKhoa,String NamBD,String NamKT) throws SQLException {
		
        System.out.println("lay oke");
        String maKH= null;
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Insert_KH,Statement.RETURN_GENERATED_KEYS)) {
       	 System.out.println(preparedStatement);
           preparedStatement.setString(1, TenKhoa );
           preparedStatement.setString(2, NamBD );
           preparedStatement.setString(3, NamKT );
           System.out.println(preparedStatement);
           preparedStatement.executeUpdate();
           ResultSet rs1 = preparedStatement.getGeneratedKeys();
           if (rs1.next()) {
        	   maKH = rs1.getString(1);}
       } catch (SQLException exception) {
       	HandleExeption.printSQLException(exception);
       }
       return maKH;
    }
	
	public KhoaHoc selectKH(String MaKH) {
		KhoaHoc khoahoc = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_KHOA_MaKH);) {
            preparedStatement.setString(1, MaKH);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {	
            	khoahoc = new KhoaHoc();
            	khoahoc.setMaKH(MaKH);
            	khoahoc.setTenKH(rs.getNString(2));
            	khoahoc.setNamBD(rs.getString(3));
            	khoahoc.setNamKT(rs.getString(4));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return khoahoc;
    }
	
	public List<KhoaHoc> selectallKHOAHOC() {
		List<KhoaHoc> khoahocs = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_ALL_KH);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String MaKH= rs.getString(1);
                String TenKH = rs.getNString(2);
                String NamBD = rs.getString(3);
                String NamKT = rs.getString(4);
                khoahocs.add(new KhoaHoc(MaKH,TenKH,NamBD,NamKT));
                
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return khoahocs;
    }
	
	public void upDate_KH(String MaKH, String tenKH,String NamBD,String NamKT) {
        
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_KH);) {
            preparedStatement.setString(1,  tenKH);
            preparedStatement.setString(2,  NamBD);
            preparedStatement.setString(3,  NamKT);
            preparedStatement.setString(4,  MaKH);
     
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
            // Step 4: Process the ResultSet object.
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
       
	}
}
