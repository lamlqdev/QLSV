<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Models.TaiKhoan"%>
!<!DOCTYPE html>
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
            <h2>Thống kê tình hình sử dụng</h2>
            <div style="width: 100%;"  class ="tables-container">
            	<form id="myForm" action="<%=request.getContextPath()%>/Qtv/slTruyCap">
				    <h3>Ngày đầu</h3>
				    <input name="ngaybd" id="ngaybd" type="date" value="<%=request.getAttribute("ngaybd")%>">
				    <span id="ngaybdError" class="error-message" style="color: red; display: none;" >Vui lòng chọn ngày đầu</span>
				
				    <h3>Ngày cuối</h3>
				    <input name="ngaykt" id="ngaykt" type="date" placeholder="Nhập ngày sinh" value="<%=request.getAttribute("ngaykt")%>">
				    <span id="ngayktError" class="error-message" style="color: red; display: none;">Vui lòng chọn ngày cuối</span>
				
				    <button type="button" onclick="validateForm()" style="margin-left: 20px; font-size: 20px; width: 100px; display: block; margin-top: 30px">Lọc</button>
				</form>	
                <div>
                    <form action="" method="post" style="margin-top: 20px; border: 1px solid black; padding: 10px" >
                        <h3 style ="text-align: center; font-size: 22px">Số lượng truy cập </h3>
                        <h3>Tài khoản sinh viên : <span style="color:red">${TruyCap.slTK_SV}</span></h3>
                        <h3>Tài khoản CTSV : <span style="color:red">${TruyCap.slTK_CTSV}</span></h3>
                        <h3>Tài khỏan Admin : <span style="color:red">${TruyCap.slTK_QTV}</span></h3>
                    </form>
					<div >
					<table id="tableBody" style="display:none ;" >                       
						
						<thead>
							<tr>
								<th>Trạng thái</th>
							</tr>
						</thead>
						<tbody>
						   <c:forEach var="tb" items="${listTB}">
								<tr>
									<td>${tb.getTrangThai()}</td>
									       
								</tr>
								
							</c:forEach>			
							<!-- Thêm các hàng dữ liệu khác vào đây -->
						</tbody>            
					</table>
				</div>
                    <form action="" method="post" style="margin-top: 20px; border: 1px solid black; padding: 10px" >
                       <h3 style="text-align: center; font-size: 22px">Số lượng yêu cầu</h3>
				        <h3>Số lượng yêu cầu gửi đi: <span id="tongSoLuong" style="color:red"></span></h3>
				        <h3>Số lượng yêu cầu được duyệt: <span id="duocDuyet" style="color:red"></span></h3>
                    </form>

                </div>       
            </div>

           
        </div>
    </div>
   
</body>

</html>

<script>
    function validateForm() {
        var ngaybd = document.getElementById('ngaybd').value;
        var ngaykt = document.getElementById('ngaykt').value;
        var ngaybdError = document.getElementById('ngaybdError');
        var ngayktError = document.getElementById('ngayktError');
        
        if (ngaybd === '') {
            ngaybdError.style.display = 'block';
        } else {
            ngaybdError.style.display = 'none';
        }

        if (ngaykt === '') {
            ngayktError.style.display = 'block';
        } else {
            ngayktError.style.display = 'none';
        }
        var ngaybdDate = new Date(ngaybd);
        var ngayktDate = new Date(ngaykt);

        if (ngaybdDate > ngayktDate) {
            document.getElementById('ngaybdError').style.display = 'none';
            document.getElementById('ngayktError').style.display = 'block';
            document.getElementById('ngayktError').innerText = 'Ngày đầu phải nhỏ hơn ngày cuối';
        }
        // Kiểm tra xem cả hai ngày đều đã được chọn, nếu đúng thì submit form
        if (ngaybd !== '' && ngaykt !== '' && ngaybdDate < ngayktDate) {
            document.getElementById('myForm').submit();
        }
        

        
    }
    
    document.addEventListener("DOMContentLoaded", function() {
        var tableBody = document.getElementById("tableBody");
        if (tableBody) {
            var tableRows = tableBody.getElementsByTagName("tr");
            var daDuyetRowCount = 0;
            var tongSoLuong = 0;

            for (var i = 0; i < tableRows.length; i++) {
                var cells = tableRows[i].getElementsByTagName("td");
                if (cells.length > 0) {
                    var cellValue = cells[0].textContent.trim();
                    tongSoLuong++;
                    if (cellValue === "Đã duyệt") {
                        daDuyetRowCount++;
                    }
                }
            }

            document.getElementById("tongSoLuong").textContent = tongSoLuong;
            document.getElementById("duocDuyet").textContent = daDuyetRowCount;
        } else {
            console.log("Không tìm thấy bảng có id là 'tableBody'");
        }
    });

   
</script>


