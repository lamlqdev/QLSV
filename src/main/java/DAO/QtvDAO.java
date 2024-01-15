package DAO;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Models.QTV;
import Models.SinhVien;
import Models.TaiKhoan;
import Util.JDBCUtil;
import Util.HandleExeption;

public class QtvDAO {

	public QtvDAO() {
		
	}
	 
	private static final String Select_TT_QTV_idTK = "SELECT QTV.MaQTV , thongtincanhan.HoTen, thongtincanhan.NgaySinh, thongtincanhan.Diachi, thongtincanhan.QueQuan, thongtincanhan.Sodienthoai, thongtincanhan.email,thongtincanhan.gioitinh, QTV.TrangThai\r\n"
			+ "FROM QTV\r\n"
			+ "JOIN thongtincanhan ON QTV.MaTT = thongtincanhan.MaTT\r\n"
			+ "WHERE QTV.Id_Tk = ? ;";
	private static final String Update_TTLL_QTV ="UPDATE QTV\r\n"
			+ "JOIN thongtincanhan ON QTV.MaTT = thongtincanhan.MaTT\r\n"
			+ "SET thongtincanhan.Sodienthoai = ?, thongtincanhan.email = ? \r\n"
			+ "WHERE QTV.Id_Tk = ? ;";      
	private static final String Select_QTV_QUENMK = "SELECT qtv.Maqtv ,"
			+ " thongtincanhan.email,taikhoan.matkhau\r\n"
			+ "FROM qtv\r\n"
			+ "JOIN thongtincanhan ON qtv.MaTT = thongtincanhan.MaTT\r\n"
			+"JOIN taikhoan ON qtv.id_tk = taikhoan.id_tk\r\n"
			+ "WHERE qtv.maqtv= ? and thongtincanhan.email =?  ;";	
	
	public QTV selectQTV(String id_tk) {
        QTV qtv = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_TT_QTV_idTK);) {
            preparedStatement.setString(1, id_tk);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String MaQTV = rs.getString(1);
                String HoTen = rs.getNString(2);
                java.sql.Date NgaySinh   = rs.getDate(3);
                String diachi = rs.getNString(4);
                String quequan = rs.getNString(5);
                String sdt = rs.getString(6);
                String email = rs.getString(7);
                String trangthai = rs.getNString(9);             
                String gioitinh = rs.getNString(8);
                qtv = new QTV(MaQTV, HoTen, NgaySinh, diachi, quequan, sdt,email,trangthai,gioitinh);
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return qtv;
    }
	
	public void upDate_ttll_QTV(String id_tk, String sdt,String email) {
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_TTLL_QTV);) {
            preparedStatement.setString(1, sdt);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, id_tk);
            
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement. executeUpdate();
            // Step 4: Process the ResultSet object.
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
    }
	
	public QTV quenMK(String email, String mssv)
	{
		QTV qtv = new QTV();
		 try (Connection connection = JDBCUtil.getConnection();
		            // Step 2:Create a statement using connection object
		            PreparedStatement preparedStatement = connection.prepareStatement(Select_QTV_QUENMK);) {
		            preparedStatement.setString(1, mssv);
		            preparedStatement.setString(2, email);
		            System.out.println(preparedStatement);
		            // Step 3: Execute the query or update query
		            ResultSet rs = preparedStatement.executeQuery();

		            // Step 4: Process the ResultSet object.
		            if (rs.next()) {
		                qtv.setMaQTV(rs.getString(1)) ;		               
		                qtv.setEmail(rs.getString(2)); 
		                TaiKhoan tk = new TaiKhoan();
		                tk.setMatkhau(rs.getString(3));
		                qtv.setTaiKhoan(tk);
		                System.out.println(qtv.getEmail());
		            }
		            else {
		         	   qtv = null;
		            }
		            
		        } catch (SQLException exception) {
		            HandleExeption.printSQLException(exception);
		        }
		 return qtv;
	}
}
