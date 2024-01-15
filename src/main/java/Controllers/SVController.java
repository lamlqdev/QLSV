package Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

import Models.CTSV;
import Models.Khoa;
import Models.KhoaHoc;
import Models.LoaiGiay;
import Models.QTV;
import Models.SinhVien;
import Models.TaiKhoan;
import Models.DKGiayXacNhan;
import Models.DKXNNganhNghe;
import Models.LyDo;
import Models.CTXH;
import Models.DKCTXH;
import Models.ThongBao;
import Util.EmailUtility;
import DAO.*;

/**
 * Servlet implementation class QtvController
 */
@WebServlet("/SV")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)

public class SVController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SinhVienDAO svDAO;
	private KhoaDAO khoaDAO;
	private KhoaHocDAO khoahocDAO;
	private LoaiGiayDAO loaigiayDAO;
	private DKGXNDAO dkgxnDAO;
	private DKXNNNDAO dkxnnnDAO;
	private LyDoDAO lydoDAO;
	private CTXHDAO ctxhDAO;
	private DKCTXHDAO dkctxhDAO;
	private ThongBaoDAO tbDAO;
    public void init() {
    	
    	
    	svDAO = new SinhVienDAO();
    	khoaDAO = new KhoaDAO();
    	khoahocDAO = new KhoaHocDAO();
    	loaigiayDAO = new LoaiGiayDAO();
    	dkgxnDAO = new DKGXNDAO();
    	dkxnnnDAO = new DKXNNNDAO();
    	lydoDAO = new LyDoDAO();
    	ctxhDAO = new CTXHDAO();
    	dkctxhDAO = new DKCTXHDAO();
    	tbDAO = new ThongBaoDAO();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SVController() {
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
                	showttSV(request, response);
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
                	break;
                case "/dk_DKGXN":
                	insert_dkGXN(request, response);
                	break;
                case "/DKGXN":
                	show_DKGXN(request, response);
                	break;
                case "/dk_DKXNNN":
                	insert_dkXNNN(request, response);
                	break;
                case "/DKXNNN":
                	show_DKXNNN(request, response);
                	break;
                case "/CTXH":
                	show_CTXH(request,response);
                	break;
                case "/dk_CTXH":
                	insert_dkCTXH(request, response);
                	break;
                case "/thongbao":
                	show_TB(request, response);
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
	
	private void show_DKGXN(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ParseException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String MSSV= acc.getTenTk();
				List <LoaiGiay> listLG = loaigiayDAO.selectallLoaiGiay();
			    request.setAttribute("listLG", listLG);
			    List <DKGiayXacNhan> listDKGXN = dkgxnDAO.selectDKGXN(MSSV);
			    request.setAttribute("listDKGXN", listDKGXN);
			    String status = (String) session.getAttribute("status");
			    if (status != null) {
			        session.setAttribute("status", status);
			    }
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_SV/TrangDKGXN.jsp");
		        dispatcher.forward(request, response);
	}
	
	private void insert_dkGXN(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException, ParseException {
				HttpSession session = request.getSession();
				TaiKhoan tk = (TaiKhoan)session.getAttribute("Acc");
				String idTk = tk.getIdTk();
				String MSSV = tk.getTenTk();
				System.out.println(tk.getMatkhau());
				String TenDichVu = request.getParameter("tenLoaiGiay");
				String MaLoaiGiay = request.getParameter("loaigiay");
				String SoLuong = request.getParameter("soluong");
				String TrangThai = "Chờ duyệt";
				LocalDateTime ThoiGianDK = LocalDateTime.now();
				
				if(dkgxnDAO.insertDKGXN(TenDichVu,SoLuong,MaLoaiGiay,MSSV,ThoiGianDK,TrangThai))
				{
					System.out.println("thanh cong");
					request.getSession().setAttribute("status", "success");
			    } else {
			        request.getSession().setAttribute("status", "failure");
			    }
		      
//		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_SV/TrangDKGXN.jsp");
//				dispatcher.forward(request, response);
//				show_DKGXN(request, response);
				response.sendRedirect(request.getContextPath() + "/SV/DKGXN");
		    }
	
	private void show_DKXNNN(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ParseException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String MSSV= acc.getTenTk();
				List <LyDo> listLD = lydoDAO.selectallLyDo();
			    request.setAttribute("listLD", listLD);
			    for (LyDo ld : listLD) {
			        System.out.println(ld.getTenLyDo());
			    }
			    List <DKXNNganhNghe> listDKXNNN = dkxnnnDAO.selectDKXNNN(MSSV);
			    request.setAttribute("listDKXNNN", listDKXNNN);
			    SinhVien sv = svDAO.selectTT_SV(MSSV);
			    request.setAttribute("SinhVien", sv);
			    String status = (String) session.getAttribute("status");
			    if (status != null) {
			        session.setAttribute("status", status);
			    }
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_SV/TrangDKXNnghe.jsp");
		        dispatcher.forward(request, response);
	}
	
	private void insert_dkXNNN(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException, ParseException {
				HttpSession session = request.getSession();
				TaiKhoan tk = (TaiKhoan)session.getAttribute("Acc");
				String idTk = tk.getIdTk();
				String MSSV = tk.getTenTk();
				System.out.println(tk.getMatkhau());
				String TenDichVu = "Đăng ký xác nhận ngành nghề";
				String MaLyDo = request.getParameter("lydo");
				String HocKy = request.getParameter("hocky");
				String NamHoc = request.getParameter("namhoctext");
				String TrangThai = "Chưa duyệt";
				LocalDateTime ThoiGianDK = LocalDateTime.now();
				
				if(dkxnnnDAO.insertDKXNNN(TenDichVu,NamHoc,HocKy,MSSV,MaLyDo,ThoiGianDK,TrangThai))
				{
					System.out.println("thanh cong");
					request.getSession().setAttribute("status", "success");
			    } else {
			    	System.out.println("thatbai");
			        request.getSession().setAttribute("status", "failure");
			    }
		      
//		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_SV/TrangDKGXN.jsp");
//				dispatcher.forward(request, response);
//				show_DKGXN(request, response);
				response.sendRedirect(request.getContextPath() + "/SV/DKXNNN");
		    }
	
	private void showttSV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
		if(acc == null)
		{
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		String MSSV= acc.getTenTk();
        SinhVien sv = svDAO.selectTT_SV(MSSV);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_SV/TTCaNhanSV.jsp");
        request.setAttribute("ttSV", sv);
        dispatcher.forward(request, response);
    }
	
	private void show_CTXH(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ParseException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String MSSV = acc.getTenTk();
				List <CTXH> listCTXH = ctxhDAO.selectCTXH_TimeHD();
			    request.setAttribute("listCTXH", listCTXH);
			    List <DKCTXH> listDKCTXH = dkctxhDAO.selectDKCTXH_MSSV(MSSV);
			    request.setAttribute("listDKCTXH", listDKCTXH);
			    String status = (String) session.getAttribute("status");
			    if (status != null) {
			        session.setAttribute("status", status);
			    }
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_SV/TrangDKCTXH.jsp");
		        dispatcher.forward(request, response);
	}
	
	private void insert_dkCTXH(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException, ParseException {
				HttpSession session = request.getSession();
				TaiKhoan tk = (TaiKhoan)session.getAttribute("Acc");
				String idTk = tk.getIdTk();
				String MSSV = tk.getTenTk();
				System.out.println(tk.getMatkhau());
				String TenDichVu = "Đăng ký CTXH: " + request.getParameter("tenHD");
				String MaHD = request.getParameter("maHD");
				String TrangThai = "Chưa duyệt";
				LocalDateTime ThoiGianDK = LocalDateTime.now();
				
				if(dkctxhDAO.insertDKCTXH(TenDichVu,MaHD,MSSV,ThoiGianDK,TrangThai))
				{
					System.out.println("thanh cong");
					request.getSession().setAttribute("status", "success");
			    } else {
			    	System.out.println("thatbai");
			        request.getSession().setAttribute("status", "failure");
			    }
		      
//		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_SV/TrangDKGXN.jsp");
//				dispatcher.forward(request, response);
//				show_DKGXN(request, response);
				response.sendRedirect(request.getContextPath() + "/SV/CTXH");
		    }
	
	private void show_TB(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ParseException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String idTk = acc.getIdTk();
				String MSSV = acc.getTenTk();
				List <ThongBao> listTB = tbDAO.selectalltbSV(idTk);
			    request.setAttribute("listTB", listTB);
			    String ndTB = request.getParameter("ndPhanHoi");
			    request.setAttribute("ndTB", ndTB);
			    String status = (String) session.getAttribute("status");
			    if (status != null) {
			        session.setAttribute("status", status);
			    }
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_SV/TrangTBSV.jsp");
		        dispatcher.forward(request, response);
	}
	
	private void update_TT(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
//		HttpSession session = request.getSession();
//		TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
//		String idTk= acc.getIdTk();
//		String std = request.getParameter("phoneInput");
//		String email = request.getParameter("emailInput");
//       qtvDAO.upDate_ttll_QTV(idTk, std, email);
//      
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/Qtv/info");
//		dispatcher.forward(request, response);
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
		
	     
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditSV_QTV.jsp");
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
		svDAO.insertSV(MSSV, MaKhoa, MaKH, TrangThai, HoTen, ngaySinh, GioiTinh, DiaChi, QueQuan, SoDienThoai, Email);	
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditSV_QTV.jsp");
		dispatcher.forward(request, response);
    }
	
	private void insert_SV_excel(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
//		//System.out.println(request.getPart("excelFile"));
//		// Code Ä‘á»�c dá»¯ liá»‡u tá»« Excel
//		try {
//            Part filePart = request.getPart("excelFile");
//            InputStream fileContent = filePart.getInputStream();
//
//            XSSFWorkbook workbook = new XSSFWorkbook(fileContent);
//            XSSFSheet sheet = workbook.getSheetAt(0); // Láº¥y sheet Ä‘áº§u tiÃªn
//            DataFormatter dataFormatter = new DataFormatter();
//
//            for (Row row : sheet) {
//                // Ä�á»�c tá»«ng dÃ²ng trong tá»‡p Excel vÃ  láº¥y thÃ´ng tin cá»§a sinh viÃªn
//            	
//            	    // ... (cÃ¡c dÃ²ng code khÃ¡c)
//
//            	    String MSSV = dataFormatter.formatCellValue(row.getCell(0));
//            	    String HoTen = dataFormatter.formatCellValue(row.getCell(1));
//            	    String ngaysinh = dataFormatter.formatCellValue(row.getCell(2));
//            	    String MaKhoa = dataFormatter.formatCellValue(row.getCell(3));
//            	    String MaKH = dataFormatter.formatCellValue(row.getCell(4));
//            	    String GioiTinh = dataFormatter.formatCellValue(row.getCell(5));
//            	    String DiaChi = dataFormatter.formatCellValue(row.getCell(6));
//            	    String QueQuan = dataFormatter.formatCellValue(row.getCell(7));
//            	    String SoDienThoai = dataFormatter.formatCellValue(row.getCell(8));
//            	    String Email = dataFormatter.formatCellValue(row.getCell(9));
//            	    String TrangThai = dataFormatter.formatCellValue(row.getCell(10));
//                                
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                java.sql.Date ngaySinh = new java.sql.Date(dateFormat.parse(ngaysinh).getTime());
//                // ChÃ¨n dá»¯ liá»‡u vÃ o cÆ¡ sá»Ÿ dá»¯ liá»‡u
//                svDAO.insertSV(MSSV, MaKhoa, MaKH, TrangThai, HoTen, ngaySinh, GioiTinh, DiaChi, QueQuan, SoDienThoai, Email);
//            }
//
//            workbook.close();
//            fileContent.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//		 
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/QuanlySV_QTV.jsp");
//        dispatcher.forward(request, response);
    }
	
	private void listCTSV(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
		    	
//		        List < CTSV > listCTSV = ctsvDAO.selectallCTSV();
//		        request.setAttribute("listCTSV", listCTSV);
//		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/QuanlyCTSV_QTV.jsp");
//		        dispatcher.forward(request, response);
		    }
			
	private void showtt_CTSV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
//		String CTSV = request.getParameter("id");
//        CTSV ctsv = ctsvDAO.selectCTSV(CTSV);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditCTSV_QTV.jsp");
//        request.setAttribute("CTSV", ctsv);
//        dispatcher.forward(request, response);
    }
	
	private void update_TT_CTSV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		
//		String CTSV= request.getParameter("id");
//		String TrangThai=request.getParameter("trangthai");
//		String HoTen=request.getParameter("hoten");
//		String ngaysinh = request.getParameter("ngaysinh");
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		java.sql.Date ngaySinh = new java.sql.Date(dateFormat.parse(ngaysinh).getTime());
//		
//		String GioiTinh=request.getParameter("gioitinh");
//		String DiaChi=request.getParameter("diachi");
//		String QueQuan=request.getParameter("quequan");
//		String SoDienThoai=request.getParameter("sodienthoai");
//		String Email=request.getParameter("email");
//		ctsvDAO.upDate_TT_CTSV(CTSV, TrangThai, HoTen, ngaySinh, GioiTinh, DiaChi, QueQuan, SoDienThoai, Email);
//      
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditCTSV_QTV.jsp");
//		dispatcher.forward(request, response);
    }
	
	private void insert_CTSV(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
//		String TrangThai=request.getParameter("trangthai");
//		String HoTen=request.getParameter("hoten");
//		String ngaysinh = request.getParameter("ngaysinh");
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		java.sql.Date ngaySinh = new java.sql.Date(dateFormat.parse(ngaysinh).getTime());
//		
//		String GioiTinh=request.getParameter("gioitinh");
//		String DiaChi=request.getParameter("diachi");
//		String QueQuan=request.getParameter("quequan");
//		String SoDienThoai=request.getParameter("sodienthoai");
//		String Email=request.getParameter("email");
//		ctsvDAO.insertCTSV( TrangThai, HoTen, ngaySinh, GioiTinh, DiaChi, QueQuan, SoDienThoai, Email);
//      
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditCTSV_QTV.jsp");
//		dispatcher.forward(request, response);
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
      
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditKhoa_QTV.jsp");
		dispatcher.forward(request, response);
    }
	
	private void insert_Khoa(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		String TrangThai=request.getParameter("trangthai");
		String tenKhoa=request.getParameter("tenkhoa");
		
		khoaDAO.insertKhoa( TrangThai, tenKhoa);
      
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditKhoa_QTV.jsp");
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
      
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditKhoaHoc_QTV.jsp");
		dispatcher.forward(request, response);
    }
	
	private void insert_KH(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
		String NamBD=request.getParameter("nambd");
		String NamKT=request.getParameter("namkt");
		String TenKH=request.getParameter("tenkh");
		
		khoahocDAO.insertKH( TenKH,NamBD,NamKT);
      
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EditKhoaHoc_QTV.jsp");
		dispatcher.forward(request, response);
    }
	
	private void showtt_SLtruycap(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
//		String ngaybd = request.getParameter("ngaybd");
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		java.sql.Date ngayBD = new java.sql.Date(dateFormat.parse(ngaybd).getTime());
//		
//		String ngaykt = request.getParameter("ngaykt");
//		java.sql.Date ngayKT = new java.sql.Date(dateFormat.parse(ngaykt).getTime());
//		
//        TruyCap truycap = truycapDAO.count_SL_truycap(ngayBD, ngayKT);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ThongkeWeb_QTV.jsp");
//        request.setAttribute("TruyCap", truycap);
//        dispatcher.forward(request, response);
    }
	
}
