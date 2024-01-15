
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ page import="Models.TaiKhoan"%>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
 
 


<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Đăng nhập</title>
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="css/style.css">
  
  <style>

        body {
        font-family: sans-serif;
        background-color: white;
        }

        .container {
        width: 630px;
        margin: 30px auto;
        font-size: 20px;
        padding: 30px 30px 30px 30px ;
		border-radius: 10px;
        border: 2px double rgb(4, 4, 4);
        }

        h1 {
        text-align: center;
        }

        .role-selection {
        display: flex;
        justify-content: space-between;
        margin-bottom: 20px;
        }

        .role-selection input {
        margin-right: 6px;
        }

        .username-input, .password-input {
        margin-bottom: 20px;
        }

        .username-input input, .password-input input {
        width: 100%;
        padding: 10px;
        border: 2px double gray;
        
        }

        .button-group {
        display: flex;
        justify-content: flex-end;
        }

        .button-group a {
        text-decoration: none;
        color: gray;
        }

        .button-group button {
        background-color: #269ABC;
        color: white;
        font-weight: bold;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
        }    

        .button-group {
            display: flex;
            justify-content: space-between; /* Căn khoảng cách giữa các phần tử */
            align-items:first baseline; /* Căn các phần tử theo chiều dọc */
        }

        .forgot-password {

            margin-right: auto; /* Di chuyển liên kết sang bên trái */
        }
  </style>
</head>
<body>
		<%
		TaiKhoan account = (TaiKhoan) session.getAttribute("Acc");
		%>
		<%
		if (account == null) {
		%>
		<%
			response.sendRedirect("login.jsp");
		%>
		<%
		} 
		%>
    <div class="image-container">
        <img border="0" src="https://online.hcmute.edu.vn/Portlets/UIS_MySpace/Images/SPKT.jpg">
    </div>
	<div class="button-container" style ="margin-top :0;">
	    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #00558c">
	        <ul class="navbar-nav navbar-collapse justify-content-end">
	            <li><a href="<%= request.getContextPath() %>/pages_Main/MainPage.jsp" class="nav-link">Trang chủ</a></li>          
	        </ul>
	    </nav>
	</div>
		
    <form action="<%=request.getContextPath()%>/ThaydoiMK" method="post"  id="changePasswordForm">
        <div class="container">       
            <h1>Đổi mật khẩu </h1>
           
            <div class="username-input">
                <label for="username">Mật khẩu cũ</label>
                <input type="text" name="password" id="password" placeholder="Mật khẩu cũ " style="margin-top: 10px">              
            </div>
           
            <div class="password-input">
                <label for="password">Mật khẩu mới </label>
                <input type="password" name="newpassword" id="newpassword" placeholder="Mật khẩu mới "  style="margin-top: 10px">
            </div>
            
            <div class="password-input">
                <label for="password">Nhập lại mật khẩu mới </label>
                <input type="password" name="renewpassword" id="renewpassword" placeholder="Mật khẩu mới "  style="margin-top: 10px">
                <div id="passwordError" class="error-message" style="display: none;color :red;  font-style: italic;">Mật khẩu mới không khớp.</div>
            </div>
             <div>
            
            	<%
                    String errMsg = (String) request.getAttribute("errMsg");
                 	String succMsg = (String) request.getAttribute("succMsg");
                %>
                <%
                    if (errMsg!=null){
                %>   
                <p style="color :red;  font-style: italic;"> <%=errMsg%> </p>       
                <%
                } 
                %>
                
                <%
                    if (succMsg!=null){
                %>   
                <p style="color :green;  font-style: italic;"> <%=succMsg%> </p>       
                <%
                } 
                %>
            </div>
            
            <div class="button-group">
                <%
                    if (account.getPhanQuyen().equals("qtv")){
                %>   
                <a href="<%= request.getContextPath() %>/Qtv/info" class="forgot-password">Quay lại</a>
                <%
                } 
                %>
             	<%
                    if (account.getPhanQuyen().equals("sv")){
                %>   
                <a href="<%= request.getContextPath() %>/SV/info" class="forgot-password">Quay lại</a>
                <%
                } 
                %>
                <%
                    if (account.getPhanQuyen().equals("ctsv")){
                %>   
                <a href="<%= request.getContextPath() %>/Ctsv/info" class="forgot-password">Quay lại</a>
                <%
                } 
                %>
                
                <button type="button" onclick="validatePasswords()"  style="font-size: 20px;">Lưu</button>
            </div>    
        </div>
    </form>
</body>
</html>

<script>
    function validatePasswords() {
        var newPassword = document.getElementById('newpassword').value;
        var renewPassword = document.getElementById('renewpassword').value;
        var passwordError = document.getElementById('passwordError');
        
        // Kiểm tra nếu mật khẩu mới và nhập lại mật khẩu không khớp
        if (newPassword !== renewPassword) {
            passwordError.style.display = 'block';
        } else {
            passwordError.style.display = 'none';
            document.getElementById('changePasswordForm').submit();
        }
    }
</script>