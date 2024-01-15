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
import Models.DKCTXH;
import Models.KhoaHoc;
import Util.HandleExeption;
import Util.JDBCUtil;

public class DKCTXHDAO {
	public DKCTXHDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final String Select_ALL_DKXNNN = "SELECT dki_xn_nganh_nghe.*, lydo.NoiDung\r\n"
			+ "FROM dki_xn_nganh_nghe\r\n"
			+ "JOIN lydo ON dki_xn_nganh_nghe.MaLyDo = lydo.MaLyDo;";
	private static final String Select_DKCTXH_MSSV = "SELECT dki_tham_gia_ctxh.*, ctxh.TenHD, ctxh.Diem, ctxh.NoiDung\r\n"
			+ "FROM dki_tham_gia_ctxh\r\n"
			+ "JOIN ctxh ON dki_tham_gia_ctxh.MaHD = ctxh.MaHD\r\n"
			+ "WHERE dki_tham_gia_ctxh.MSSV = ? order by dki_tham_gia_ctxh.id";
	
	private static final String Update_DKXNNN ="UPDATE dki_xn_nganh_nghe\r\n"
			+ "SET ThoiGianPhanHoi= ?, TieuDe = ?, MaCTSV = ?, NdPhanHoi = ?, TrangThai = ? \r\n"
			+ "WHERE id = ? ;\r\n";
	
	private static final String Insert_DKCTXH = "INSERT INTO dki_tham_gia_ctxh (TenDichVu,MaHD,ThoiGianDKI,MSSV,TrangThai)\r\n"
			+ "VALUES (?, ?, ?, ?, ?);\r\n  ";
	
	public boolean insertDKCTXH(String TenDichVu, String MaHD, String MSSV, LocalDateTime ThoiGianDK, String TrangThai) throws SQLException {
	    
	    System.out.println("lay oke");
	    boolean result = false;
	    
	    try (Connection connection = JDBCUtil.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(Insert_DKCTXH)) {
	        
	        System.out.println(preparedStatement);
	        preparedStatement.setString(1, TenDichVu);
	        preparedStatement.setString(2, MaHD);
	        preparedStatement.setString(4, MSSV);
	        preparedStatement.setTimestamp(3, JDBCUtil.getSQLTimestamp(ThoiGianDK));
	        preparedStatement.setString(5, TrangThai);
	        
	        System.out.println(preparedStatement);
	        int affectedRows = preparedStatement.executeUpdate();
	        result = affectedRows > 0; // Kiểm tra xem có hàng nào được thêm vào
	    } catch (SQLException exception) {
	        HandleExeption.printSQLException(exception);
	    }
	    
	    return result;
	}
	
	public List<DKCTXH> selectDKCTXH_MSSV(String MSSV) {
		List<DKCTXH> dkctxh = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_DKCTXH_MSSV);) {
            preparedStatement.setString(1, MSSV);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {	
            	String MSSV_ = rs.getString(5);
            	String TenDichVu = rs.getString(2);
            	String MaHD = rs.getString(3);
            	String TenHD =  rs.getString(11);
            	String NDPhanHoi = rs.getString(9);
            	String Diem = rs.getString(12);
            	String NoiDung = rs.getString(13);
            	String TrangThai = rs.getString(10);
            	LocalDateTime ThoiGianDK = rs.getTimestamp(4).toLocalDateTime();
            	dkctxh.add(new DKCTXH(TenDichVu, MaHD, TenHD, ThoiGianDK, MSSV_, NDPhanHoi, TrangThai, Diem, NoiDung));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return dkctxh;
    }
}
