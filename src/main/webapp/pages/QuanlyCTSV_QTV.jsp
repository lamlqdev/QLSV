<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Models.TaiKhoan"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
            
            <div>
                <input id="searchInput" type="text" placeholder="Tìm kiếm công tác sinh viên" style="font-size: 20px;">
                <button id="searchButton" type="button" style="font-size: 20px;" >Tìm kiếm</button>
            </div>

            <div style="width: 100%;"  class ="tables-container">
                <table id="studentTable" border="1" style="width: 100%; border-collapse: collapse; font-size: 20px;">                       
                    <h3>Thông tin sinh viên </h3> 
                    <thead>
                        <tr>
                           
                            <th>Mã CTSV</th>
                            <th>Họ và tên </th>
                            <th>Email</th>
                            <th>Số điện thoại</th>
                            <th>Giới tính</th>
                            <th>Ngày sinh</th>
                            <th>Quê quán</th>
                            <th>Trạng thái</th>
                            <th> </th>
                                               
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach var="sv" items="${listCTSV}">
	                        <tr>
	                            <td>${sv.maCTSV}</td>  
	                            <td>${sv.hoTen}</td> 	                            
	                            <td>${sv.email}</td>
	                            <td>${sv.soDienThoai}</td>
	                            <td>${sv.gioiTinh }</td>
	                            <c:set var="localDate" value="${sv.ngaySinh.toLocalDate()}" />
	                            <% 
							        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							        Date utilDate = java.sql.Date.valueOf((java.time.LocalDate)pageContext.getAttribute("localDate"));
							        String formattedDate = sdf.format(utilDate);
						        %>
		                        <td>
       								
       								 
            						 <%= formattedDate %>
						        </td>
	                            
	                            <td>${sv.queQuan }</td>
	                            <td>${sv.trangThai }</td>
	                            <td>
						            <!-- Nút sửa thông tin -->
						            <a href="showtt_CTSV?id=${sv.maCTSV}">Sửa thông tin</a>
						        </td>
	                        </tr>        
                        </c:forEach>                          
                        <!-- Thêm các hàng dữ liệu khác vào đây -->
                    </tbody>            
                </table>

                
            </div>
            <div class="button-contain">
				<button type="button" onclick="location.href='<%=request.getContextPath()%>/Qtv/showtt_CTSV'">Thêm CTSV</button>
            </div>
        </div>
    </div>
    
</body>

</html>

<script>

    document.addEventListener("DOMContentLoaded", function() {
        const searchInput = document.getElementById('searchInput');
        const searchButton = document.getElementById('searchButton');
        const studentTable = document.getElementById('studentTable');
        const rows = studentTable.getElementsByTagName('tr');

        searchButton.addEventListener('click', function() {
            const searchText = searchInput.value.toLowerCase();

            for (let i = 0; i < rows.length; i++) {
                const cells = rows[i].getElementsByTagName('td');
                let found = false;

                for (let j = 0; j < cells.length; j++) {
                    const cellText = cells[j].textContent.toLowerCase();
                    if (cellText.includes(searchText)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    rows[i].style.display = '';
                } else {
                    rows[i].style.display = 'none';
                }
            }
        });
    });

</script>