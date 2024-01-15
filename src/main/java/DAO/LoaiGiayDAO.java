package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.LoaiGiay;
import Util.HandleExeption;
import Util.JDBCUtil;

public class LoaiGiayDAO {
	
	public LoaiGiayDAO() {
		
	}
	private static final String Select_All_LoaiGiay = "SELECT lg.MaLoaiGiay, lg.TenLoaiGiay FROM loai_giay lg ";
	private static final String Insert_LoaiGiay1 = "INSERT INTO loai_giay (TenLoaiGiay) VALUES (?)";
	private static final String Check_Exist_LoaiGiay = "SELECT COUNT(*) FROM loai_giay WHERE tenloaigiay = ?";
	
	private static final String Select_ALL_LoaiGiay = "SELECT * from loai_giay";
	private static final String Select_LoaiGiay_MaLoaiGiay = "SELECT * from loai_giay where MaKhoa= ?";
	
	private static final String Update_LoaiGiay ="UPDATE loai_giay\r\n"
			+ "SET TenLoaiGiay=?\r\n"
			+ "WHERE MaLoaiGiay = ? ;\r\n";
	
	private static final String Insert_LoaiGiay = "INSERT INTO loai_giay (TenLoaiGiay, TrangThai)\r\n"
			+ "VALUES (?, ? );\r\n  ";
	
	public void insertLoaiGiay(String TrangThai,String TenLoaiGiay) throws SQLException {
		
//      System.out.println("lay oke");
      String matt= null;
      String id_tk = null;
      
      try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Insert_LoaiGiay)) {
     	   System.out.println(preparedStatement);
         preparedStatement.setString(1, TenLoaiGiay);
         preparedStatement.setString(2, TrangThai);
         System.out.println(preparedStatement);
         preparedStatement.executeUpdate();
     } catch (SQLException exception) {
     	HandleExeption.printSQLException(exception);
     }
  }
	
	public LoaiGiay selectLoaiGiay(String MaLoaiGiay) {
		LoaiGiay loaigiay = null;
      // Step 1: Establishing a Connection
      try (Connection connection = JDBCUtil.getConnection();
          // Step 2:Create a statement using connection object
          PreparedStatement preparedStatement = connection.prepareStatement(Select_LoaiGiay_MaLoaiGiay);) {
          preparedStatement.setString(1, MaLoaiGiay);
          System.out.println(preparedStatement);
          // Step 3: Execute the query or update query
          ResultSet rs = preparedStatement.executeQuery();

          // Step 4: Process the ResultSet object.
          while (rs.next()) {	
          	loaigiay = new LoaiGiay();
          	loaigiay.setMaLoaiGiay(MaLoaiGiay);
          	loaigiay.setTenLoaiGiay(rs.getString(2));        
          }
      } catch (SQLException exception) {
          HandleExeption.printSQLException(exception);
      }
      return loaigiay;
  }
	
	public List<LoaiGiay> selectallLoaiGiay() {
		List<LoaiGiay> loaigiays = new ArrayList < > ();
      // Step 1: Establishing a Connection
      try (Connection connection = JDBCUtil.getConnection();
          // Step 2:Create a statement using connection object
          PreparedStatement preparedStatement = connection.prepareStatement(Select_ALL_LoaiGiay);) {
          System.out.println(preparedStatement);
          // Step 3: Execute the query or update query
          ResultSet rs = preparedStatement.executeQuery();

          // Step 4: Process the ResultSet object.
          while (rs.next()) {
              String MaLoaiGiay = rs.getString(1);
              String TenLoaiGiay = rs.getString(2);           
              loaigiays.add(new LoaiGiay(MaLoaiGiay,TenLoaiGiay));
              
          }
      } catch (SQLException exception) {
          HandleExeption.printSQLException(exception);
      }
      return loaigiays;
  }
	
	public void upDate_LoaiGiay(String maLoaiGiay,String tenLoaiGiay) {
      
      // Step 1: Establishing a Connection
      try (Connection connection = JDBCUtil.getConnection();
          // Step 2:Create a statement using connection object
          PreparedStatement preparedStatement = connection.prepareStatement(Update_LoaiGiay);) {
          preparedStatement.setString(1,  tenLoaiGiay);
          preparedStatement.setString(2,  maLoaiGiay);
   
          System.out.println(preparedStatement);
          // Step 3: Execute the query or update query
          preparedStatement.executeUpdate();
          // Step 4: Process the ResultSet object.
         
      } catch (SQLException exception) {
          HandleExeption.printSQLException(exception);
      }
     
	}
	public List<LoaiGiay> SelectAllLoaiGiay() {
		List<LoaiGiay> lgs = new ArrayList < > ();

        try (Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Select_All_LoaiGiay);) {
            System.out.println(preparedStatement);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	String maLoaiGiay = rs.getString("MaLoaiGiay");
            	String tenLoaiGiay = rs.getString("TenLoaiGiay");
                lgs.add(new LoaiGiay(maLoaiGiay, tenLoaiGiay));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return lgs;
    }
	
	public boolean InsertGXN(String tenLoaiGiay) {
		try (Connection connection = JDBCUtil.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(Insert_LoaiGiay1);) {
	            System.out.println(preparedStatement);	            
	            preparedStatement.setString(1, tenLoaiGiay);
	            
	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;
	            
	        } catch (SQLException exception) {
	            HandleExeption.printSQLException(exception);
	            return false;
	        }
	}
	
	public boolean isTenLoaiGiayExists(String tenLoaiGiay) {
		try (Connection connection = JDBCUtil.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(Check_Exist_LoaiGiay);) {
	            System.out.println(preparedStatement);	            
	            preparedStatement.setString(1, tenLoaiGiay);
	            
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	                int count = rs.getInt(1);
	                return count > 0;
	            }
	            
	        } catch (SQLException exception) {
	            HandleExeption.printSQLException(exception);
	            return false;
	        }
		return false;
	}
}
