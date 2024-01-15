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
        <c:if test="${CTSV != null}">
	     	<form id="myForm" action="<%=request.getContextPath()%>/Qtv/update_TT_CTSV" method="post">
	     	
	    </c:if>
	    <c:if test="${CTSV == null}">
	     	<form id="myForm" action="<%=request.getContextPath()%>/Qtv/insert_CTSV" method="post">
	    </c:if>
	            <div style="width: 100%;"  class ="tables-container">
	                <div class="form-row">
	                    <div class="column">
	                    	<c:if test="${CTSV != null}">
						     	<div class="form-group">
			                        <h3>Mã CTSV :</h3>
			                        <input type="text" value="${CTSV.maCTSV}" name="id" id="id" readonly>
			                    </div>
						    </c:if>         
		                    <div class="form-group">   
		                    	 
		                        <h3>Ngày sinh :</h3>
								<input type="date"  value="${CTSV.ngaySinh}" name="ngaysinh" id="ngaysinh" placeholder="Nhập ngày sinh">
		                        <span id="ngaysinhError" class="error-message"></span>
		                    </div>
		                        
							<div class="form-group">
		                        <h3>Quê quán:</h3>
		                        <input type="text" value="${CTSV.queQuan}" name="quequan" id="quequan" placeholder="Nhập que quan">
		                        <span id="quequanError" class="error-message"></span>
		                    </div>
		                        
		            		<div class="form-group">
		                        <h3>Điện thoại :</h3>
		                        <input type="text" value="${CTSV.soDienThoai}"name="sodienthoai" id="dienthoai" placeholder="Nhập điện thoại">
		                        <span id="dienthoaiError" class="error-message"></span>
		                    </div>

	                    </div>
	                    <div class="column">
	                    	<div class="form-group">
		                        <h3>Họ tên :</h3>
		                        <input type="text" value="${CTSV.hoTen}"name="hoten" id="hoten" placeholder="Nhập họ tên">
		            			<span id="hotenError" class="error-message"></span>	 
		            		</div>   
		            			        			 
		            		<div class="form-group">	
		                        <input type="hidden" id="hiddengioitinh" name="hiddengioitinh" value="${CTSV.gioiTinh}">            
		                        <h3>Giới tính :</h3>
								<select name="gioitinh" id="gioitinh">
								    <option value="Nam">Nam</option>
								    <option value="Nữ">Nữ</option>
								</select>
							</div>
								
							<div class="form-group">
		                        <h3>Địa chỉ:</h3>
		                        <input type="text" value="${CTSV.diaChi}" name="diachi" id="diachi" placeholder="Nhập địa chỉ">
								<span id="diachiError" class="error-message"></span>
							</div>
								
							<div class="form-group">		
								<input type="hidden" id="hiddentrangthai" name="hiddentrangthai" value="${CTSV.trangThai}" >          
		                        <h3>Trạng thái </h3>
								<select name="trangthai" id="trangthai">
								    <option value="Đang làm">Đang làm</option>
								    <option value="Đã nghỉ việc">Đã nghỉ việc</option>
								</select>
							</div>
								
							<div class="form-group">	
		                        <h3>Email :</h3>
		                        <input type="text" value="${CTSV.email}" name="email" id="email" placeholder="Nhập email">
		                        <span id="emailError" class="error-message"></span>
							</div>
	                    </div>
	                </div>                         
	            </div>
	           <div style="display: flex; justify-content: center;">
	                <button type="submit" style=" width: 100px;margin-left: 5px; font-size: 20px; height: 40px;" >Lưu</button>
	        
	            </div>
        	</form>
			<button type="button" onclick="location.href='<%= request.getContextPath() %>/Qtv/listCTSV'" style="margin-top: 40px;margin-bottom: 40px; background-color: rgb(159, 108, 108);margin-left: 5px; font-size: 20px; height: 40px;">Quay lại </button>
         	
        </div>
    </div>
    <style>
        .form-row {
        display: flex;
        flex-wrap: wrap;
        gap: 20px; /* Khoảng cách giữa các cột */
        }

        /* CSS cho cột */
        .column {
            flex: 1; /* Cột sẽ căng ra để lấp đầy không gian */
            width: calc(50% - 10px); /* Chiều rộng cột */
            padding: 5px;
        }

        /* CSS cho h3 */
        h3 {
            margin-bottom: 5px;
        }

        /* CSS cho input và select */
        input,
        select {
            width: calc(100% - 10px); /* Điều chỉnh chiều rộng */
            padding: 5px;
            font-size: 20px;
        }
        
        .error-message {
            color: red;
            font-size: 12px;
            display: none;
        }
    </style>
    
