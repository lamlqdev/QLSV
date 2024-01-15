<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="Models.TaiKhoan"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<%
		TaiKhoan account = (TaiKhoan) session.getAttribute("Acc");
		%>
		<%
		if (account == null) {
		%>
		<%
		response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
		%>
		<%
		} 
		%>
    <!-- Phần header -->
    <jsp:include page="/pages/header.jsp"></jsp:include>

    <!-- Phần Body -->
    <div class="wrapper">

        <!-- Phần sidebar -->
        <div class="sidebar">
            <ul class="menu">
				<li><a href="<%=request.getContextPath()%>/Qtv/info">Thông tin cá nhân</a></li>
                <li><a href="<%=request.getContextPath()%>/Qtv/listSV">Quản lý sinh viên </a></li>
                <li><a href="<%=request.getContextPath()%>/Qtv/listCTSV">Quản lý người dùng</a></li>
                <li><a href="<%=request.getContextPath()%>/Qtv/listKhoa">Quản lý khoa</a></li>
                <li><a href="<%=request.getContextPath()%>/Qtv/listKH">Quản lý khóa học</a></li>
                <li><a href="<%=request.getContextPath()%>/pages/ThongkeWeb_QTV.jsp">Thống kê tình hình sử dụng </a></li>
            </ul>
        </div>
    
        <!-- Phần content -->
        <div class="main-content">  
         
            <div style="width: 100%;"  class ="tables-container">
                <table border="1" style="width: 100%; border-collapse: collapse; font-size: 20px;">                       
                    <h3>Thông tin Khóa học </h3> 
                    <thead>
                        <tr>
                            <th>Mã khóa</th>
                            <th>Tên khóa học  </th>  
                            <th>Năm bắt đầu</th>       
                            <th>Năm hết thời gian đào tạo</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="KH" items="${listKH}">
                        <tr>
                            <td>${KH.maKH}</td>  
                            <td>${KH.tenKH}</td>  
                            <td>${KH.namBD}</td>
                            <td>${KH.namKT}</td>
                            <td>
					            <!-- Nút sửa thông tin -->
					            <a href="showtt_KH?id=${KH.maKH}">Sửa thông tin</a>
					        </td>
                        </tr>                      
                      </c:forEach>      
                        <!-- Thêm các hàng dữ liệu khác vào đây -->
                    </tbody>            
                </table>

                
            </div>
            <div class="button-contain">
                <button type="button" onclick="location.href='<%=request.getContextPath()%>/Qtv/showtt_KH'">Thêm khóa học</button>
            </div>
        </div>
    </div>
    
</body>

</html>


