package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.*;
import Util.HandleExeption;
import Util.JDBCUtil;

public class SinhVienDAO {

	public SinhVienDAO() {
		// TODO Auto-generated constructor stub
	}
	private static final String Select_ALL_TT_SV = "SELECT SinhVien.MSSV, thongtincanhan.HoTen,thongtincanhan.GioiTinh, thongtincanhan.NgaySinh, Khoa.TenKhoa, KhoaHoc.TenKH, thongtincanhan.Email,thongtincanhan.SoDienThoai,thongtincanhan.Quequan, SinhVien.TrangThai\r\n"
			+ "FROM SinhVien\r\n"
			+ "JOIN thongtincanhan ON SinhVien.MaTT = thongtincanhan.MaTT\r\n"
			+ "JOIN Khoa ON SinhVien.MaKhoa = Khoa.MaKhoa\r\n"
			+ "JOIN KhoaHoc ON SinhVien.MaKhoaHoc = KhoaHoc.MaKhoaHoc;";
	
	private static final String Update_SV ="UPDATE SinhVien\r\n"
			+ "SET MSSV = ? ,MaKhoa = ? , MaKhoaHoc = ? , TrangThai = ? \r\n"
			+ "WHERE MSSV = ? ;\r\n";
	private static final String Update_TTCN_SV = "UPDATE thongtincanhan\r\n"
			+ "SET HoTen = ? , NgaySinh = ? , GioiTinh=? ,DiaChi= ? ,QueQuan= ? ,SoDienThoai=? ,email=? \r\n"
			+ "WHERE MaTT = (SELECT MaTT FROM SinhVien WHERE MSSV = ? );";      
	
	private static final String Insert_ttcn_sv = "INSERT INTO thongtincanhan ( HoTen, NgaySinh, GioiTinh,DiaChi, QueQuan,SoDienThoai,Email)\r\n"
			+ "VALUES (?, ? , ?, ?, ?, ?, ?);\r\n  ";
			
	
	private static final String Insert_tk_sv=  "INSERT INTO TaiKhoan (TenTk , MatKhau,PhanQuyen,TrangThai)\r\n"
			+ "VALUES ( ?, '123','sv','enable');\r\n";
			
	private static final String Insert_sv=  "INSERT INTO SinhVien (MSSV, MaKhoa, MaKhoaHoc, MaTT, Id_TK, DiemCTXH, TrangThai)\r\n"
			+ "VALUES (?, ?, ?, ?, ?, 0, ?);";
	
