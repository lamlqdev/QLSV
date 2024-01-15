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

public class CtsvDAO {

	public CtsvDAO() {
		// TODO Auto-generated constructor stub
	}
	private static final String Select_TT_CTSV_idTK = "SELECT CTSV.MaCTSV , thongtincanhan.HoTen, thongtincanhan.NgaySinh, thongtincanhan.Diachi, thongtincanhan.QueQuan, thongtincanhan.Sodienthoai, thongtincanhan.email,thongtincanhan.gioitinh, ctsv.TrangThai\r\n"
			+ "FROM CTSV\r\n"
			+ "JOIN thongtincanhan ON CTSV.MaTT = thongtincanhan.MaTT\r\n"
			+ "WHERE CTSV.Id_Tk = ? ;";
	private static final String Update_TTLL_CTSV ="UPDATE CTSV\r\n"
			+ "JOIN thongtincanhan ON CTSV.MaTT = thongtincanhan.MaTT\r\n"
			+ "SET thongtincanhan.Sodienthoai = ?, thongtincanhan.email = ? \r\n"
			+ "WHERE CTSV.Id_Tk = ? ;";      
	
	private static final String Select_ALL_TT_CTSV = "SELECT ctsv.MaCTSV, thongtincanhan.HoTen,thongtincanhan.GioiTinh, thongtincanhan.NgaySinh, thongtincanhan.Email,thongtincanhan.SoDienThoai,thongtincanhan.DiaChi,thongtincanhan.Quequan, ctsv.TrangThai\r\n"
			+ "FROM ctsv\r\n"
			+ "JOIN thongtincanhan ON ctsv.MaTT = thongtincanhan.MaTT\r\n ;";
	
	private static final String Update_CTSV ="UPDATE ctsv\r\n"
			+ "SET  TrangThai = ? \r\n"
			+ "WHERE MaCTSV = ? ;\r\n";
	private static final String Update_TTCN_CTSV = "UPDATE thongtincanhan\r\n"
			+ "SET HoTen = ? , NgaySinh = ? , GioiTinh=? ,DiaChi= ? ,QueQuan= ? ,SoDienThoai=? ,email=? \r\n"
			+ "WHERE MaTT = (SELECT MaTT FROM ctsv WHERE MaCTSV = ? );";      
	
	private static final String Insert_ttcn_ctsv = "INSERT INTO thongtincanhan ( HoTen, NgaySinh, GioiTinh,DiaChi, QueQuan,SoDienThoai,Email)\r\n"
			+ "VALUES (?, ? , ?, ?, ?, ?, ?);\r\n  ";
			
	
	private static final String Insert_tk_ctsv=  "INSERT INTO TaiKhoan (TenTk , MatKhau,PhanQuyen,TrangThai)\r\n"
			+ "VALUES ( ?, '123','ctsv','enable');\r\n";
			
	private static final String Insert_ctsv=  "INSERT INTO ctsv ( MaTT, Id_TK, TrangThai)\r\n"
			+ "VALUES (?, ?, ?);";
	
	private static final String Select_TT_CTSV_MaCTSV = "SELECT ctsv.MaCTSV, thongtincanhan.HoTen,thongtincanhan.GioiTinh, thongtincanhan.NgaySinh, thongtincanhan.Email,thongtincanhan.SoDienThoai,thongtincanhan.DiaChi,thongtincanhan.Quequan, ctsv.TrangThai\r\n"
			+ "FROM ctsv\r\n"
			+ "JOIN thongtincanhan ON ctsv.MaTT = thongtincanhan.MaTT\r\n"
			+"where ctsv.MaCTSV = ? ;";
	private static final String Select_CTSV_QUENMK = "SELECT ctsv.Mactsv ,"
			+ " thongtincanhan.email,taikhoan.matkhau\r\n"
			+ "FROM ctsv\r\n"
			+ "JOIN thongtincanhan ON ctsv.MaTT = thongtincanhan.MaTT\r\n"
			+"JOIN taikhoan ON ctsv.id_tk = taikhoan.id_tk\r\n"
			+ "WHERE ctsv.mactsv= ? and thongtincanhan.email =?  ;";	
	
