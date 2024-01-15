<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Models.TaiKhoan"%>
<%@ page import="Models.HoatDongCTXH"%>
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
	TaiKhoan account = (TaiKhoan)session.getAttribute("Acc");
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
	    	<c:if test="${hdctxh != null}">
	     		<form action="<%=request.getContextPath()%>/Ctsv/confirmupdatectxh?id=${hdctxh.getMaHoatDong()}" method="post">
		    </c:if>
		    <c:if test="${hdctxh == null}">
		     	<form action="<%=request.getContextPath()%>/Ctsv/inserthdctxh" method="post">
		    </c:if>
	    	<form action="<%=request.getContextPath()%>/Ctsv/confirmupdatectxh?id=${hdctxh.getMaHoatDong()}" method="post">
	    	<fieldset style="border:1px solid black; padding: 10px;">
	    		<legend class="my-legend">Hoạt động</legend>
			        <div style="display: flex; flex-direction: column;">
				        <div style="display: flex; justify-content: space-between; margin-bottom: 10px;">
				            <div style="flex: 1; margin-right: 5px;">
				                <label for="tenHoatDong" style="font-weight: bold;">Tên hoạt động</label>
				                <input type="text" id="tenHoatDong" name="tenHoatDong" value="${hdctxh.getTenHoatDong()}">
				            </div>
				            <div style="flex: 1; margin-left: 5px;">
				                <label for="diem" style="font-weight: bold;">Điểm</label>
				                <input type="text" id="diem" name="diem" value="${hdctxh.getDiem()}">
				            </div>
				        </div>
				
				        <div style="display: flex; justify-content: space-between; margin-bottom: 10px;">
				            <div style="flex: 1; margin-right: 5px;">
				                <label for="thoiGianBatDau" style="font-weight: bold;">Thời gian bắt đầu</label>
				                <input type="date" id="thoiGianBatDau" name="thoiGianBatDau" value="${hdctxh.getThoiGianDienRa()}">
				            </div>
				            <div style="flex: 1; margin-left: 5px;">
				                <label for="thoiGianKetThuc" style="font-weight: bold;">Thời gian kết thúc</label>
				                <input type="date" id="thoiGianKetThuc" name="thoiGianKetThuc" value="${hdctxh.getThoiGianKetThuc()}">
				            </div>
				        </div>
				        
				        <div style="flex: 2; margin-left: 5px;">
				                <label for="content" style="font-weight: bold;">Nội dung:</label>
				                <textarea id="noiDung" name="noiDung" style="width: 100%; box-sizing: border-box; height: 80px;">${hdctxh.getNoiDung()}</textarea>
				        </div>
				            
				        <div class="buttons" style="margin-top: 10px; display: flex; justify-content: space-between;">
				            <button class="button" type="submit">Lưu</button>
				            <button class="button" type="button" onclick="window.location.href='<%= request.getContextPath() %>/Ctsv/listhdctxh'">Hủy</button>
				        </div>
				    </div>				 
			</fieldset>
			</form>
	    </div>
    </div>
</body>
</html>