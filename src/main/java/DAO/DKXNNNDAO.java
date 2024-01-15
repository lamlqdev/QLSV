package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.time.*;

import Models.DKGiayXacNhan;
import Models.DKXNNganhNghe;
import Models.KhoaHoc;
import Util.HandleExeption;
import Util.JDBCUtil;

public class DKXNNNDAO {
	public DKXNNNDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final String Select_ALL_DKXNNN = "SELECT dki_xn_nganh_nghe.*, lydo.NoiDung\r\n"
			+ "FROM dki_xn_nganh_nghe\r\n"
			+ "JOIN lydo ON dki_xn_nganh_nghe.MaLyDo = lydo.MaLyDo;";
	private static final String Select_DKXNNN_MSSV = "SELECT dki_xn_nganh_nghe.*, lydo.NoiDung\r\n"
			+ "FROM dki_xn_nganh_nghe\r\n"
			+ "JOIN lydo ON dki_xn_nganh_nghe.MaLyDo = lydo.MaLyDo where MSSV= ? order by dki_xn_nganh_nghe.id";
	
	private static final String Update_DKXNNN ="UPDATE dki_xn_nganh_nghe\r\n"
			+ "SET ThoiGianPhanHoi= ?, TieuDe = ?, MaCTSV = ?, NdPhanHoi = ?, TrangThai = ? \r\n"
			+ "WHERE id = ? ;\r\n";
	
	private static final String Insert_DKXNNN = "INSERT INTO dki_xn_nganh_nghe (TenDichVu,NamHoc,HocKy,MSSV,MaLyDo,ThoiGianDki,TrangThai)\r\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?);\r\n  ";
	
	public boolean insertDKXNNN(String TenDichVu, String NamHoc, String HocKy, String MSSV, String MaLyDo, LocalDateTime ThoiGianDK, String TrangThai) throws SQLException {
	    
	    System.out.println("lay oke");
	    boolean result = false;
	    
	    try (Connection connection = JDBCUtil.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(Insert_DKXNNN)) {
	        
	        System.out.println(preparedStatement);
	        preparedStatement.setString(1, TenDichVu);
	        preparedStatement.setString(2, NamHoc);
	        preparedStatement.setString(3, HocKy);
	        preparedStatement.setString(4, MSSV);
	        preparedStatement.setString(5, MaLyDo);
	        preparedStatement.setTimestamp(6, JDBCUtil.getSQLTimestamp(ThoiGianDK));
	        preparedStatement.setString(7, TrangThai);
	        
	        System.out.println(preparedStatement);
	        int affectedRows = preparedStatement.executeUpdate();
	        result = affectedRows > 0; // Kiểm tra xem có hàng nào được thêm vào
	    } catch (SQLException exception) {
	        HandleExeption.printSQLException(exception);
	    }
	    
	    return result;
	}
	
	public List<DKXNNganhNghe> selectDKXNNN(String MSSV) {
		List<DKXNNganhNghe> dkxnnn = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_DKXNNN_MSSV);) {
            preparedStatement.setString(1, MSSV);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {	
            	String MSSV_ = rs.getString(6);
            	String NamHoc = rs.getString(4);
            	String HocKy = rs.getString(5);
            	String TenDichVu =  rs.getString(2);
            	String MaLyDo = rs.getString(7);
            	String NDLyDo = rs.getString(13);
            	String TrangThai = rs.getString(12);
            	LocalDateTime ThoiGianDK = rs.getTimestamp(3).toLocalDateTime();
            	dkxnnn.add(new DKXNNganhNghe(TenDichVu, NamHoc, HocKy, MaLyDo, NDLyDo, MSSV_, ThoiGianDK, TrangThai));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return dkxnnn;
    }
}
