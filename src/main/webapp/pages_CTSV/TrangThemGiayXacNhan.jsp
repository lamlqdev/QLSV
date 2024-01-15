<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Models.TaiKhoan"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Trang thêm loại giấy</title>
	<link rel="stylesheet" href="../css/style_CTSV.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<!-- Phần header -->
    <jsp:include page="header.jsp"></jsp:include>
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
    <!-- Phần Body -->
    <div class="wrapper">
        <!-- Phần sidebar -->
        <div class="sidebarnew">
            <ul class="menu">
            	<li><a href="<%=request.getContextPath()%>/Ctsv/listTB">Trang chủ</a></li>
                <li><a href="<%=request.getContextPath()%>/Ctsv/info">Thông tin cá nhân</a></li>
                <li><a href="<%=request.getContextPath()%>/Ctsv/listgxn">Quản lý giấy xác nhận</a></li>
                <li><a href="<%=request.getContextPath()%>/Ctsv/listxnnn">Quản lý giấy xác nhận ngành nghề</a></li>
                <li><a href="<%=request.getContextPath()%>/Ctsv/listdkictxh">Quản lý hoạt động CTXH</a></li>
            </ul>
        </div>
        
	    <!-- Phần nội dung -->
	    <div class="main-content">
	    	<form action="<%=request.getContextPath()%>/Ctsv/addgxn" method="post" onsubmit="return validateForm()">
		    	<fieldset style="border:1px solid black; padding: 10px;">
		    		<legend class="my-legend">Thêm giấy xác nhận</legend>
					<label for="tenGiay">Tên giấy:</label>
				    <input type="text" id="tenGiay" name="tenGiay">
				
				    <button class="button" type="submit">Lưu</button>
				    <button class="button" type="button" onclick="location.href='<%=request.getContextPath()%>/Ctsv/listgxn'">Quay lại</button>
				 </fieldset>
			</form>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
		$(document).ready(function() {
		    var isSuccess = <%= request.getAttribute("isSuccess") %>;
		    var errorMessage = '<%= request.getAttribute("errorMessage") %>';
		    
		    if (isSuccess != null) {
		        if (isSuccess) {
		            alert("Thêm dữ liệu thành công!");
		        } else {
		            if (errorMessage != null && errorMessage.trim() !== '') {
		                alert("Thêm dữ liệu thất bại! Lỗi: " + errorMessage);
		            } else {
		                alert("Thêm dữ liệu thất bại!");
		            }
		        }
		    }
		});
        
        function validateForm() {
            var tenGiay = document.getElementById('tenGiay').value;
            if (tenGiay.trim() === '') {
                alert('Vui lòng nhập tên giấy.');
                return false; 
            }
            return true;
        }
</script>
</html>