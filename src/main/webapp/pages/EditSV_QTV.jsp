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
        <c:if test="${SinhVien != null}">
	     	<form action="update_TT_SV" method="post">
	    </c:if>
	    <c:if test="${SinhVien == null}">
	     	<form action="insert_SV" method="post">
	    </c:if>
	            <div style="width: 100%;"  class ="tables-container">
	            	 <div>
            
			            	<%
			                    String errMsg = (String) request.getAttribute("errMsg");
			       
			                %>
			                <%
			                    if (errMsg!=null){
			                %>   
			                <p style="color :red; font-size:23px; font-style: italic;"> <%=errMsg%> </p>       
			                <%
			                } 
			                %>
		            </div>            
	                <div class="form-row">
	                    <div class="column">
	                    	<div class="form-group">
		                        <h3>MSSV :</h3>
		                        <input type="text" value="${SinhVien.MSSV}"  name="mssv" id="mssv" placeholder="Nhập MSSV">
		                        <span id="mssvError" class="error-message"></span>
		            		</div>
		            		
		            		<div class="form-group">
		                        <h3>Khoa :</h3>
		                        <input type="hidden"  value="${empty SinhVien ? requestScope.makhoa : SinhVien.maKhoa}"  id="hiddenmakhoa" placeholder="Nhập mã khoa">
		                        <select name="makhoa" id="makhoa" >
		                        	<c:forEach var="khoa" items="${listKhoa}">
		                        		<option value="${khoa.maKhoa}">${khoa.tenKhoa}</option>			                        
			                        </c:forEach>      		                        
		                        </select>
		                     </div>   
		                     
		            		<div class="form-group">
		                        <h3>Ngày sinh :</h3>
		                        <input type="date" value="${empty SinhVien ? requestScope.ngaysinh : SinhVien.ngaySinh}" name="ngaysinh" id="ngaysinh" placeholder="Nhập ngày sinh">
		                        <span id="ngaysinhError" class="error-message"></span>
							</div>
							<div class="form-group">
		                        <h3>Quê quán:</h3>
		                        <input type="text" value="${empty SinhVien ? requestScope.quequan : SinhVien.queQuan}"  name="quequan" id="quequan" placeholder="Nhập que quan">
		                        <span id="quequanError" class="error-message"></span>
		            		</div>
		            		<div class="form-group">
		                        <h3>Điện thoại :</h3>
		                        <input type="text" value="${empty SinhVien ? requestScope.sdt : SinhVien.soDienThoai}" name="sodienthoai" id="dienthoai" placeholder="Nhập điện thoại">
		                        <span id="dienthoaiError" class="error-message"></span>
		                    </div> 

	
	                    </div>
	                    <div class="column">
	                    	<div class="form-group">
		                        <h3>Họ tên :</h3>
		                        <input type="text"   value="${empty SinhVien ? requestScope.hoten : SinhVien.hoTen}" name="hoten" id="hoten" placeholder="Nhập họ tên">
		                        <span id="hotenError" class="error-message">Vui lòng chọn ngày sinh</span>
		           			 </div>
		            		<div class="form-group">		            		
		                        <h3>Khóa học :</h3>
		                        <input type="hidden"  value="${empty SinhVien ? requestScope.makh : SinhVien.maKH}"  id="hiddenmakhoahoc" placeholder="Nhập mã khóa học">	
		                        <select name="makh" id="makhoahoc">
		                        	<c:forEach var="KH" items="${listKH}">
		                        		<option value="${KH.getMaKH()}">${KH.getTenKH()}</option>			                                     
				                      </c:forEach>              
		                        </select>
		                    </div>
		                        
		                   <div class="form-group">
		                        <input type="hidden" id="hiddengioitinh"  name="hiddengioitinh" value="${SinhVien.gioiTinh}" value="<%=request.getAttribute("gioitinh")%>">            
		                        <h3>Giới tính :</h3>
								<select name="gioitinh" id="gioitinh">
								    <option value="Nam">Nam</option>
								    <option value="Nữ">Nữ</option>
								</select>
							</div>
							<div class="form-group">
		                        <h3>Địa chỉ:</h3>
		                        <input type="text"  value="${empty SinhVien ? requestScope.diachi : SinhVien.diaChi}"  name="diachi" id="diachi" placeholder="Nhập địa chỉ">
		                        <span id="diachiError" class="error-message">Vui lòng chọn ngày sinh</span>
							</div>
							<div class="form-group">
		                       <input type="hidden" id="hiddentrangthai" name="hiddentrangthai"  value="${SinhVien.trangThai}"  value="<%=request.getAttribute("trangthai")%>">          
		                        <h3>Trạng thái </h3>
								<select name="trangthai" id="trangthai">
								    <option value="Đang học">Đang học</option>
								    <option value="Đã tốt nghiệp">Đã tốt nghiệp</option>
								</select>
							</div>
							<div class="form-group">
		                        <h3>Email :</h3>
		                        <input type="text"  value="${empty SinhVien ? requestScope.email : SinhVien.email}" value="${SinhVien.email}"  name="email" id="email" placeholder="Nhập email">
		                        <span id="emailError" class="error-message">Vui lòng chọn ngày sinh</span>
	                        </div>

	                    </div>
	                </div>       
	                     
	            </div>
	            <div style="display: flex; justify-content: center;">
	                <button type="submit" style=" width: 100px;margin-left: 5px; font-size: 20px; height: 40px;" >Lưu</button>
	        
	            </div>
        	</form>
			<button type="button" onclick="location.href='<%= request.getContextPath() %>/Qtv/listSV'" style="margin-top: 40px;margin-bottom: 40px; background-color: rgb(159, 108, 108);margin-left: 5px; font-size: 20px; height: 40px;">Quay lại </button>
         	
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
/* 	if(${SinhVien} != null)
	{
		var gioiTinhSelect = document.getElementById('gioitinh');
		gioiTinhSelect.value = ${SinhVien.gioiTinh};
	} */
     document.addEventListener('DOMContentLoaded', function () {
        document.querySelector('form').addEventListener('submit', function (event) {
        	var MSSV = document.getElementById('mssv').value;
            var hoTen = document.getElementById('hoten').value;
            var diaChi = document.getElementById('diachi').value;
            var quequan = document.getElementById('quequan').value;
            var email = document.getElementById('email').value;
            var soDienThoai = document.getElementById('dienthoai').value;
            var ngaysinh = document.getElementById('ngaysinh').value;

            var phonePattern = /^\d{10}$/;
            var emailPattern = /^\S+@\S+\.\S+$/;
			
            var mssvError = document.getElementById('mssvError');
            var hoTenError = document.getElementById('hotenError');
            var diaChiError = document.getElementById('diachiError');
            var ququanError = document.getElementById('quequanError');
            var emailError = document.getElementById('emailError');
            var soDienThoaiError = document.getElementById('dienthoaiError');
            var ngaysinhError = document.getElementById('ngaysinhError');


            
            if (MSSV === '') {
                mssvError.innerText = 'Vui lòng nhập mssv';
                mssvError.style.display = 'block';
                event.preventDefault();
            } else {
                mssvError.style.display = 'none';
            }
            
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
            }           
            else {
                emailError.style.display = 'none';
            }

            // Kiểm tra Số điện thoại
            if (!phonePattern.test(soDienThoai)) {
                soDienThoaiError.innerText = 'Số điện thoại phải có đúng 10 số';
                soDienThoaiError.style.display = 'block';
                event.preventDefault();
           
            } 
            else {
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
		    
		 var makhoa = document.getElementById('hiddenmakhoa');
	    var makhoaSelect = document.getElementById('makhoa');

	    if (makhoa.value !== '') {
	    	makhoaSelect.value = makhoa.value;
	    }
	    
	    var makhoahoc = document.getElementById('hiddenmakhoahoc');
	    var makhoahocSelect = document.getElementById('makhoahoc');

	    if (makhoahoc.value !== '') {
	    	makhoahocSelect.value = makhoahoc.value;
	    } 
	});
	
	var errMsg = '<%=request.getAttribute("errMsg")%>'
	     if(errMsg !== 'null')
	    	 {
	    	 	alert(errMsg);
	    	 }
</script>


