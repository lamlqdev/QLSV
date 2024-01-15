<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
<!-- Phần header -->
	<div class="image-container">
	    <img border="0" src="../img/SPKT.jpg" alt="Ảnh" class="full-width-img">
	</div>
	<div class="button-container">
	    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #00558c">
	        <ul class="navbar-nav navbar-collapse justify-content-end">
	            <li><a href="<%= request.getContextPath() %>/Ctsv/listTB" class="nav-link">Trang chủ</a></li>
	            <li><a href="<%=request.getContextPath()%>/logout" class="nav-link">Đăng xuất</a></li>
	        </ul>
	    </nav>
	</div>
</header>