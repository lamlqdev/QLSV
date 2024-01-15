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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
    <link rel="stylesheet" href="../css/style_SV.css">
    <style>
	    .col1 { width: 20%; }
	    .col2 { width: 15%; }
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
                <li><a href="<%=request.getContextPath()%>/SV/thongbao">Trang của bạn</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/info">Thông tin cá nhân</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/DKXNNN">Xác nhận ngành nghề</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/DKGXN" class="special-link">Đăng kí giấy xác nhận</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/CTXH">Đăng kí tham gia CTXH</a></li>
            </ul>
        </div>
    
        <!-- Phần content -->
        <div class="main-content">
            <div class="row">
			  <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
			
			  <div class="container">
			   <h3 class="text-center">Đăng kí Giấy Xác Nhận</h3>
			   <hr>
			   <form action="<%=request.getContextPath()%>/SV/dk_DKGXN" method="post">
				    <div class="container text-left">
				        <label for="loaigiay">Loại giấy:</label>
				        <input type="hidden" id="tenLoaiGiay" name="tenLoaiGiay">
				        <select id="loaigiay" name="loaigiay">
				            <c:forEach var="lg" items="${listLG}">
				                <option value="${lg.maLoaiGiay}">${lg.tenLoaiGiay}</option>			             
				            </c:forEach>  
				        </select>
				        &emsp;&emsp;
				        <label for="soluong">Số lượng:</label>
				        <select id="soluong" name="soluong">
				            <option value="1">1</option>
				            <option value="2">2</option>
				        </select>
				        &emsp;&emsp;&emsp;&emsp;
				        <input type="submit" class="btn btn-success" value="Đăng ký">
				    </div>
				</form>
			   <br>
			   <div style="height: 300px; overflow: auto;">
				   <table class="table table-bordered">
				    <thead>
				     <tr>
				      <th>Thời gian đăng kí</th>
				      <th>Tên giấy xác nhận</th>
				      <th class="col3">Số thự tự</th>
				      <th class="col4">Số lượng</th>
				      <th class="col5">Nội dung phản hồi</th>
				      <th class="col6">Tình trạng</th>
				     </tr>
				    </thead>
				    <tbody>
				     <!--   for (Todo todo: todos) {  -->
				     <c:forEach var="dkgxn" items="${listDKGXN}">
				      <tr>
				       <td><c:out value="${dkgxn.thoiGianDK.format(DateTimeFormatter.ofPattern('dd-MM-yyyy HH:mm:ss'))}" /></td>
				       <td><c:out value="${dkgxn.tenDichVu}" /></td>
				       <td class="col3"><c:out value="${dkgxn.STT}" /></td>
				       <td class="col4"><c:out value="${dkgxn.soLuong}" /></td>
					   <td class="col5"><c:out value="${dkgxn.NDPhanHoi}" /></td>
				       <td class="col6"><c:out value="${dkgxn.trangThai}" /></td>
				
				       <!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
				                 <button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
				      </tr>
				     </c:forEach>
				     <!-- } -->
				    </tbody>
				
				   </table>
			   </div>
			   <br>
			   
			   <legend class="my-legend">
				  <fieldset style="border:1px solid black; padding: 10px;">
				    <legend class="my-legend">Ghi chú</legend>
				    <p id="detail"> Quy trình nhận Giấy xác nhận đã đăng ký:</p>
				    <p id="detail"> 1. Sinh viên nhận Giấy theo thời gian nhận sẽ được thông báo sau khi được duyệt.</p>
				    <p id="detail"> 2. Sinh viên liên hệ Phòng Tuyển sinh và Công tác sinh viên A1-203 để nhận giấy. Buổi sáng từ 7h00 đến 11h30, buổi chiều từ 13h đến 16h30 các ngày từ thứ hai đến thứ sáu và sáng thứ bảy.</p>
				    <p id="detail"> 3. Sinh viên cung cấp Số thứ tự và họ tên cho nhân viên trả giấy.</p>
				    <p id="detail"> 4. Thời hạn nhận giấy xác nhận đã đăng ký là 2 tuần, sau thời gian trên các loại giấy quá hạn sẽ bị hủy.</p>
				  </fieldset>
			   </legend>
			   
			  </div>
			 </div>
			</div>
    </div>
    <script>
	    var loaigiaySelect = document.getElementById('loaigiay');
	    var tenLoaiGiayInput = document.getElementById('tenLoaiGiay');
	
	    // Hàm cập nhật giá trị
	    function updateTenLoaiGiay() {
	        var selectedOption = loaigiaySelect.options[loaigiaySelect.selectedIndex];
	        tenLoaiGiayInput.value = selectedOption.text;
	    }
	
	    // Cập nhật giá trị ngay khi trang tải lên
	    updateTenLoaiGiay();
	
	    // Cập nhật giá trị khi người dùng thay đổi lựa chọn
	    loaigiaySelect.addEventListener('change', updateTenLoaiGiay);
	</script>
	<% if (session.getAttribute("status") != null) { %>
	    <script>
	        $(document).ready(function() {
	            var status = '<%=session.getAttribute("status")%>';
	            console.log('Status: ' + status);
	            if (status == 'success') {
	                alert('Đăng ký thành công!');
	            } else if (status == 'failure') {
	                alert('Đăng ký thất bại!');
	            }
	        });
	    </script>
	    <% session.removeAttribute("status"); %>
	<% } %>

</body>
</html>





