<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Models.TaiKhoan"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Trang quản lí hoạt động CTXH</title>
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
	    	<fieldset style="border:1px solid black; padding: 10px;">
	    		<legend class="my-legend">Danh sách chờ xác nhận hoạt động CTXH</legend>
	    		<table class="mytable">
			        <thead>
			            <tr>
			            	<th>MSSV</th>
			                <th>Tên hoạt động</th>			                
			                <th>Điểm CTXH</th>
			                <th>Trạng thái</th>
			                <th>Xác nhận</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach var="xnctxh" items="${xnctxhs}">
	                        <tr>
	                            <td>${xnctxh.getMssv()}</td>  
	                            <td>${xnctxh.getTenHoatDong()}</td> 
	                            <td>${xnctxh.getDiem()}</td>                 
	                            <td>${xnctxh.getTrangThai()}</td>
	                            <td>
						            <a href="confirmctxh?id=${xnctxh.getID()}">Xác nhận</a>
						        </td>
	                        </tr>        
                    	</c:forEach>    
			        </tbody>
	    		</table>
	    	</fieldset>
	    	
	    	<fieldset style="border:1px solid black; padding: 10px;">
	    		<legend class="my-legend">Danh sách đăng kí hoạt động CTXH</legend>
	    		<table class="mytable">
			        <thead>
			            <tr>
			            	<th>MSSV</th>
			                <th>Tên hoạt động</th>			                
			                <th>Điểm CTXH</th>
			                <th>Thời gian đăng kí</th>
			                <th>Trạng thái</th>
			                <th>Duyệt</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach var="dkictxh" items="${dkictxhs}">
	                        <tr>
	                            <td>${dkictxh.getMssv()}</td>  
	                            <td>${dkictxh.getTenHoatDong()}</td> 
	                            <td>${dkictxh.getDiem()}</td>                 
	                            <td>${dkictxh.getThoiGianDangKi()}</td>
	                            <td>${dkictxh.getTrangThai()}</td>
	                            <td>
						            <a href="acceptdkictxh?id=${dkictxh.getID()}">Duyệt</a>
						        </td>
	                        </tr>        
                    	</c:forEach>    
			        </tbody>
	    		</table>
	    	</fieldset>
	    	
	    	<button class="button-add" type="button" onclick="window.location.href='<%= request.getContextPath() %>/Ctsv/listhdctxh'"> Quản lí các hoạt động CTXH</button>
	    </div>       
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
        $(document).ready(function() {
            var isSuccess = <%= request.getAttribute("isSuccess") %>;
            if (isSuccess != null) {
                if (isSuccess) {
                    alert("Duyệt thành công!");
                } else {
                    alert("Duyệt thất bại!");
                }
            }
        });
        
        $(document).ready(function() {
            var isSuccess = <%= request.getAttribute("isSuccessConfirm") %>;
            if (isSuccess != null) {
                if (isSuccess) {
                    alert("Xác nhận thành công!");
                } else {
                    alert("Xác nhận thất bại!");
                }
            }
        });
</script>
</html>