package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.*;

import Models.DKGiayXacNhan;
import Models.Khoa;
import Models.KhoaHoc;
import Util.HandleExeption;
import Util.JDBCUtil;

public class DKGXNDAO {

	public DKGXNDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final String Select_ALL_DKGXN = "SELECT * from dki_giay_xac_nhan";
	private static final String Select_DKGXN_MSSV = "SELECT * from dki_giay_xac_nhan where MSSV= ?";
	
	private static final String Update_DKGXN ="UPDATE dki_giay_xac_nhan\r\n"
			+ "SET ThoiGianPhanHoi= ?, TieuDe = ?, MaCTSV = ?, NdPhanHoi = ?, TrangThai = ? \r\n"
			+ "WHERE id = ? ;\r\n";
	
	private static final String Insert_DKGXN = "INSERT INTO dki_giay_xac_nhan (TenDichVu,SoLuong,MaLoaiGiay,MSSV,ThoiGianDki,TrangThai)\r\n"
			+ "VALUES (?, ?, ?, ?, ?, ?);\r\n  ";
	
	public boolean insertDKGXN(String TenDichVu, String SoLuong, String MaLoaiGiay, String MSSV, LocalDateTime ThoiGianDK, String TrangThai) throws SQLException {
	    
	    System.out.println("lay oke");
	    boolean result = false;
	    
	    try (Connection connection = JDBCUtil.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(Insert_DKGXN)) {
	        
	        System.out.println(preparedStatement);
	        preparedStatement.setString(1, TenDichVu);
	        preparedStatement.setString(2, SoLuong);
	        preparedStatement.setString(3, MaLoaiGiay);
	        preparedStatement.setString(4, MSSV);
	        preparedStatement.setTimestamp(5, JDBCUtil.getSQLTimestamp(ThoiGianDK));
	        preparedStatement.setString(6, TrangThai);
	        
	        System.out.println(preparedStatement);
	        int affectedRows = preparedStatement.executeUpdate();
	        result = affectedRows > 0; // Kiểm tra xem có hàng nào được thêm vào
	    } catch (SQLException exception) {
	        HandleExeption.printSQLException(exception);
	    }
	    
	    return result;
	}
	
	public List<DKGiayXacNhan> selectDKGXN(String MSSV) {
		List<DKGiayXacNhan> dkgxns = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_DKGXN_MSSV);) {
            preparedStatement.setString(1, MSSV);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {	
            	String MSSV_ = rs.getString(5);
            	String MaLoaiGiay = rs.getString(4);
            	String SoLuong = rs.getString(3);
            	String TenDichVu =  rs.getString(2);
            	String NDPhanHoi = rs.getString(10);
            	String TrangThai = rs.getString(11);
            	String STT = rs.getString(12);
            	LocalDateTime ThoiGianDK = rs.getTimestamp(6).toLocalDateTime();
            	dkgxns.add(new DKGiayXacNhan(TenDichVu,SoLuong,MaLoaiGiay,MSSV_,ThoiGianDK,NDPhanHoi,TrangThai,STT));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return dkgxns;
    }
	
//	public List<KhoaHoc> selectallKHOAHOC() {
//		List<KhoaHoc> khoahocs = new ArrayList < > ();
//        // Step 1: Establishing a Connection
//        try (Connection connection = JDBCUtil.getConnection();
//            // Step 2:Create a statement using connection object
//            PreparedStatement preparedStatement = connection.prepareStatement(Select_ALL_KH);) {
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            ResultSet rs = preparedStatement.executeQuery();
//
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                String MaKH= rs.getString(1);
//                String TenKH = rs.getNString(2);
//                String NamBD = rs.getString(3);
//                String NamKT = rs.getString(4);
//                khoahocs.add(new KhoaHoc(MaKH,TenKH,NamBD,NamKT));
//                
//            }
//        } catch (SQLException exception) {
//            HandleExeption.printSQLException(exception);
//        }
//        return khoahocs;
//    }
	
//	public void upDate_KH(String MaKH, String tenKH,String NamBD,String NamKT) {
//        
//        // Step 1: Establishing a Connection
//        try (Connection connection = JDBCUtil.getConnection();
//            // Step 2:Create a statement using connection object
//            PreparedStatement preparedStatement = connection.prepareStatement(Update_KH);) {
//            preparedStatement.setString(1,  tenKH);
//            preparedStatement.setString(2,  NamBD);
//            preparedStatement.setString(3,  NamKT);
//            preparedStatement.setString(4,  MaKH);
//     
//            System.out.println(preparedStatement);
//            // Step 3: Execute the query or update query
//            preparedStatement.executeUpdate();
//            // Step 4: Process the ResultSet object.
//           
//        } catch (SQLException exception) {
//            HandleExeption.printSQLException(exception);
//        }
//       
//	}
	
}
