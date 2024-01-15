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
import Models.Khoa;
import Models.QTV;
import Util.HandleExeption;
import Util.JDBCUtil;

public class KhoaDAO {

	public KhoaDAO() {
		// TODO Auto-generated constructor stub
	}
	private static final String Select_ALL_KHOA = "SELECT * from khoa";
	private static final String Select_KHOA_MaKHOA = "SELECT * from khoa where MaKhoa= ?";
	
	private static final String Update_Khoa ="UPDATE khoa\r\n"
			+ "SET TenKhoa=?, TrangThai = ? \r\n"
			+ "WHERE MaKhoa = ? ;\r\n";
	
	private static final String Insert_Khoa = "INSERT INTO khoa ( TenKhoa,TrangThai)\r\n"
			+ "VALUES (?, ? );\r\n  ";
			
	
			
	public String insertKhoa(String TrangThai,String TenKhoa) throws SQLException {
		
        System.out.println("lay oke");
        String makhoa= null;
       
        
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Insert_Khoa, Statement.RETURN_GENERATED_KEYS)) {
       	 System.out.println(preparedStatement);
           preparedStatement.setString(1, TenKhoa );
           preparedStatement.setString(2, TrangThai );
           System.out.println(preparedStatement);
           preparedStatement.executeUpdate();
           ResultSet rs1 = preparedStatement.getGeneratedKeys();
           if (rs1.next()) {
               makhoa = rs1.getString(1);}
       } catch (SQLException exception) {
       	HandleExeption.printSQLException(exception);
       }
        return makhoa;
    }
	
	public Khoa selectKhoa(String MaKhoa) {
		Khoa khoa = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_KHOA_MaKHOA);) {
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
	
	public List<Khoa> selectallKHOA() {
		List<Khoa> khoas = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_ALL_KHOA);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String MaKhoa= rs.getString(1);
                String TenKhoa = rs.getNString(2);
                String TrangThai = rs.getNString(3);           
                khoas.add(new Khoa(MaKhoa,TenKhoa,TrangThai));
                
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return khoas;
    }
	
	public void upDate_Khoa(String MaKhoa,String TrangThai,String tenKhoa) {
        
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_Khoa);) {
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
