package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Models.SinhVien;
import Models.ThongBao;
import Util.HandleExeption;
import Util.JDBCUtil;

public class ThongBaoDAO {

	public ThongBaoDAO() {
		// TODO Auto-generated constructor stub
	}
	private static final String Select_ALL_THONGBAO_CTSV = "SELECT dki_giay_xac_nhan.tendichvu AS tenDV, thongtincanhan.hoten AS TenSinhvien, dki_giay_xac_nhan.Thoigiandki AS thoigiangui,khoa.tenkhoa, khoahoc.tenKH\r\n"
			+ "FROM dki_giay_xac_nhan\r\n"
			+ "JOIN Sinhvien ON dki_giay_xac_nhan.Mssv = Sinhvien.Mssv\r\n"
			+ "JOIN thongtincanhan ON Sinhvien.matt = thongtincanhan.matt\r\n"
			+ "JOIN khoa ON Sinhvien.makhoa = khoa.makhoa\r\n"
			+ "JOIN khoahoc ON Sinhvien.makhoahoc = khoahoc.makhoahoc\r\n"
			+ "where dki_giay_xac_nhan.TrangThai='Chưa duyệt'\r\n"
			+ "UNION\r\n"
			+ "\r\n"
			+ "SELECT dki_tham_gia_ctxh.tendichvu AS tenDV, thongtincanhan.hoten AS TenSinhvien, dki_tham_gia_ctxh.Thoigiandki AS thoigiangui,khoa.tenkhoa, khoahoc.tenKH\r\n"
			+ "FROM dki_tham_gia_ctxh\r\n"
			+ "JOIN Sinhvien ON dki_tham_gia_ctxh.Mssv = Sinhvien.Mssv\r\n"
			+ "JOIN thongtincanhan ON Sinhvien.matt = thongtincanhan.matt\r\n"
			+ "JOIN khoa ON Sinhvien.makhoa = khoa.makhoa\r\n"
			+ "JOIN khoahoc ON Sinhvien.makhoahoc = khoahoc.makhoahoc\r\n"
			+ "where dki_tham_gia_ctxh.TrangThai='Chưa duyệt'\r\n"
			+ "UNION\r\n"
			+ "\r\n"
			+ "SELECT dki_xn_nganh_nghe.tendichvu AS tenDV, thongtincanhan.hoten AS TenSinhvien, dki_xn_nganh_nghe.Thoigiandki AS thoigiangui,khoa.tenkhoa, khoahoc.tenKH\r\n"
			+ "FROM dki_xn_nganh_nghe\r\n"
			+ "JOIN Sinhvien ON dki_xn_nganh_nghe.Mssv = Sinhvien.Mssv\r\n"
			+ "JOIN thongtincanhan ON Sinhvien.matt = thongtincanhan.matt\r\n"
			+ "JOIN khoa ON Sinhvien.makhoa = khoa.makhoa\r\n"
			+ "JOIN khoahoc ON Sinhvien.makhoahoc = khoahoc.makhoahoc\r\n "
			+"where dki_xn_nganh_nghe.TrangThai='Chưa duyệt'\r\n;";
	
