<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <!-- Phần header -->
	<div class="image-container">
	    <img border="0" src="../img/SPKT.jpg" alt="Ảnh" class="full-width-img">
	</div>
	<div class="button-container" style ="margin-top :0;">
	    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #00558c">
	        <ul class="navbar-nav navbar-collapse justify-content-end">
	            <li><a href="MainPage.jsp" class="nav-link">Trang chủ</a></li>
	            <li><a href="<%= request.getContextPath()%>/pages/login.jsp" class="nav-link">Đăng nhập</a></li>
	        </ul>
	    </nav>
	</div>

    <!-- Phần Body -->
    <div class="wrapper">
    	<div class="main-content_Page">
		    <img border="0" src="../img/Truong_HCMUTE.jpg" alt="Ảnh" class="main-page">
		</div>
    </div>
    
</body>
</html>

