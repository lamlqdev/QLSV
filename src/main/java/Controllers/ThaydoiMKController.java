package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.*;
import Models.TaiKhoan;

/**
 * Servlet implementation class ThaydoiMKController
 */
@WebServlet("/ThaydoiMK")
public class ThaydoiMKController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private LoginDAO loginDAO ;
	public void init() {
		loginDAO = new LoginDAO();
		}
    public ThaydoiMKController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		String password = request.getParameter("password");
		String newpass= request.getParameter("newpassword");
		HttpSession session = request.getSession();
		TaiKhoan account = (TaiKhoan) session.getAttribute("Acc");
		String pass=account.getMatkhau();
		if (account.getMatkhau().equals(password)) {	
			System.out.println(newpass);
			System.out.println(pass);
			 loginDAO.upDate_MK( account.getIdTk(),newpass);
			 request.setAttribute("succMsg", "Đổi mật khẩu thành công");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_Main/ThaydoiMK.jsp");
		    dispatcher.forward(request, response);
		    	
		    } 
		else {
		        //HttpSession session = request.getSession();
		        //session.setAttribute("user", username);
		        //response.sendRedirect("login.jsp");
		    	request.setAttribute("errMsg", "Nhập sai mật khẩu, vui lòng nhập lại ");
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/pages_Main/ThaydoiMK.jsp");
		    	dispatcher.forward(request, response);
		    	
		    }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
