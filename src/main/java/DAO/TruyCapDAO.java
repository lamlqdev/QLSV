package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Models.SinhVien;
import Models.TruyCap;
import Util.HandleExeption;
import Util.JDBCUtil;

public class TruyCapDAO {

	public TruyCapDAO() {
		// TODO Auto-generated constructor stub
	}
	private static final String COUNT_TRUYCAP = "SELECT TaiKhoan.phanquyen, COUNT(*) AS SoLuong\r\n"
			+ "FROM TruyCap\r\n"
			+ "JOIN TaiKhoan ON TruyCap.Id_Tk = TaiKhoan.id_tk\r\n"
			+ "WHERE TaiKhoan.phanquyen IN ('sv', 'qtv', 'ctsv')\r\n"
			+ "  AND ThoiGian >= ? AND ThoiGian <= ? \r\n"
			+ "GROUP BY TaiKhoan.phanquyen ;";
	
	private static final String INSERT_TRUYCAP= " INSERT INTO TruyCap (Id_Tk, ThoiGian) VALUES (?, ?) " ;
	
	public TruyCap count_SL_truycap(Date NgayBD, Date NgayKT) {
		TruyCap truycap = new TruyCap();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(COUNT_TRUYCAP);) {
            preparedStatement.setDate(1, NgayBD);
            preparedStatement.setDate(2, NgayKT);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();        
            // Step 4: Process the ResultSet object.
            while (rs.next()) {	
            	String role = rs.getString(1);
            	if(role.equals("sv"))
            	{
            		truycap.setslTK_SV(rs.getString(2));
            		System.out.println(rs.getString(2));
            	}
            	else if(role.equals("ctsv"))
            	{
            		truycap.setslTK_CTSV(rs.getString(2));
            		System.out.println(rs.getString(2));
            	}
            	else if(role.equals("qtv"))
            	{
            		truycap.setslTK_QTV(rs.getString(2));
            		System.out.println(rs.getString(2));
            	}     	       
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return truycap;
    }
	
	public void insertTruyCap(String Id_TK) throws SQLException {
	    try (Connection connection = JDBCUtil.getConnection(); 
	         PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRUYCAP)) {
	        
	        preparedStatement.setString(1, Id_TK);
	        preparedStatement.setDate(2, Date.valueOf(LocalDate.now())); // Chuyển LocalDate thành java.sql.Date

	        System.out.println(preparedStatement);
	        preparedStatement.executeUpdate();
	    } catch (SQLException exception) {
	        HandleExeption.printSQLException(exception);
	    }
	}

}
