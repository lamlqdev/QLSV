package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.*;
import Models.*;
import Models.TaiKhoan;
import Util.EmailUtility;

/**
 * Servlet implementation class QuenMKController
 */
@WebServlet("/QuenMK")
public class QuenMKController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
    private String port;
    private String user;
    private String pass;
    private SinhVienDAO svDAO;
    private CtsvDAO ctsvDAO ;
    private QtvDAO qtvDAO;
    public void init() {
        // reads SMTP server setting from web.xml file
    	ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
		svDAO= new SinhVienDAO();
		ctsvDAO= new CtsvDAO();
		qtvDAO= new QtvDAO();
	}
   
	
    public QuenMKController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String maso = request.getParameter("maso");
		String roleSelect= request.getParameter("role");
		String mactsv = request.getParameter("mactsv");
		String maqtv = request.getParameter("maqtv");
		System.out.println("heheheheh");
		try {
			if (roleSelect.equals("sv")) {
				
				 SinhVien sv = new SinhVien();
				 sv = svDAO.quenMK(email, maso);
				 System.out.println("heheheheh");
			    	if(sv != null )
			    	{	
			    		TaiKhoan tKhoan = sv.getTaiKhoan();
			    		sendemail(request,response,tKhoan.getMatkhau(),email);
			    		request.setAttribute("succMsg", "Mật khẩu đã được gửi về email "+" Vui lòng kiểm tra Email "  );
			    		 RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_Main/QuenMK.jsp");
			 		    dispatcher.forward(request, response);              
			    	}
			    	else 
			    	{
			    		request.setAttribute("errMsg", "Mssv hoặc email bị sai"  );
			            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_Main/QuenMK.jsp");
			        	dispatcher.forward(request, response);
			    	}
			    	
			    }
			 else if (roleSelect.equals("ctsv")){
				 CTSV ctsv = new CTSV();
				 ctsv = ctsvDAO.quenMK(email, mactsv);
				 System.out.println("heheheheh");
			    	if(ctsv != null )
			    	{	
			    		TaiKhoan tKhoan = ctsv.getTaiKhoan();
			    		sendemail(request,response,tKhoan.getMatkhau(),email);
			    		request.setAttribute("succMsg", "Mật khẩu đã được gửi về email "+" Vui lòng kiểm tra Email "  );
			    		 RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_Main/QuenMK.jsp");
			 		    dispatcher.forward(request, response);              
			    	}
			    	else 
			    	{
			    		request.setAttribute("errMsg", "Mssv hoặc email bị sai"  );
			            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_Main/QuenMK.jsp");
			        	dispatcher.forward(request, response);
			    	}
			    	
			    }
			 else if(roleSelect.equals("qtv")){
				 QTV qtv = new QTV();
				 qtv = qtvDAO.quenMK(email, maqtv);
				 System.out.println("heheheheh");
			    	if(qtv != null )
			    	{	
			    		TaiKhoan tKhoan = qtv.getTaiKhoan();
			    		sendemail(request,response,tKhoan.getMatkhau(),email);
			    		request.setAttribute("succMsg", "Mật khẩu đã được gửi về email "+" Vui lòng kiểm tra Email "  );
			    		 RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_Main/QuenMK.jsp");
			 		    dispatcher.forward(request, response);              
			    	}
			    	else 
			    	{
			    		request.setAttribute("errMsg", "Mssv hoặc email bị sai"  );
			            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_Main/QuenMK.jsp");
			        	dispatcher.forward(request, response);
			    	}
			    	
			 	}
		}
		catch (Exception e) {
			System.out.print(e);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}
	
	
	  private void sendemail(HttpServletRequest request, HttpServletResponse
	  response,String matkhau, String email) throws SQLException, ServletException,
	  IOException, ParseException {
	  
	  String subject = "Gửi lại mật khẩu"; String content =
	  "Mật khẩu của bạn là : "+ matkhau;
	  
	  String resultMessage = "";
	  
	  try { EmailUtility.sendEmail(host, port, user, pass, email, subject,
	  content); resultMessage = "The e-mail was sent successfully"; } catch
	  (Exception ex) { ex.printStackTrace(); resultMessage =
	  "There were an error: " + ex.getMessage(); } finally {
	  request.setAttribute("Message", resultMessage);
	  } }
	 
	

}
