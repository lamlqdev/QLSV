<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Models.TaiKhoan"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
         <c:if test="${KhoaHoc != null}">
	     	<form action="<%=request.getContextPath()%>/Qtv/update_KH" method="post">
	     	<h2>Thay đổi khóa học</h2>
	    </c:if>
	    <c:if test="${KhoaHoc == null}">
	     	<form action="<%=request.getContextPath()%>/Qtv/insert_KH" method="post">
	     	<h2>Thêm khóa học</h2>
	    </c:if>
            <div style="width: 100%;"  class ="tables-container">
                
                <div class="form-row">

                    <div class="column">
                    	<c:if test="${KhoaHoc != null}">
					     	<div class="form-group">
		                        <h3>Mã khóa học :</h3>
		                        <input type="text" value="${KhoaHoc.maKH}" name="id" id="makhoahoc" placeholder="Nhập mã khóa học" readonly>  
		                     </div>
					    </c:if>                   		
	            		<div class="form-group">
	                        <h3>Tên khóa học :</h3>
	                        <input type="text" value="${KhoaHoc.tenKH}" name="tenkh" id="tenkhoahoc" placeholder="Nhập tên khóa học ">
	                     </div>
                    </div>

                    <div class="column">  
                    	<div class="form-group">
	                        <h3> Năm bắt đầu :</h3>
	                        <input type="text" value="${KhoaHoc.namBD}"  name="nambd" id="nambatdau" placeholder="Nhập năm bắt đầu">
	                     </div>    
	                        
	                    <div class="form-group">                 
	                        <h3>Năm hết thời gian đào tạo : </h3>          
	                        <input type="text" value="${KhoaHoc.namKT}" name="namkt" id="namketthuc" placeholder="Nhập năm hết thời gian đào tạo ">
	                     </div>
                    </div>            
                </div>                         
            </div>

            <div class="button-contain">
                <button style=" width: 100px;">Lưu</button> 
            </div>
         </form>
         <div class="button-contain">
             <button type="button" onclick="location.href='<%= request.getContextPath() %>/Qtv/listKH'" style="margin-top: 40px;margin-bottom: 40px; background-color: rgb(159, 108, 108);margin-left: 5px; font-size: 20px; height: 40px;">Quay lại </button>
         </div>
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
    </style>
</body>

</html>
<script type="text/javascript">
	var errMsg = '<%=request.getAttribute("errMsg") %>';
	if(errMsg !== 'null')
		 {
		 	alert(errMsg);
		 }
</script>