</body>

</html>

<script>
     document.addEventListener('DOMContentLoaded', function () {
        document.querySelector('form').addEventListener('submit', function (event) {
            var hoTen = document.getElementById('hoten').value;
            var diaChi = document.getElementById('diachi').value;
            var quequan = document.getElementById('quequan').value;
            var email = document.getElementById('email').value;
            var soDienThoai = document.getElementById('dienthoai').value;
            var ngaysinh = document.getElementById('ngaysinh').value;

            var phonePattern = /^\d{10}$/;
            var emailPattern = /^\S+@\S+\.\S+$/;

            var hoTenError = document.getElementById('hotenError');
            var diaChiError = document.getElementById('diachiError');
            var ququanError = document.getElementById('quequanError');
            var emailError = document.getElementById('emailError');
            var soDienThoaiError = document.getElementById('dienthoaiError');
            var ngaysinhError = document.getElementById('ngaysinhError');


            // Kiểm tra Họ tên
            if (hoTen === '') {
                hoTenError.innerText = 'Vui lòng nhập họ tên';
                hoTenError.style.display = 'block';
                event.preventDefault();
            } else {
                hoTenError.style.display = 'none';
            }

            // Kiểm tra Địa chỉ
            if (diaChi === '') {
                diaChiError.innerText = 'Vui lòng nhập địa chỉ';
                diaChiError.style.display = 'block';
                event.preventDefault();
            } else {
                diaChiError.style.display = 'none';
            }
            
         // Kiểm tra Địa chỉ
            if (quequan === '') {
            	quequanError.innerText = 'Vui lòng nhập quê quán';
            	quequanError.style.display = 'block';
                event.preventDefault();
            } else {
            	quequanError.style.display = 'none';
            }
         
            
            if (ngaysinh === '') {
                ngaysinhError.innerText = 'Vui lòng nhập ngày sinh';
                ngaysinhError.style.display = 'block';
                event.preventDefault();
            } else {
                ngaysinhError.style.display = 'none';
            }

			
            // Kiểm tra Email
            if (!emailPattern.test(email) ) {
                emailError.innerText = 'Vui lòng nhập email đúng định dạng';
                emailError.style.display = 'block';
                event.preventDefault();        
            } else {
                emailError.style.display = 'none';
            }
			console.log(ngaysinh);
            
            // Kiểm tra Số điện thoại
            if (!phonePattern.test(soDienThoai)) {
                soDienThoaiError.innerText = 'Số điện thoại phải có đúng 10 số';
                soDienThoaiError.style.display = 'block';
                event.preventDefault();
           
            } else {
                soDienThoaiError.style.display = 'none';
            }
        });
    }); 
     
     
     document.addEventListener('DOMContentLoaded', function() {
 	    var hiddenInput = document.getElementById('hiddengioitinh');
 	    var gioiTinhSelect = document.getElementById('gioitinh');

 	    if (hiddenInput.value !== '') {
 	        gioiTinhSelect.value = hiddenInput.value;
 	    }
 	    
 	    var trangthai = document.getElementById('hiddentrangthai');
 	    var trangthaiSelect = document.getElementById('trangthai');

 	    if (trangthai.value !== '') {
 	    	trangthaiSelect.value = trangthai.value;
 	    }
  
 	});  
     
     var errMsg = '<%=request.getAttribute("errMsg") %>';
     if(errMsg !== 'null')
    	 {
    	 	alert(errMsg);
    	 }
</script>