	private static final String Select_ALL_THONGBAO_SV = "SELECT dki_giay_xac_nhan.tendichvu AS tendichvu, CTSV_hoten.hoten AS TenCTSV,\r\n"
			+ "       dki_giay_xac_nhan.Thoigiandki AS thoigiangui, \r\n"
			+ "       dki_giay_xac_nhan.thoigianphanhoi AS thoigianphanhoi,\r\n"
			+ "       dki_giay_xac_nhan.tieude AS tieude, \r\n"
			+ "       dki_giay_xac_nhan.ndPhanHoi AS noidung\r\n"
			+ "FROM dki_giay_xac_nhan\r\n"
			+ "JOIN Sinhvien ON dki_giay_xac_nhan.Mssv = Sinhvien.Mssv\r\n"
			+ "JOIN CTSV ON dki_giay_xac_nhan.maCTSV = CTSV.maCTSV\r\n"
			+ "JOIN thongtincanhan AS CTSV_hoten ON CTSV.matt = CTSV_hoten.matt\r\n"
			+ "where sinhvien.Id_tk= ? and dki_giay_xac_nhan.TrangThai='Đã duyệt'\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "UNION\r\n"
			+ "\r\n"
			+ "SELECT dki_tham_gia_ctxh.tenDichvu AS tendichvu, CTSV_hoten.hoten AS TenCTSV,\r\n"
			+ "       dki_tham_gia_ctxh.Thoigiandki AS thoigiangui, \r\n"
			+ "       dki_tham_gia_ctxh.thoigianphanhoi AS thoigianphanhoi,\r\n"
			+ "       dki_tham_gia_ctxh.tieude AS tieude, \r\n"
			+ "       dki_tham_gia_ctxh.ndPhanHoi AS noidung\r\n"
			+ "FROM dki_tham_gia_ctxh\r\n"
			+ "JOIN Sinhvien ON dki_tham_gia_ctxh.Mssv = Sinhvien.Mssv\r\n"
			+ "JOIN CTSV ON dki_tham_gia_ctxh.maCTSV = CTSV.maCTSV\r\n"
			+ "JOIN thongtincanhan AS CTSV_hoten ON CTSV.matt = CTSV_hoten.matt\r\n"
			+ "where sinhvien.Id_tk=? and ( dki_tham_gia_ctxh.TrangThai='Đã duyệt' or dki_tham_gia_ctxh.TrangThai ='Đã tham gia')\r\n"
			+ "\r\n"
			+ "UNION\r\n"
			+ "\r\n"
			+ "SELECT dki_xn_nganh_nghe.tenDichvu AS tendichvu, CTSV_hoten.hoten AS TenCTSV,\r\n"
			+ "       dki_xn_nganh_nghe.Thoigiandki AS thoigiangui, \r\n"
			+ "       dki_xn_nganh_nghe.thoigianphanhoi AS thoigianphanhoi,\r\n"
			+ "       dki_xn_nganh_nghe.tieude AS tieude, \r\n"
			+ "       dki_xn_nganh_nghe.ndPhanHoi AS noidung\r\n"
			+ "FROM dki_xn_nganh_nghe\r\n"
			+ "JOIN Sinhvien ON dki_xn_nganh_nghe.Mssv = Sinhvien.Mssv\r\n"
			+ "JOIN CTSV ON dki_xn_nganh_nghe.maCTSV = CTSV.maCTSV\r\n"
			+ "JOIN thongtincanhan AS CTSV_hoten ON CTSV.matt = CTSV_hoten.matt\r\n"
			+ "where sinhvien.Id_tk=? and dki_xn_nganh_nghe.TrangThai='Đã duyệt';";
	private static final String COUNT_SLTB_QTV = "SELECT  dki_giay_xac_nhan.TrangThai\r\n"
			+ "FROM dki_giay_xac_nhan\r\n"
			+ "JOIN Sinhvien ON dki_giay_xac_nhan.Mssv = Sinhvien.Mssv\r\n"
			+ "where dki_giay_xac_nhan.thoigiandki >= ? and dki_giay_xac_nhan.thoigiandki <= ? \r\n"
			+ "UNION ALL\r\n"
			+ "\r\n"
			+ "SELECT dki_tham_gia_ctxh.TrangThai\r\n"
			+ "FROM dki_tham_gia_ctxh\r\n"
			+ "JOIN Sinhvien ON dki_tham_gia_ctxh.Mssv = Sinhvien.Mssv\r\n"
			+ "where dki_tham_gia_ctxh.thoigiandki >= ? and dki_tham_gia_ctxh.thoigiandki <= ? \r\n"
			+ "UNION ALL\r\n"
			+ "\r\n"
			+ "SELECT dki_xn_nganh_nghe.TrangThai\r\n"
			+ "FROM dki_xn_nganh_nghe\r\n"
			+ "JOIN Sinhvien ON dki_xn_nganh_nghe.Mssv = Sinhvien.Mssv \r\n"
			+"where dki_xn_nganh_nghe.thoigiandki >= ? and dki_xn_nganh_nghe.thoigiandki <= ? \r\n;";
	
	public List<ThongBao> selectalltbCTSV() {
		List<ThongBao> thongbaos = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_ALL_THONGBAO_CTSV);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	ThongBao thongBao= new ThongBao();
                thongBao.setTenDV(rs.getNString(1)); 
                thongBao.setTenSV(rs.getNString(2)); 
                Timestamp timestamp = rs.getTimestamp(3);
                thongBao.setTGGui(timestamp); 
                thongBao.setTenKhoa(rs.getNString(4));
                thongBao.setTenKH(rs.getString(5));
                thongbaos.add(thongBao);          
            }
            
        }catch (SQLException exception) {
                HandleExeption.printSQLException(exception);
            }
        return thongbaos;
        }


	public List<ThongBao> selectalltbSV(String idTK) {
		List<ThongBao> thongbaos = new ArrayList < > ();
	    // Step 1: Establishing a Connection
	    try (Connection connection = JDBCUtil.getConnection();
	        // Step 2:Create a statement using connection object
	        PreparedStatement preparedStatement = connection.prepareStatement(Select_ALL_THONGBAO_SV);) {
	    	preparedStatement.setString(1, idTK);
	        preparedStatement.setString(2,  idTK);
	        preparedStatement.setString(3,  idTK);
	        System.out.println(preparedStatement);
	        // Step 3: Execute the query or update query
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	        	ThongBao thongBao= new ThongBao();
	            thongBao.setTenDV(rs.getNString(1)); 
	            thongBao.setTenCTSV(rs.getNString(2)); 
	            System.out.println(thongBao.getTenCTSV());
	            thongBao.setTgPhanHoi(rs.getTimestamp(4).toLocalDateTime()); 
	            thongBao.setTieuDe(rs.getNString(5));
	            thongBao.setNDPhanHoi(rs.getNString(6));
	            thongbaos.add(thongBao);       
	            
	        }
	        
	    }catch (SQLException exception) {
	            HandleExeption.printSQLException(exception);
	        }
	    return thongbaos;
	    }

	public List<ThongBao> countTB_QTV(Date NgayBD, Date NgayKT) {
		List<ThongBao> thongbaos = new ArrayList < > ();
	    // Step 1: Establishing a Connection
	    try (Connection connection = JDBCUtil.getConnection();
	        // Step 2:Create a statement using connection object
	        PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SLTB_QTV);) {
	    	preparedStatement.setDate(1, NgayBD);
            preparedStatement.setDate(2, NgayKT);
            preparedStatement.setDate(3, NgayBD);
            preparedStatement.setDate(4, NgayKT);
            preparedStatement.setDate(5, NgayBD);
            preparedStatement.setDate(6, NgayKT);
	        System.out.println(preparedStatement);
	        // Step 3: Execute the query or update query
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	ThongBao thongBao= new ThongBao();
	            thongBao.setTrangThai(rs.getNString(1)); 
	            
	            thongbaos.add(thongBao);          
	        }
	        
	    }catch (SQLException exception) {
	            HandleExeption.printSQLException(exception);
	        }
	    return thongbaos;
	    }
}


