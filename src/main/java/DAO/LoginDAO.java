package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.TaiKhoan;
import Util.HandleExeption;
import Util.JDBCUtil;

public class LoginDAO {
	private static final String Update_MK ="UPDATE taikhoan\r\n"
			+ "SET  matkhau = ? \r\n"
			+ "WHERE id_Tk = ? ;\r\n";
    public TaiKhoan onLogin(TaiKhoan Acc) throws ClassNotFoundException {
    	TaiKhoan  acc = new TaiKhoan();
        

        try (Connection connection = JDBCUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("select * from TaiKhoan where TenTk = ? and MatKhau = ? ")) {
            preparedStatement.setString(1, Acc.getTenTk());
            preparedStatement.setString(2, Acc.getMatkhau()); 

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
           if(rs.next()) {
            	acc.setIdTk( (rs.getString(1)));
                acc.setTenTk((rs.getString(2)));
                acc.setMatkhau((rs.getString(3)));
                acc.setPhanQuyen((rs.getString(4)));
                acc.setTrangThai((rs.getString(5)));
            }
           else {
        	   acc = null;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acc;
    }
    
	public void upDate_MK(String idTK ,String newMK) {
	        
	        // Step 1: Establishing a Connection
	        try (Connection connection = JDBCUtil.getConnection();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(Update_MK);) {
	            preparedStatement.setString(1,  newMK);
	            preparedStatement.setString(2,  idTK);
	           
	     
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeUpdate();
	            // Step 4: Process the ResultSet object.
	           
	        } catch (SQLException exception) {
	            HandleExeption.printSQLException(exception);
	        }
	       
		}
}