	public CTSV selectCTSV_IDTK(String id_tk) {
		CTSV ctsv = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_TT_CTSV_idTK);) {
            preparedStatement.setString(1, id_tk);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String MaCTSV = rs.getString(1);
                String HoTen = rs.getNString(2);
                java.sql.Date NgaySinh   = rs.getDate(3);
                String diachi = rs.getNString(4);
                String quequan = rs.getNString(5);
                String sdt = rs.getString(6);
                String email = rs.getString(7);
                String trangthai = rs.getNString(9);             
                String gioitinh = rs.getNString(8);
                ctsv = new CTSV(MaCTSV, HoTen,gioitinh, NgaySinh,  email,sdt,diachi, quequan,trangthai);
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return ctsv;
    }
	
	public void upDate_ttll_CTSV(String id_tk, String sdt,String email) {
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_TTLL_CTSV);) {
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
	
	public String  insertCTSV(String TrangThai,String HoTen,java.util.Date ngaySinh,
			String GioiTinh,String DiaChi,String QueQuan,String SoDienThoai,String Email) throws SQLException {
		
        System.out.println("lay oke");
        String matt= null;
        String id_tk = null;
        String mactsv = null;
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Insert_ttcn_ctsv,Statement.RETURN_GENERATED_KEYS)) {
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
       }
        int newMaCTSV =0;
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(maCTSV) AS maxMaCTSV FROM ctsv")) {
          	 System.out.println(preparedStatement);
          	 ResultSet rs = preparedStatement.executeQuery();
             if (rs.next()) {
                 int maxMaCTSV = rs.getInt("maxMaCTSV");
                 // Tăng giá trị lớn nhất lên 1 để sử dụng
                  newMaCTSV = maxMaCTSV + 1;
             }} catch (SQLException exception) {
          	HandleExeption.printSQLException(exception);
          }
       
        // try-with-resource statement will auto close the connection.
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Insert_tk_ctsv, Statement.RETURN_GENERATED_KEYS)) {
        	 System.out.println(preparedStatement);
            preparedStatement.setString(1,"ctsv"+ newMaCTSV );         
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet rs2 = preparedStatement.getGeneratedKeys();
            if (rs2.next()) {
                id_tk = rs2.getString(1);
            }
        } catch (SQLException exception) {
        	HandleExeption.printSQLException(exception);
        }
        
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(Insert_ctsv, Statement.RETURN_GENERATED_KEYS)) {
       	 System.out.println(preparedStatement);
           preparedStatement.setString(1, matt );
           preparedStatement.setString(2, id_tk );
           preparedStatement.setString(3, TrangThai );
           System.out.println(preparedStatement);
           preparedStatement.executeUpdate();
           ResultSet rs3 = preparedStatement.getGeneratedKeys();
           if (rs3.next()) {
               mactsv = rs3.getString(1);}
       } catch (SQLException exception) {
       	HandleExeption.printSQLException(exception);
       }
       return mactsv;
    }
	
	public CTSV selectCTSV(String MaCTSV) {
		CTSV sv = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_TT_CTSV_MaCTSV);) {
            preparedStatement.setString(1, MaCTSV);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {	
            	sv = new CTSV();
            	sv.setMaCTSV(MaCTSV);
            	sv.setHoTen(rs.getNString(2));
            	sv.setGioiTinh(rs.getNString(3));
            	sv.setNgaySinh(rs.getDate(4));          	
            	sv.setEmail(rs.getString(5));
            	sv.setSoDienThoai(rs.getString(6));
            	sv.setDiaChi(rs.getNString(7));
            	sv.setQueQuan(rs.getNString(8));
            	sv.setTrangThai(rs.getNString(9));            
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return sv;
    }
	
	public List<CTSV> selectallCTSV() {
		List<CTSV> sinhviens = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Select_ALL_TT_CTSV);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String MaCTSV = rs.getString(1);
                String HoTen = rs.getNString(2);
                String gioitinh = rs.getNString(3);
                java.sql.Date NgaySinh   = rs.getDate(4);
                String email = rs.getString(5);
                String sdt = rs.getString(6);
                String diachi = rs.getString(7);
                String quequan = rs.getString(8);
                String trangthai = rs.getNString(9);             
                sinhviens.add(new CTSV(MaCTSV,HoTen,gioitinh,NgaySinh,email,sdt,diachi,quequan,trangthai));
                
            }
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        return sinhviens;
    }
	
	public void upDate_TT_CTSV(String MaCTSV,String TrangThai,String HoTen,Date NgaySinh,
			String GioiTinh,String DiaChi,String QueQuan,String SoDienThoai,String Email) {
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(Update_CTSV);) {
            preparedStatement.setString(1,  TrangThai);
            preparedStatement.setString(2,  MaCTSV);
     
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
            // Step 4: Process the ResultSet object.
           
        } catch (SQLException exception) {
            HandleExeption.printSQLException(exception);
        }
        
        try (Connection connection = JDBCUtil.getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(Update_TTCN_CTSV);) {               
                preparedStatement.setString(1, HoTen);
                preparedStatement.setDate(2,  NgaySinh);
                preparedStatement.setString(3,  GioiTinh);
                preparedStatement.setString(4, DiaChi );
                preparedStatement.setString(5,  QueQuan);
                preparedStatement.setString(6,  SoDienThoai);
                preparedStatement.setString(7,  Email);
                preparedStatement.setString(8,  MaCTSV);
         
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                preparedStatement.executeUpdate();
                // Step 4: Process the ResultSet object.
               
            } catch (SQLException exception) {
                HandleExeption.printSQLException(exception);
            }
	}
	
	public CTSV quenMK(String email, String mssv)
	{
		CTSV ctsv = new CTSV();
		 try (Connection connection = JDBCUtil.getConnection();
		            // Step 2:Create a statement using connection object
		            PreparedStatement preparedStatement = connection.prepareStatement(Select_CTSV_QUENMK);) {
		            preparedStatement.setString(1, mssv);
		            preparedStatement.setString(2, email);
		            System.out.println(preparedStatement);
		            // Step 3: Execute the query or update query
		            ResultSet rs = preparedStatement.executeQuery();

		            // Step 4: Process the ResultSet object.
		            if (rs.next()) {
		            	ctsv.setMaCTSV(rs.getString(1)) ;		               
		            	ctsv.setEmail(rs.getString(2)); 
		                TaiKhoan tk = new TaiKhoan();
		                tk.setMatkhau(rs.getString(3));
		                ctsv.setTaiKhoan(tk);
		                System.out.println(ctsv.getEmail());
		            }
		            else {
		            	ctsv = null;
		            }
		            
		        } catch (SQLException exception) {
		            HandleExeption.printSQLException(exception);
		        }
		 return ctsv;
	}
}