	private static final String Select_TT_SV_MSSV = "SELECT SinhVien.MSSV, thongtincanhan.HoTen,thongtincanhan.GioiTinh, thongtincanhan.NgaySinh,SinhVien.MaKhoa,SinhVien.MaKhoaHoc,  thongtincanhan.Email,thongtincanhan.SoDienThoai,thongtincanhan.DiaChi,thongtincanhan.Quequan, SinhVien.TrangThai\r\n"
			+ "FROM SinhVien\r\n"
			+ "JOIN thongtincanhan ON SinhVien.MaTT = thongtincanhan.MaTT\r\n"
			+"where SinhVien.MSSV = ? ;";
	private static final String Select_TT_SV_idTK = "SELECT sinhvien.MSSV , thongtincanhan.HoTen, thongtincanhan.NgaySinh, thongtincanhan.Diachi, thongtincanhan.QueQuan, thongtincanhan.Sodienthoai,"
			+ " thongtincanhan.email,thongtincanhan.gioitinh, sinhvien.TrangThai,khoa.tenKhoa,khoahoc.tenKH,khoahoc.nambd,khoahoc.namkt\r\n"
			+ "FROM sinhvien\r\n"
			+ "JOIN thongtincanhan ON sinhvien.MaTT = thongtincanhan.MaTT\r\n"
			+"JOIN khoa ON sinhvien.MaKhoa = khoa.Makhoa\r\n"
			+"JOIN khoahoc ON sinhvien.MaKhoahoc = khoahoc.Makhoahoc\r\n"
			+ "WHERE sinhvien.Id_Tk = ? ;";
	private static final String Update_TTLL_SV ="UPDATE sinhvien\r\n"
			+ "JOIN thongtincanhan ON sinhvien.MaTT = thongtincanhan.MaTT\r\n"
			+ "SET thongtincanhan.Sodienthoai = ?, thongtincanhan.email = ? \r\n"
			+ "WHERE sinhvien.Id_Tk = ? ;";      
	private static final String Select_SV_QUENMK = "SELECT sinhvien.MSSV ,"
			+ " thongtincanhan.email,taikhoan.matkhau\r\n"
			+ "FROM sinhvien\r\n"
			+ "JOIN thongtincanhan ON sinhvien.MaTT = thongtincanhan.MaTT\r\n"
			+"JOIN taikhoan ON sinhvien.id_tk = taikhoan.id_tk\r\n"
			+ "WHERE sinhvien.mssv= ? and thongtincanhan.email =?  ;";	
	private static final String Select_SV_TT_SV_MSSV = "SELECT \r\n"
			+ "    sinhvien.MSSV, \r\n"
			+ "    thongtincanhan.HoTen, \r\n"
			+ "    thongtincanhan.NgaySinh, \r\n"
			+ "    thongtincanhan.GioiTinh, \r\n"
			+ "    thongtincanhan.Quequan, \r\n"
			+ "    sinhvien.TrangThai, \r\n"
			+ "    khoa.TenKhoa, \r\n"
			+ "    thongtincanhan.SoDienThoai, \r\n"
			+ "    thongtincanhan.Email, \r\n"
			+ "    khoahoc.TenKH, \r\n"
			+ "    khoahoc.NamBD, \r\n"
			+ "    khoahoc.NamKT\r\n"
			+ "FROM \r\n"
			+ "    sinhvien\r\n"
			+ "JOIN \r\n"
			+ "    thongtincanhan ON sinhvien.MaTT = thongtincanhan.MaTT\r\n"
			+ "JOIN \r\n"
			+ "    khoa ON sinhvien.MaKhoa = khoa.MaKhoa\r\n"
			+ "JOIN \r\n"
			+ "    khoahoc ON sinhvien.MaKhoaHoc = khoahoc.MaKhoaHoc\r\n"
			+ "WHERE \r\n"
			+ "    sinhvien.MSSV = ?;";
	public SinhVien selectTT_SV(String MSSV) {
		SinhVien sv = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_SV_TT_SV_MSSV);) {
            preparedStatement.setString(1, MSSV);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {	
            	sv = new SinhVien();
            	sv.setMSSV(MSSV);
            	sv.setHoTen(rs.getString(2));
            	sv.setNgaySinh(rs.getDate(3));
            	sv.setGioiTinh(rs.getString(4));
            	sv.setQueQuan(rs.getString(5));
            	sv.setTrangThai(rs.getString(6));
            	sv.setTenKhoa(rs.getString(7));
            	sv.setSoDienThoai(rs.getString(8));
            	sv.setEmail(rs.getNString(9));
            	sv.setTenKH(rs.getNString(10));
            	sv.setNamBD(rs.getInt(11));
            	sv.setNamKT(rs.getInt(12));
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return sv;
    }
	
	public boolean insertSV(String MSSV, String MaKhoa, String MaKH,String TrangThai,String HoTen,java.util.Date ngaySinh,
			String GioiTinh,String DiaChi,String QueQuan,String SoDienThoai,String Email) throws SQLException {
        System.out.println("lay oke");
        String matt= null;
        String id_tk = null;
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Insert_ttcn_sv,Statement.RETURN_GENERATED_KEYS)) {
       	 System.out.println(preparedStatement);
       	preparedStatement.setString(1, HoTen );
           preparedStatement.setDate(2,  (Date) ngaySinh);
           preparedStatement.setString(3,  GioiTinh);
           preparedStatement.setString(4,  DiaChi);
           preparedStatement.setString(5, QueQuan);
           preparedStatement.setString(6, SoDienThoai);
           preparedStatement.setString(7, Email );       
           System.out.println(preparedStatement);     
           preparedStatement.executeUpdate();
           ResultSet rs1 = preparedStatement.getGeneratedKeys();
           if (rs1.next()) {
               matt = rs1.getString(1);}
       } catch (SQLException exception) {
       	HandleExeption.printSQLException(exception);
       	return false;
       	
       }
        // try-with-resource statement will auto close the connection.
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Insert_tk_sv, Statement.RETURN_GENERATED_KEYS)) {
        	 System.out.println(preparedStatement);
            preparedStatement.setString(1, MSSV );         
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet rs2 = preparedStatement.getGeneratedKeys();
            if (rs2.next()) {
                id_tk = rs2.getString(1);
            }
        } catch (SQLException exception) {
        	HandleExeption.printSQLException(exception);
        	return false;
        }
        
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Insert_sv)) {
       	 System.out.println(preparedStatement);
           preparedStatement.setString(1, MSSV );
           preparedStatement.setString(2, MaKhoa );
           preparedStatement.setString(3, MaKH );
           preparedStatement.setString(4, matt );
           preparedStatement.setString(5, id_tk );
           preparedStatement.setString(6, TrangThai );
           System.out.println(preparedStatement);
           preparedStatement.executeUpdate();
       } catch (SQLException exception) {
       	HandleExeption.printSQLException(exception);
       	return false;
       }
        
        return true;
    }
	
	public SinhVien selectSV(String MSSV) {
		SinhVien sv = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_TT_SV_MSSV);) {
            preparedStatement.setString(1, MSSV);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {	
            	sv = new SinhVien();
            	sv.setMSSV(MSSV);
            	sv.setHoTen(rs.getNString(2));
            	sv.setGioiTinh(rs.getNString(3));
            	sv.setNgaySinh(rs.getDate(4));
            	sv.setMaKhoa(rs.getString(5));
            	sv.setMaKH(rs.getString(6));
            	sv.setEmail(rs.getString(7));
            	sv.setSoDienThoai(rs.getString(8));
            	sv.setDiaChi(rs.getNString(9));
            	sv.setQueQuan(rs.getNString(10));
            	sv.setTrangThai(rs.getNString(11));            
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return sv;
    }
	
	public List<SinhVien> selectallSV() {
		List<SinhVien> sinhviens = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_ALL_TT_SV);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String MSSV = rs.getString(1);
                String HoTen = rs.getNString(2);
                String gioitinh = rs.getNString(3);
                java.sql.Date NgaySinh   = rs.getDate(4);
                String Tenkhoa = rs.getNString(5);
                String TenKH = rs.getNString(6);
                String email = rs.getString(7);
                String sdt = rs.getString(8);
                String quequan = rs.getString(9);
                String trangthai = rs.getNString(10);             
                sinhviens.add(new SinhVien(MSSV,HoTen,gioitinh,NgaySinh,Tenkhoa,TenKH,email,sdt,quequan,trangthai));
                
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return sinhviens;
    }
	
	public void upDate_TT_SV(String MSSV, String MaKhoa, String MaKH,String TrangThai,String HoTen,Date NgaySinh,
			String GioiTinh,String DiaChi,String QueQuan,String SoDienThoai,String Email) {
        QTV qtv = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_SV);) {
            preparedStatement.setString(1, MSSV);
            preparedStatement.setString(2,  MaKhoa);
            preparedStatement.setString(3,  MaKH);
            preparedStatement.setString(4,  TrangThai);
            preparedStatement.setString(5,  MSSV);
     
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
            // Step 4: Process the ResultSet object.
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        
        try (Connection connection = JDBCUtil.getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(Update_TTCN_SV);) {
                
                
                preparedStatement.setString(1, HoTen);
                preparedStatement.setDate(2,  NgaySinh);
                preparedStatement.setString(3,  GioiTinh);
                preparedStatement.setString(4, DiaChi );
                preparedStatement.setString(5,  QueQuan);
                preparedStatement.setString(6,  SoDienThoai);
                preparedStatement.setString(7,  Email);
                preparedStatement.setString(8,  MSSV);
         
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                preparedStatement.executeUpdate();
                // Step 4: Process the ResultSet object.
               
            } catch (SQLException exception) {
                HandleExeption.printSQLException(exception);
            }
	}

	
		// TODO Auto-generated method stub
		
	public SinhVien selectSV_idtk(String id_tk) {
        SinhVien sv = new SinhVien();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_TT_SV_idTK);) {
            preparedStatement.setString(1, id_tk);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                sv.setMSSV(rs.getString(1)) ;
                sv.setHoTen(rs.getNString(2));
                java.sql.Date NgaySinh   = rs.getDate(3);
                sv.setNgaySinh(NgaySinh);
                sv.setDiaChi(rs.getNString(4)); 
                sv.setQueQuan(rs.getNString(5)); 
                sv.setSoDienThoai(rs.getString(6));
                sv.setEmail(rs.getString(7)); 
                sv.setTrangThai(rs.getNString(9));        
                sv.setGioiTinh(rs.getNString(8)); 
                sv.setTenKhoa(rs.getNString(10));
                sv.setTenKH(rs.getNString(11));
                sv.setNamBD(rs.getInt(12));
                sv.setNamKT(rs.getInt(13));
                System.out.println(sv.getDiaChi());
            }
            
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return sv;
    }
	
	public void upDate_ttll_SV(String id_tk, String sdt,String email) {
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_TTLL_SV);) {
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
	
	public SinhVien quenMK(String email, String mssv)
	{
		SinhVien sv = new SinhVien();
		 try (Connection connection = JDBCUtil.getConnection();
		            // Step 2:Create a statement using connection object
		            PreparedStatement preparedStatement = connection.prepareStatement(Select_SV_QUENMK);) {
		            preparedStatement.setString(1, mssv);
		            preparedStatement.setString(2, email);
		            System.out.println(preparedStatement);
		            // Step 3: Execute the query or update query
		            ResultSet rs = preparedStatement.executeQuery();

		            // Step 4: Process the ResultSet object.
		            if (rs.next()) {
		                sv.setMSSV(rs.getString(1)) ;		               
		                sv.setEmail(rs.getString(2)); 
		                TaiKhoan tk = new TaiKhoan();
		                tk.setMatkhau(rs.getString(3));
		                sv.setTaiKhoan(tk);
		                System.out.println(sv.getMSSV());
		            }
		            else {
		         	   sv = null;
		            }
		            
		        } catch (SQLException exception) {
		            HandleExeption.printSQLException(exception);
		        }
		 return sv;
	}
	
	
	
}
