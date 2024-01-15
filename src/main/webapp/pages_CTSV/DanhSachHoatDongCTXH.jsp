<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Models.TaiKhoan"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Danh sách hoạt động công tác xã hội</title>
	<link rel="stylesheet" href="../css/style_CTSV.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
    <jsp:include page="header.jsp"></jsp:include>
    
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
        	<table class="mytable">
		        <thead>
		            <tr>
		                <th>Tên hoạt động</th>
		                <th>Thời gian bắt đầu</th>
		                <th>Thời gian kết thúc</th>
		                <th>Điểm CTXH</th>
		                <th>Nội dung</th>
		                <th>Chỉnh sửa</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:forEach var="hdctxh" items="${hdctxhs}">
	                        <tr>
	                            <td>${hdctxh.getTenHoatDong()}</td>  
	                            <td>${hdctxh.getThoiGianDienRa()}</td> 
	                            <td>${hdctxh.getThoiGianKetThuc()}</td>                 
	                            <td>${hdctxh.getDiem()}</td>
	                            <td>${hdctxh.getNoiDung()}</td>
	                            <td>
						            <a href="updatehdctxh?id=${hdctxh.getMaHoatDong()}">Chỉnh sửa</a>
						        </td>
	                        </tr>        
                    </c:forEach>
		        </tbody>
	    	</table>
		    
		    <button class="button-add" type="button" onclick="window.location.href='<%= request.getContextPath() %>/Ctsv/updatehdctxh'">Thêm hoạt động</button>
   		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
        $(document).ready(function() {
            var isSuccess = <%= request.getAttribute("isSuccess") %>;
            if (isSuccess != null) {
                if (isSuccess) {
                    alert("Sửa thành công!");
                } else {
                    alert("Sửa thất bại!");
                }
            }
            
            var isSuccess = <%= request.getAttribute("isSuccessInsert") %>;
            if (isSuccess != null) {
                if (isSuccess) {
                    alert("Thêm thành công!");
                } else {
                    alert("Thêm thất bại!");
                }
            }
        });
</script>
</html>