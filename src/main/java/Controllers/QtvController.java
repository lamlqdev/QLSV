package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import com.google.common.collect.Table.Cell;

import Models.CTSV;
import Models.Excel_alert;
import Models.Khoa;
import Models.KhoaHoc;
import Models.QTV;
import Models.SinhVien;
import Models.TaiKhoan;
import Models.ThongBao;
import Models.TruyCap;
import Util.EmailUtility;
import DAO.*;

/**
 * Servlet implementation class QtvController
 */
@WebServlet("/Qtv")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class QtvController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
    private String port;
    private String user;
    private String pass;
	private QtvDAO qtvDAO;
	private SinhVienDAO svDAO;
	private CtsvDAO ctsvDAO;
	private KhoaDAO khoaDAO;
	private KhoaHocDAO khoahocDAO;
	private TruyCapDAO truycapDAO;
	private ThongBaoDAO thongbaoDAO;
    public void init() {
    	
    	ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    	qtvDAO = new QtvDAO();
    	svDAO = new SinhVienDAO();
    	ctsvDAO = new CtsvDAO();
    	khoaDAO = new KhoaDAO();
    	khoahocDAO = new KhoaHocDAO();
    	truycapDAO = new TruyCapDAO();
    	thongbaoDAO = new ThongBaoDAO();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QtvController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getPathInfo();

		request.setCharacterEncoding("UTF-8");
        try {
            switch (action) {
                case "/info":
                	showttQTV(request, response);
                    break;  
                case "/updateTT":
                	update_TT(request, response);
                    break;  
                case "/listSV":
                	listSV(request, response);
                    break;  
                    
                case "/update_TT_SV":
                	update_TT_SV(request, response);
                	break;
                	
                case "/insert_SV":
                	insert_SV(request, response);
                	break;
                case "/insert_SV_excel":
                	insert_SV_excel(request, response);
                	break;
                	
                case "/showtt_SV":
                	showtt_SV(request, response);
                	break;
                
                case "/showtt_CTSV":
                	showtt_CTSV(request, response);
                	break;
                	
                case "/listCTSV":
                	listCTSV(request, response);
                    break;  
                    
                case "/update_TT_CTSV":
                	update_TT_CTSV(request, response);
                	break;
                	
                case "/insert_CTSV":
                	insert_CTSV(request, response);
                	break;
                case "/showtt_Khoa":
                	showtt_Khoa(request, response);
                	break;
                	
                case "/listKhoa":
                	listKhoa(request, response);
                    break;  
                    
                case "/update_Khoa":
                	update_Khoa(request, response);
                	break;
                	
                case "/insert_Khoa":
                	insert_Khoa(request, response);
                	break;
                case "/showtt_KH":
                	showtt_KH(request, response);
                	break;
                	
                case "/listKH":
                	listKH(request, response);
                    break;  
                    
                case "/update_KH":
                	update_KH(request, response);
                	break;
                	
                case "/insert_KH":
                	insert_KH(request, response);
                	break;
                case "/slTruyCap":
                	showtt_SLtruycap(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	private void showttQTV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
		String idTk= acc.getIdTk();
        QTV qtv = qtvDAO.selectQTV(idTk);
        String errMsgString = (String)request.getAttribute("errMsg");
        System.out.println(errMsgString);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/TTCaNhanQTV.jsp");
        request.setAttribute("errMsgg", errMsgString);
        request.setAttribute("ttQTV", qtv);
        dispatcher.forward(request, response);
    }
	
	private void update_TT(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
		String idTk= acc.getIdTk();
		String std = request.getParameter("phoneInput");
		String email = request.getParameter("emailInput");
       qtvDAO.upDate_ttll_QTV(idTk, std, email);
       	request.setAttribute("errMsg", "Bạn đã cập nhật thành công");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Qtv/info");
		dispatcher.forward(request, response);
    }
	
	private void listSV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	
        List < SinhVien > listSV = svDAO.selectallSV();
        request.setAttribute("listSV", listSV);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/QuanlySV_QTV.jsp");
        dispatcher.forward(request, response);
    }
	
	private void showtt_SV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
		String MSSV = request.getParameter("id");
        SinhVien sv = svDAO.selectSV(MSSV);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditSV_QTV.jsp");
        request.setAttribute("SinhVien", sv);
        List < KhoaHoc > listKH = khoahocDAO.selectallKHOAHOC();
	     request.setAttribute("listKH", listKH);
		 List < Khoa > listKhoa = khoaDAO.selectallKHOA();
	     request.setAttribute("listKhoa", listKhoa);
        dispatcher.forward(request, response);
    }
	
	private void update_TT_SV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		
		String MSSV= request.getParameter("mssv");
		String MaKhoa= request.getParameter("makhoa");
		String MaKH=request.getParameter("makh");
		String TrangThai=request.getParameter("trangthai");
		String HoTen=request.getParameter("hoten");
		String ngaysinh = request.getParameter("ngaysinh");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date ngaySinh = new java.sql.Date(dateFormat.parse(ngaysinh).getTime());
		
		String GioiTinh=request.getParameter("gioitinh");
		String DiaChi=request.getParameter("diachi");
		String QueQuan=request.getParameter("quequan");
		String SoDienThoai=request.getParameter("sodienthoai");
		String Email=request.getParameter("email");
		svDAO.upDate_TT_SV(MSSV, MaKhoa, MaKH, TrangThai, HoTen, ngaySinh, GioiTinh, DiaChi, QueQuan, SoDienThoai, Email);
		request.setAttribute("errMsg", "Bạn đã cập nhật thành công");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Qtv/showtt_SV?id="+MSSV);
		 dispatcher.forward(request, response);
    }
	
	private void insert_SV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		
		String MSSV= request.getParameter("mssv");
		String MaKhoa= request.getParameter("makhoa");
		String MaKH=request.getParameter("makh");
		String TrangThai=request.getParameter("trangthai");
		String HoTen=request.getParameter("hoten");
		String ngaysinh = request.getParameter("ngaysinh");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date ngaySinh = new java.sql.Date(dateFormat.parse(ngaysinh).getTime());
		
		String GioiTinh=request.getParameter("gioitinh");
		String DiaChi=request.getParameter("diachi");
		String QueQuan=request.getParameter("quequan");
		String SoDienThoai=request.getParameter("sodienthoai");
		String Email=request.getParameter("email");
		if(svDAO.selectSV(MSSV)!=null)
		{
			request.setAttribute("errMsg", "MSSV:"+MSSV+" đã tồn tại"  );
			 List < KhoaHoc > listKH = khoahocDAO.selectallKHOAHOC();
		     request.setAttribute("listKH", listKH);
			 List < Khoa > listKhoa = khoaDAO.selectallKHOA();
		     request.setAttribute("listKhoa", listKhoa);   
		     request.setAttribute("makhoa", MaKhoa);
		     request.setAttribute("makh", MaKH);
		     request.setAttribute("trangthai", TrangThai);
		     request.setAttribute("hoten",HoTen );
		     request.setAttribute("ngaysinh", ngaySinh);
		     request.setAttribute("gioitinh", GioiTinh);
		     request.setAttribute("diachi", DiaChi);
		     request.setAttribute("quequan", QueQuan);
		     request.setAttribute("sdt", SoDienThoai);
		     request.setAttribute("email", Email);
		     RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditSV_QTV.jsp");
		     dispatcher.forward(request, response);
		}
		else {
			svDAO.insertSV(MSSV, MaKhoa, MaKH, TrangThai, HoTen, ngaySinh, GioiTinh, DiaChi, QueQuan, SoDienThoai, Email);	
			request.setAttribute("errMsg", "Bạn đã thêm sinh viên thành công \n Đã gửi email tên tài khoản và mật khẩu tới sinh viên");
			try {
				String tieuDe="Tai khoản sinh viên của bạn đã được tạo";
				String noiDung="Tên tài khoản : "+MSSV + " \n Mật khẩu : 123";
				sendemail(request,response,tieuDe,noiDung,Email);
			} catch (SQLException | ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Qtv/showtt_SV?id="+MSSV);
			 dispatcher.forward(request, response);
		}
		
    }
	
	private void insert_SV_excel(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		//System.out.println(request.getPart("excelFile"));
		// Code Ä‘á»�c dá»¯ liá»‡u tá»« Excel
		request.removeAttribute("listERR"); 
		request.removeAttribute("errMsg"); 
		int i=0;
		int x=0;
		List<Excel_alert> exs = new ArrayList < > ();
		try {
            Part filePart = request.getPart("excelFile");
            InputStream fileContent = filePart.getInputStream();

            XSSFWorkbook workbook = new XSSFWorkbook(fileContent);
            XSSFSheet sheet = workbook.getSheetAt(0); // Láº¥y sheet Ä‘áº§u tiÃªn
            DataFormatter dataFormatter = new DataFormatter();

            for (Row row : sheet) {
                // Ä�á»�c tá»«ng dÃ²ng trong tá»‡p Excel vÃ  láº¥y thÃ´ng tin cá»§a sinh viÃªn
            	
            	    // ... (cÃ¡c dÃ²ng code khÃ¡c)
            		i++;
            	    String MSSV = dataFormatter.formatCellValue(row.getCell(0));          	   
            	    String SoDienThoai = dataFormatter.formatCellValue(row.getCell(8));
            	    String Email = dataFormatter.formatCellValue(row.getCell(9));  	  
                
                if(svDAO.selectSV(MSSV)!=null || isValidEmail( Email)==false ||  isValidPhoneNumber(SoDienThoai)==false)
        		{   
                	x++;
                	if(svDAO.selectSV(MSSV)!=null) {
                		Excel_alert excel_alert = new Excel_alert();
                    	excel_alert.setDong(Integer.toString(i));
                		excel_alert.setNoidung("Mssv " +MSSV +" đã tồn tại");
                		exs.add(excel_alert);
                		}
                	if(isValidEmail( Email)==false ){
                		Excel_alert excel_alert = new Excel_alert();
                    	excel_alert.setDong(Integer.toString(i));
                		excel_alert.setNoidung("Email "+ Email+" không đúng định dạng");
                		exs.add(excel_alert);
                	}
                	if(isValidPhoneNumber(SoDienThoai)==false ){
                		Excel_alert excel_alert = new Excel_alert();
                    	excel_alert.setDong(Integer.toString(i));
                		excel_alert.setNoidung("Số đth "+ SoDienThoai+" không đúng định dạng");
                		exs.add(excel_alert);
                	}
        	    	continue ;
        		}                
            }
            if(x==0) 
            {
            	for (Row row : sheet) {
                    // Ä�á»�c tá»«ng dÃ²ng trong tá»‡p Excel vÃ  láº¥y thÃ´ng tin cá»§a sinh viÃªn
                	
                	    // ... (cÃ¡c dÃ²ng code khÃ¡c)
                		i++;
                	    String MSSV = dataFormatter.formatCellValue(row.getCell(0));            	    
                	    String HoTen = dataFormatter.formatCellValue(row.getCell(1));
                	    String ngaysinh = dataFormatter.formatCellValue(row.getCell(2));
                	    String MaKhoa = dataFormatter.formatCellValue(row.getCell(3));
                	    String MaKH = dataFormatter.formatCellValue(row.getCell(4));
                	    String GioiTinh = dataFormatter.formatCellValue(row.getCell(5));
                	    String DiaChi = dataFormatter.formatCellValue(row.getCell(6));
                	    String QueQuan = dataFormatter.formatCellValue(row.getCell(7));
                	    String SoDienThoai = dataFormatter.formatCellValue(row.getCell(8));
                	    String Email = dataFormatter.formatCellValue(row.getCell(9));
                	    String TrangThai = dataFormatter.formatCellValue(row.getCell(10));                                
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.sql.Date ngaySinh = new java.sql.Date(dateFormat.parse(ngaysinh).getTime());                            
                    // ChÃ¨n dá»¯ liá»‡u vÃ o cÆ¡ sá»Ÿ dá»¯ liá»‡u
                    svDAO.insertSV(MSSV, MaKhoa, MaKH, TrangThai, HoTen, ngaySinh, GioiTinh, DiaChi, QueQuan, SoDienThoai, Email);         
                }      	
            }
            

            workbook.close();
            fileContent.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		request.setAttribute("listERR", exs); 
		if(x>0) {
			request.setAttribute("errMsg", "Bạn đã thêm sinh viên từ file excel không thành công  "); 
		}
		else {request.setAttribute("succMsg", "Bạn đã thêm sinh viên từ file excel thành công  "); }
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/import_excel.jsp");
        dispatcher.forward(request, response);
    }
	
	public boolean isValidEmail(String email) {
	    String regex = "^(.+)@(.+)$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}
	
	public boolean isValidPhoneNumber(String phone) {
	    // Loại bỏ tất cả ký tự không phải số để kiểm tra độ dài
	    String cleanedPhone = phone.replaceAll("[^0-9]", "");
	    return cleanedPhone.length() == 10; // Kiểm tra xem có đủ 10 số hay không
	}
	
	private void listCTSV(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		    	
		        List < CTSV > listCTSV = ctsvDAO.selectallCTSV();
		        request.setAttribute("listCTSV", listCTSV);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/QuanlyCTSV_QTV.jsp");
		        dispatcher.forward(request, response);
		    }
			
	private void showtt_CTSV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
		String CTSV = request.getParameter("id");
        CTSV ctsv = ctsvDAO.selectCTSV(CTSV);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditCTSV_QTV.jsp");
        request.setAttribute("CTSV", ctsv);
        dispatcher.forward(request, response);
    }
	
	private void update_TT_CTSV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		
		String CTSV= request.getParameter("id");
		String TrangThai=request.getParameter("trangthai");
		String HoTen=request.getParameter("hoten");
		String ngaysinh = request.getParameter("ngaysinh");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date ngaySinh = new java.sql.Date(dateFormat.parse(ngaysinh).getTime());
		
		String GioiTinh=request.getParameter("gioitinh");
		String DiaChi=request.getParameter("diachi");
		String QueQuan=request.getParameter("quequan");
		String SoDienThoai=request.getParameter("sodienthoai");
		String Email=request.getParameter("email");
		
		ctsvDAO.upDate_TT_CTSV(CTSV, TrangThai, HoTen, ngaySinh, GioiTinh, DiaChi, QueQuan, SoDienThoai, Email);
		request.setAttribute("errMsg", "Bạn đã cập nhật thông tin CTSV thành công  "); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Qtv/showtt_CTSV?id="+CTSV);
        dispatcher.forward(request, response);
		
    }
	
	private void insert_CTSV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		String TrangThai=request.getParameter("trangthai");
		String HoTen=request.getParameter("hoten");
		String ngaysinh = request.getParameter("ngaysinh");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date ngaySinh = new java.sql.Date(dateFormat.parse(ngaysinh).getTime());
		
		String GioiTinh=request.getParameter("gioitinh");
		String DiaChi=request.getParameter("diachi");
		String QueQuan=request.getParameter("quequan");
		String SoDienThoai=request.getParameter("sodienthoai");
		String Email=request.getParameter("email");
		String mactsv=ctsvDAO.insertCTSV( TrangThai, HoTen, ngaySinh, GioiTinh, DiaChi, QueQuan, SoDienThoai, Email);
		request.setAttribute("errMsg", "Bạn đã thêm CTSV thành công  "); 
		try {
			String tieuDe="Tai khoản sinh viên của bạn đã được tạo";
			String noiDung="Tên tài khoản : ctsv"+mactsv + " \n Mật khẩu : 123";
			sendemail(request,response,tieuDe,noiDung,Email);
		} catch (SQLException | ServletException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Qtv/showtt_CTSV?id="+mactsv);
        dispatcher.forward(request, response);
		
    }
	
	private void listKhoa(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		    	
		        List < Khoa > listKhoa = khoaDAO.selectallKHOA();
		        request.setAttribute("listKhoa", listKhoa);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/QuanlyKhoa_QTV.jsp");
		        dispatcher.forward(request, response);
		    }
			
	private void showtt_Khoa(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
		String maKhoa = request.getParameter("id");
        Khoa khoa = khoaDAO.selectKhoa(maKhoa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditKhoa_QTV.jsp");
        request.setAttribute("Khoa", khoa);
        dispatcher.forward(request, response);
    }
	
	private void update_Khoa(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException, ParseException {
		
		String maKhoa= request.getParameter("id");
		String TrangThai=request.getParameter("trangthai");
		String HoTen=request.getParameter("tenkhoa");
		
		khoaDAO.upDate_Khoa(maKhoa, TrangThai, HoTen);
		request.setAttribute("errMsg", "Bạn đã cập nhật thông tin khoa thành công  "); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Qtv/showtt_Khoa?id="+maKhoa);
		dispatcher.forward(request, response);
    }
	
	private void insert_Khoa(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		String TrangThai=request.getParameter("trangthai");
		String tenKhoa=request.getParameter("tenkhoa");
		
		String maKhoa= khoaDAO.insertKhoa( TrangThai, tenKhoa);
		request.setAttribute("errMsg", "Bạn đã thêm khoa thành công  "); 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Qtv/showtt_Khoa?id="+maKhoa);
		dispatcher.forward(request, response);
    }
	
	private void listKH(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		    	
		        List < KhoaHoc > listKH = khoahocDAO.selectallKHOAHOC();
		        request.setAttribute("listKH", listKH);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/QuanlyKhoaHoc_QTV.jsp");
		        dispatcher.forward(request, response);
		    }
			
	private void showtt_KH(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
		String maKH = request.getParameter("id");
        KhoaHoc KH = khoahocDAO.selectKH(maKH);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditKhoaHoc_QTV.jsp");
        request.setAttribute("KhoaHoc", KH);
        dispatcher.forward(request, response);
    }
	
	
	
	private void update_KH(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException, ParseException {
		
		String maKhoa= request.getParameter("id");
		String NamBD=request.getParameter("nambd");
		String NamKT=request.getParameter("namkt");
		String TenKH=request.getParameter("tenkh");
		
		khoahocDAO.upDate_KH(maKhoa, TenKH,NamBD,NamKT);
		request.setAttribute("errMsg", "Bạn đã cập nhật thông tin khóa học thành công  "); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Qtv/showtt_KH?id="+maKhoa);
		dispatcher.forward(request, response);
    }
	
	private void insert_KH(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		String NamBD=request.getParameter("nambd");
		String NamKT=request.getParameter("namkt");
		String TenKH=request.getParameter("tenkh");
		String maKH= khoahocDAO.insertKH( TenKH,NamBD,NamKT);
		request.setAttribute("errMsg", "Bạn đã thêm khóa học thành công"); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Qtv/showtt_KH?id="+maKH);
		dispatcher.forward(request, response);
    }
	
	private void showtt_SLtruycap(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		String ngaybd = request.getParameter("ngaybd");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date ngayBD = new java.sql.Date(dateFormat.parse(ngaybd).getTime());
		
		String ngaykt = request.getParameter("ngaykt");
		java.sql.Date ngayKT = new java.sql.Date(dateFormat.parse(ngaykt).getTime());
		
        TruyCap truycap = truycapDAO.count_SL_truycap(ngayBD, ngayKT);
        List < ThongBao > listTB = thongbaoDAO.countTB_QTV(ngayBD, ngayKT);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ThongkeWeb_QTV.jsp");
        request.setAttribute("ngaybd",ngayBD);
        request.setAttribute("ngaykt", ngayKT);
        request.setAttribute("listTB", listTB);
        request.setAttribute("TruyCap", truycap);
        dispatcher.forward(request, response);
    }
	
	 private void sendemail(HttpServletRequest request, HttpServletResponse
			  response,String Tieude,String Noidung, String email) throws SQLException, ServletException,
			  IOException, ParseException {
			  
			  String subject = Tieude;
			  String content =Noidung;
			 
			  String resultMessage = "";
			  
			  try { EmailUtility.sendEmail(host, port, user, pass, email, subject,
			  content); resultMessage = "The e-mail was sent successfully"; } catch
			  (Exception ex) { ex.printStackTrace(); resultMessage =
			  "There were an error: " + ex.getMessage(); } finally {
			  request.setAttribute("Message", resultMessage);
			  } }

}
