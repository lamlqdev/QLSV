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
	    .col1 { width: 18%; }
	    .col2 { width: 30%; }
	    .col3 { width: 10%; }
	    .col4 { width: 10%; }
	    .col5 { width: 25%; }
	    .col6 { width: 10%; }
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
		response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
	%>
	<%
	} 
	%>		
    <!-- Phần Body -->
    <div class="wrapper">

        <!-- Phần sidebar -->
        <div class="sidebarnew">
            <ul class="menu">
                <li><a href="#">Trang của bạn</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/info">Thông tin cá nhân</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/DKXNNN">Xác nhận ngành nghề</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/DKGXN">Đăng kí giấy xác nhận</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/CTXH" class="special-link">Đăng kí tham gia CTXH</a></li>
            </ul>
        </div>
    
        <!-- Phần content -->
        <div class="main-content">
            <div class="row">
			  <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
			
			  <div class="container">
			   <h3 class="text-center">Đăng kí hoạt động công tác xã hội</h3>
			   <hr>
			  <legend class="my-legend"> 
			  	<fieldset style="border:1px solid black; padding: 10px;">
				    <legend class="my-legend">Danh sách hoạt động</legend>
					   <table class="table table-bordered table-custom">
					    <thead>
					     <tr>
					      <th class="col1">Tên hoạt động</th>
					      <th class="col2">Nội dung</th>
					      <th>Ngày diễn ra</th>
					      <th>Ngày kết thúc</th>
					      <th>Điểm CTXH</th>
					      <th class="col6">Đăng kí</th>
					     </tr>
					    </thead>
					    <tbody>
					     <!--   for (Todo todo: todos) {  -->
					     <c:forEach var="dsctxh" items="${listCTXH}">
					
					      <tr>
					       <td class="col1"><c:out value="${dsctxh.tenHD}" /></td>
					       <td class="col2"><c:out value="${dsctxh.noiDung}" /></td>
					       <td><c:out value="${dsctxh.tgDienRa.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}" /></td>
					       <td><c:out value="${dsctxh.tgKetThuc.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}" /></td>
						   <td><c:out value="${dsctxh.diem}" /></td>
						   <td class="col6"><a href="dk_CTXH?maHD=<c:out value='${dsctxh.maHD}'/>&tenHD=<c:out value='${dsctxh.tenHD}'/>">Đăng kí</a>
					
					       <!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
					                 <button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
					      </tr>
					     </c:forEach>
					     <!-- } -->
					    </tbody>
					   </table>
					</fieldset>
				</legend>
			   
			   <br>
			   <legend class="my-legend"> 
			  	<fieldset style="border:1px solid black; padding: 10px;">
				    <legend class="my-legend">Các hoạt động đã đăng kí</legend>
					   <table class="table table-bordered table-custom">
					    <thead>
					     <tr>
					      <th>Tên hoạt động</th>
					      <th>Nội dung</th>
					      <th>Ngày đăng kí</th>
					      <th>Điểm CTXH</th>
					      <th>Trạng thái</th>
					     </tr>
					    </thead>
					    <tbody>
					     <!--   for (Todo todo: todos) {  -->
					     <c:forEach var="dkctxh" items="${listDKCTXH}">
					
					      <tr>
					       <td><c:out value="${dkctxh.tenHD}" /></td>
					       <td><c:out value="${dkctxh.noiDung}" /></td>
					       <td><c:out value="${dkctxh.thoiGianDK.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}" /></td>
						   <td><c:out value="${dkctxh.diem}" /></td>
						   <td><c:out value="${dkctxh.trangThai}" /></td>
					       <!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
					                 <button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
					      </tr>
					     </c:forEach>
					     <!-- } -->
					    </tbody>
					   </table>
					   <div class="container text-right table-custom">
						<label for="combo1">Điểm CTXH:</label>
					   </div>	
					</fieldset>
				</legend>
			   
			  </div>
			 </div>
			</div>
    </div>
    
</body>
</html>

