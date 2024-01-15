package Controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CtsvDAO;
import DAO.DangKiCTXHDAO;
import DAO.HoatDongCTXHDAO;
import DAO.KhoaDAO;
import DAO.KhoaHocDAO;
import DAO.LoaiGiayDAO;
import DAO.LyDoDAO;
import DAO.QtvDAO;
import DAO.SinhVienDAO;
import DAO.ThongBaoDAO;
import DAO.TruyCapDAO;
import DAO.XacNhanGXNDAO;
import DAO.XacNhanNganhNgheDAO;
import Models.CTSV;
import Models.DangKiCTXH;
import Models.HoatDongCTXH;
import Models.Khoa;
import Models.KhoaHoc;
import Models.LoaiGiay;
import Models.QTV;
import Models.SinhVien;
import Models.TaiKhoan;
import Models.ThongBao;
import Models.XacNhanGXN;
import Models.XacNhanNganhNghe;
import Util.EmailUtility;


@WebServlet("/Ctsv")
public class CTSVController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QtvDAO qtvDAO;
	private SinhVienDAO svDAO;
	private CtsvDAO ctsvDAO;
	private KhoaDAO khoaDAO;
	private KhoaHocDAO khoahocDAO;
	private TruyCapDAO truycapDAO;
	private XacNhanNganhNgheDAO xnnnDAO;
	private XacNhanGXNDAO gxnDAO;
	private LoaiGiayDAO lgDAO;
	private DangKiCTXHDAO dkictxhDAO;
	private HoatDongCTXHDAO hdctxhDAO;
	private ThongBaoDAO thongbaoDAO;
	private LyDoDAO lydoDAO;
	private String host;
    private String port;
    private String user;
    private String pass;
    public CTSVController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
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
    	xnnnDAO = new XacNhanNganhNgheDAO();
    	gxnDAO = new XacNhanGXNDAO();
    	lgDAO = new LoaiGiayDAO();
    	dkictxhDAO = new DangKiCTXHDAO();
    	hdctxhDAO = new HoatDongCTXHDAO();
    	thongbaoDAO = new ThongBaoDAO();
    	lydoDAO = new LyDoDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();

		request.setCharacterEncoding("UTF-8");
        try {
            switch (action) {
                case "/info":
                	ShowPersonalInfo(request, response);
                    break;
                case "/update":
                	UpdateContactInfo(request, response);
                    break;
                case "/listxnnn":
                	ShowAllRequestsXNNN(request, response);
                	break;
                case "/acceptxnnn":
                	AcceptXNNNRequest(request, response);
                	break;
                case "/confirmacceptxnnn":
                	UpdateXNNNRequest(request, response);
                	break;
                case "/listgxn":
                	ShowAllRequestGXN(request, response);
                	break;
                case "/acceptgxn":
                	AcceptGXNRequest(request, response);
                	break;
                case "/confirmacceptgxn":
                	UpdateGXNRequest(request, response);
                	break;
                case "/addgxn":
                	AddGXN(request, response);
                	break;
                case "/listdkictxh":
                	ShowAllRequestDKICTXH(request, response);
                	break;
                case "/acceptdkictxh":
                	AcceptDKICTXHRequest(request, response);
                	break;
                case "/confirmacceptdkictxh":
                	UpdateDKICTXHRequest(request, response);
                	break;
                case "/confirmctxh":
                	ConfirmCTXH(request, response);
                	break;            
                case "/listhdctxh":
                	ShowAllHDCTXH(request, response);
                	break;
                case "/updatehdctxh":
                	ShowUpdateHDCTXH(request, response);
                	break;
                case "/confirmupdatectxh":
                	UpdateHDCTXH(request, response);
                	break;
                case "/inserthdctxh":
                	InsertHDCTXH(request, response);
                	break;
                case "/listTB":
                	listTB(request, response);
                    break;
				case "/listConfirmAcceptGXN":
					ShowAllAcceptedGXN(request, response);
					break;
                case "/confirmGXN":
                	UpdateConfirmGXN(request, response);
                	break;
                case "/addlydo":
                	InsertLydo(request, response);
                	break;
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
	
	private void ShowPersonalInfo(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String idTk = acc.getIdTk();
		        CTSV ctsv = ctsvDAO.selectCTSV_IDTK(idTk);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/TTCaNhanCTSV.jsp");
		        request.setAttribute("ttCTSV", ctsv);
		        dispatcher.forward(request, response);
		    }
	
	private void UpdateContactInfo(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				request.setAttribute("acc", acc);
				String idTk = acc.getIdTk();
				String std = request.getParameter("phoneInput");
				String email = request.getParameter("emailInput");
		        ctsvDAO.upDate_ttll_CTSV(idTk, std, email);
		      
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/Ctsv/info");
				dispatcher.forward(request, response);
		    }
	
	private void ShowAllRequestsXNNN(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {	
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
		        List <XacNhanNganhNghe> xnnns = xnnnDAO.SelectAllRequestsXNNN();
		        request.setAttribute("xnnns", xnnns);
		        request.setAttribute("acc", acc);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/QuanLyXacNhanNganhNghe_CTSV.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void AcceptXNNNRequest(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String ID = request.getParameter("id");
				XacNhanNganhNghe xnnn = xnnnDAO.SelectXNNNRequestID(ID);
				request.setAttribute("xnnn", xnnn);
				request.setAttribute("acc", acc);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/DuyetYeuCauXNNN.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void UpdateXNNNRequest(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String ID_TK = acc.getIdTk();
				CTSV ctsv = ctsvDAO.selectCTSV_IDTK(ID_TK);
				String maCTSV = ctsv.getMaCTSV();				
				String id = request.getParameter("id");
				String tieuDe = request.getParameter("title");
				String email = request.getParameter("email");
				String noiDung = request.getParameter("content");
				
				LocalDate ngayHienTai = LocalDate.now();
				java.sql.Date thoiGianPhanHoi = java.sql.Date.valueOf(ngayHienTai);
				
				boolean isSuccess = xnnnDAO.UpdateRequestXNNN(id, thoiGianPhanHoi, tieuDe, maCTSV, noiDung);
				request.setAttribute("isSuccess", isSuccess);
				try {
					sendemail(request,response,tieuDe,noiDung,email);
				} catch (SQLException | ServletException | IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Ctsv/listxnnn");
				dispatcher.forward(request, response);
		    }
	
	private void ShowAllRequestGXN(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {	
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				List <LoaiGiay> lgs = lgDAO.SelectAllLoaiGiay();
		        request.setAttribute("lgs", lgs);
				String maLoaiGiay = request.getParameter("maLoaiGiay");
		        List <XacNhanGXN> gxns = gxnDAO.SelectAllRequestsGXN(maLoaiGiay);
		        request.setAttribute("gxns", gxns);
		        request.setAttribute("acc", acc);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/QuanLyGiayXacNhan_CTSV.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void AcceptGXNRequest(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String ID = request.getParameter("id");
				XacNhanGXN gxn = gxnDAO.SelectGXNRequestID(ID);
				request.setAttribute("gxn", gxn);
				request.setAttribute("acc", acc);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/DuyetYeuCauGXN.jsp");
		        dispatcher.forward(request, response);
	}
	
	private void UpdateGXNRequest(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String ID_TK = acc.getIdTk();
				CTSV ctsv = ctsvDAO.selectCTSV_IDTK(ID_TK);
				String maCTSV = ctsv.getMaCTSV();
				
				String id = request.getParameter("id");
				String tieuDe = request.getParameter("title");
				String email = request.getParameter("email");
				String noiDung = request.getParameter("content");
				LocalDate ngayHienTai = LocalDate.now();
				java.sql.Date thoiGianPhanHoi = java.sql.Date.valueOf(ngayHienTai);
				
				boolean isSuccess = gxnDAO.UpdateRequestGXN(id, thoiGianPhanHoi, tieuDe, maCTSV, noiDung);
				request.setAttribute("isSuccess", isSuccess);
				try {
					sendemail(request,response,tieuDe,noiDung,email);
				} catch (SQLException | ServletException | IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Ctsv/listgxn");
				dispatcher.forward(request, response);
		    }
	
	private void AddGXN(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException, ParseException {
				String tenLoaiGiay = request.getParameter("tenGiay");
				if (!lgDAO.isTenLoaiGiayExists(tenLoaiGiay)) {
			        boolean isSuccess = lgDAO.InsertGXN(tenLoaiGiay);
			        request.setAttribute("isSuccess", isSuccess);
			    } else {
			        request.setAttribute("isSuccess", false);
			        request.setAttribute("errorMessage", "Tên giấy đã tồn tại trong cơ sở dữ liệu.");
			    }
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/TrangThemGiayXacNhan.jsp");
				dispatcher.forward(request, response);
		    }
	
	private void ShowAllRequestDKICTXH(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {	
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				List <DangKiCTXH> dkictxhs = dkictxhDAO.SelectAllRequestsHDCTXH();
				List <DangKiCTXH> xnctxhs = dkictxhDAO.SelectAllUnConfirmedHDCTXH();
		        request.setAttribute("dkictxhs", dkictxhs);
		        request.setAttribute("xnctxhs", xnctxhs);
		        request.setAttribute("acc", acc);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/QuanLyHoatDongCTXH_CTSV.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void AcceptDKICTXHRequest(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String ID = request.getParameter("id");
				DangKiCTXH dkictxh = dkictxhDAO.SelectDKICTXHRequestID(ID);
				request.setAttribute("dkictxh", dkictxh);
				request.setAttribute("acc", acc);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/DuyetYeuCauHDCTXH.jsp");
		        dispatcher.forward(request, response);
	}
	
	private void UpdateDKICTXHRequest(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String ID_TK = acc.getIdTk();
				CTSV ctsv = ctsvDAO.selectCTSV_IDTK(ID_TK);
				String maCTSV = ctsv.getMaCTSV();				
				String id = request.getParameter("id");
				String tieuDe = request.getParameter("title");
				String email = request.getParameter("email");
				String noiDung = request.getParameter("content");
				LocalDate ngayHienTai = LocalDate.now();
				java.sql.Date thoiGianPhanHoi = java.sql.Date.valueOf(ngayHienTai);
				
				boolean isSuccess = dkictxhDAO.UpdateRequestDKICTXH(id, thoiGianPhanHoi, tieuDe, maCTSV, noiDung);
				request.setAttribute("isSuccess", isSuccess);
				try {
					sendemail(request,response,tieuDe,noiDung,email);
				} catch (SQLException | ServletException | IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Ctsv/listdkictxh");
				dispatcher.forward(request, response);
		    }
	
	private void ShowAllHDCTXH(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {	
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				List <HoatDongCTXH> hdctxhs = hdctxhDAO.SelectAllHDCTXH();		        
		        request.setAttribute("hdctxhs", hdctxhs);
		        request.setAttribute("acc", acc);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/DanhSachHoatDongCTXH.jsp");
		        dispatcher.forward(request, response);
		    }
		
	private void ShowUpdateHDCTXH(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");				
				String id = request.getParameter("id");
				HoatDongCTXH hdctxh = hdctxhDAO.SelectHDCTXH(id);				
				request.setAttribute("acc", acc);
				request.setAttribute("hdctxh", hdctxh);				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/QuanLyDanhSachHoatDongCTXH.jsp");
				dispatcher.forward(request, response);
		    }
	
	private void UpdateHDCTXH(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
	
				String id = request.getParameter("id");
				String tenHoatDong = request.getParameter("tenHoatDong");
				String diem = request.getParameter("diem");
				String tgDienRa = request.getParameter("thoiGianBatDau");
				String tgKetThuc = request.getParameter("thoiGianKetThuc");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String noiDung = request.getParameter("noiDung");
				
				try {
				    java.util.Date utilThoiGianBatDau = sdf.parse(tgDienRa);
				    java.util.Date utilThoiGianKetThuc = sdf.parse(tgKetThuc);

				    Date sqlThoiGianBatDau = new Date(utilThoiGianBatDau.getTime());
				    Date sqlThoiGianKetThuc = new Date(utilThoiGianKetThuc.getTime());

				    boolean isSuccess = hdctxhDAO.UpdateHDCTXH(id, tenHoatDong, diem, noiDung, sqlThoiGianBatDau, sqlThoiGianKetThuc);
					request.setAttribute("isSuccess", isSuccess);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Ctsv/listhdctxh");
					dispatcher.forward(request, response);

				} catch (ParseException e) {
				    e.printStackTrace();
				}
				request.setAttribute("acc", acc);

		    }
	
	private void InsertHDCTXH(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException, ParseException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");	
				String tenHoatDong = request.getParameter("tenHoatDong");
				String diem = request.getParameter("diem");
				String tgDienRa = request.getParameter("thoiGianBatDau");
				String tgKetThuc = request.getParameter("thoiGianKetThuc");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String noiDung = request.getParameter("noiDung");
				
				boolean isSuccess = hdctxhDAO.InsertHDCTXH(tenHoatDong, diem, noiDung, tgDienRa, tgKetThuc);
				request.setAttribute("isSuccessInsert", isSuccess);
					
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Ctsv/listhdctxh");
				dispatcher.forward(request, response);

				request.setAttribute("acc", acc);
		    }
	
	private void listTB(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {   	
		        List < ThongBao > listTB = thongbaoDAO.selectalltbCTSV();
		        request.setAttribute("listTB", listTB);
		        List < KhoaHoc > listKH = khoahocDAO.selectallKHOAHOC();
			    request.setAttribute("listKH", listKH);
				List < Khoa > listKhoa = khoaDAO.selectallKHOA();
			    request.setAttribute("listKhoa", listKhoa);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/TrangChu.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void ConfirmCTXH(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				String ID_TK = acc.getIdTk();
				CTSV ctsv = ctsvDAO.selectCTSV_IDTK(ID_TK);
				String maCTSV = ctsv.getMaCTSV();				
				String id = request.getParameter("id");
				String trangThai = "Đã tham gia";
				boolean isSuccessConfirm = dkictxhDAO.ConfirmCTXH(id, maCTSV, trangThai);
				request.setAttribute("isSuccessConfirm", isSuccessConfirm);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Ctsv/listdkictxh");
				dispatcher.forward(request, response);
		    }
	private void ShowAllAcceptedGXN(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {	
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
		        List <XacNhanGXN> gxns = gxnDAO.SelectAllAcceptedGXN();
		        request.setAttribute("gxns", gxns);
		        request.setAttribute("acc", acc);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/DanhSachChoXacNhanGXN.jsp");
		        dispatcher.forward(request, response);
		    }
	
	private void UpdateConfirmGXN(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");
				
				String id = request.getParameter("id");			
				boolean isSuccessConfirm = gxnDAO.UpdateConfirmGXN(id);
				request.setAttribute("isSuccessConfirm", isSuccessConfirm);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Ctsv/listConfirmAcceptGXN");
				dispatcher.forward(request, response);
		    }
	
	private void InsertLydo(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, ServletException, IOException, ParseException {
				HttpSession session = request.getSession();
				TaiKhoan acc = (TaiKhoan)session.getAttribute("Acc");	
				
				String tenLydo = request.getParameter("tenLydo");
				String noiDung = request.getParameter("noiDung");
				
				boolean isSuccess = lydoDAO.insertLyDo("enable", tenLydo, noiDung);
				request.setAttribute("isSuccessInsert", isSuccess);
					
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_CTSV/TrangThemLyDo.jsp");
				dispatcher.forward(request, response);

				request.setAttribute("acc", acc);
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
