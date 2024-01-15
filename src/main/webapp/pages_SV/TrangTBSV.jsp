<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%@ page import="Models.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../css/style_SV.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
	    .col1 { width: 20%; }
	    .col2 { width: 15%; }
	    .col3 { width: 16%; }
	    .col4 { width: 10%; }
	    .col5 { width: 25%; }
	    .col6 { width: 10%; }
	    .selected-row {
		    background-color: #f0f0f0; /* Đổi màu nền của hàng khi được chọn */
		}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
		response.sendRedirect(request.getContextPath() + "pages/login.jsp");
	%>
	<%
	} 
	%>		
    <!-- Phần Body -->
    <div class="wrapper">

        <!-- Phần sidebar -->
        <div class="sidebarnew">
            <ul class="menu">      
                 <li><a href="<%=request.getContextPath()%>/SV/thongbao" class="special-link">Trang của bạn</a></li>      
                <li><a href="<%=request.getContextPath()%>/SV/info">Thông tin cá nhân</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/DKXNNN" >Xác nhận ngành nghề</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/DKGXN">Đăng kí giấy xác nhận</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/CTXH" >Đăng kí tham gia CTXH</a></li>
            </ul>
        </div>
    	<div class="main-content">
            <div class="row">
			  <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
			
			  <div class="container">
			   <h3 class="text-center">Thông Báo </h3>
			   <hr>
			   <div class="container text-left">
			
			    <a href="<%=request.getContextPath()%>/SV/thongbao"
			     class="btn btn-success">Reload</a>
			   </div>
			   <br>
			   <div class="table-wrapper">
    			<table class="table table-bordered">
			    <thead>
			     <tr>
			      <th>Tiêu đề</th>
			      <th>Người gửi</th>
			      <th>Thời điểm gửi</th>
			      <th style="display: none;">Nội dung</th>
			     </tr>
			    </thead>
			    <tbody >
			    
			     <!--   for (Todo todo: todos) {  -->
					    <c:forEach var="tb" items="${listTB}">				
						      <tr>
							       <td> ${tb.tieuDe}</td>
							       <td>${tb.getTenCTSV()}</td>
							       <td>${tb.tgPhanHoi.format(DateTimeFormatter.ofPattern('dd-MM-yyyy HH:mm:ss'))}</td>			
							       <td style="display: none;">${tb.getNDPhanHoi()}</td>
						      </tr>				      				      				   
					     </c:forEach>
					     
					      
			     <!-- } -->
			    </tbody>
			
			   </table>
			    </div>
			   <br>
			   <legend class="my-legend">
				  <fieldset style="border:1px solid black; padding: 10px;">
				    <legend class="my-legend">Nội dung</legend>
				    <p id="detail"> </p>
				  </fieldset>
			   </legend>
			   
			  </div>
			 </div>
		</div>
		
		
    </div>
    
</body>
</html>
<script>
	
	$(document).ready(function() {
	    $('tbody tr').click(function() {
	        var content = $(this).find('td:last-child').text(); // Lấy nội dung của cột cuối cùng
	        $('#detail').text(content); // Hiển thị nội dung tương ứng ở phần nội dung
	    });
	});
	
	$(document).ready(function() {
	    // Xử lý sự kiện click trên hàng của bảng
	    $('tbody tr').click(function() {
	        // Loại bỏ lớp 'selected-row' từ tất cả các hàng
	        $('tbody tr').removeClass('selected-row');
	        // Thêm lớp 'selected-row' vào hàng được nhấp
	        $(this).addClass('selected-row');
	        
	        // Hiển thị nội dung tương ứng ở phần nội dung
	        var content = $(this).find('td:last-child').text();
	        $('#detail').text(content);
	    });
	});
</script>

