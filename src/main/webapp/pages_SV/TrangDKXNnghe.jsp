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
	    .col3 { width: 50%; }
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
                <li><a href="<%=request.getContextPath()%>/SV/DKXNNN" class="special-link">Xác nhận ngành nghề</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/DKGXN">Đăng kí giấy xác nhận</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/CTXH" >Đăng kí tham gia CTXH</a></li>
            </ul>
        </div>
    
        <!-- Phần content -->
        <div class="main-content">
            <div class="row">
			  <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
			
			  <div class="container">
			   <h3 class="text-center">Xác nhận ngành nghề độc hại</h3>
			   <hr>
			   <form action="<%=request.getContextPath()%>/SV/dk_DKXNNN" method="post">
				   <div class="container text-left">
					<label for="namhoc">Năm học:</label>
					<input type="hidden" id="namhoctext" name="namhoctext">
				    <select id="namhoc" name="namhoc" onchange="updateNamHocText()">
				    </select>
					&emsp;
				    <label for="hocky">Học kỳ:</label>
				    <select id="hocky" name="hocky">
				        <option value="HK01">HK01</option>
				        <option value="HK02">HK02</option>
				        <option value="HK03">HK03</option>
				        <!-- Thêm các tùy chọn khác ở đây -->
				    </select>
				    &emsp;
				    <label for="lydo">Lý do:</label>
				    <select id="lydo" name="lydo">
				        <c:forEach var="ld" items="${listLD}">
							<option value="${ld.maLyDo}">${ld.tenLyDo}</option>			             
						</c:forEach>
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
				      <th>Năm học</th>
				      <th>Học kỳ</th>
				      <th class="col3">Lý do</th>
				      <th>Trạng thái</th>
				      <th>Ngày đăng ký</th>
				     </tr>
				    </thead>
				    <tbody>
				     <!--   for (Todo todo: todos) {  -->
				     <c:forEach var="dkxnnn" items="${listDKXNNN}">
				
				      <tr>
				       <td><c:out value="${dkxnnn.namHoc}" /></td>
				       <td><c:out value="${dkxnnn.hocKy}" /></td>
				       <td class="col3"><c:out value="${dkxnnn.NDLyDo}" /></td>
				       <td><c:out value="${dkxnnn.trangThai}" /></td>
					   <td><c:out value="${dkxnnn.thoiGianDK.format(DateTimeFormatter.ofPattern('dd-MM-yyyy'))}" /></td>
				
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
				    <legend class="my-legend">Lí do</legend>
				    <p id="detail"> 1. Cơ khí: Hàn điện, hàn hơi; Mài khô kim loại; Vận hành máy đột dập kim loại; Tiếp xúc với bụi đá, bụi kim loại, rung và ồn, căng thẳng thị giác.</p>
				    <p id="detail"> 2. Điện, điện tử: ảnh hưởng của điện từ trường, thường xuyên tiếp xúc với điện cao áp nên rất nguy hiểm đến tính mạng. Tiếp xúc với các chất độc hại như: chì, nhựa thông và các chất tẩy rửa bo mạch.</p>
				    <p id="detail"> 3. Công nghệ thông tin & máy tính: Công việc đơn điệu, thường xuyên tiếp xúc với màn hình máy tính, căng thẳng thần kinh, tâm lý.</p>
				    </fieldset>
			   </legend>
			   
			  </div>
			 </div>
			</div>
    </div>
	<script>
	    var startYear = ${SinhVien.namBD};
	    var endYear = ${SinhVien.namKT};
	
	    window.onload = function() {
	        var select = document.getElementById("namhoc");
	
	        for (var year = startYear; year < endYear; year++) {
	            var opt = document.createElement('option');
	            opt.value = 'option' + (year - startYear + 1);
	            opt.innerHTML = year + "-" + (year + 1);
	            select.appendChild(opt);
	        }
	        updateNamHocText();
		    }
	
		    function updateNamHocText() {
		        var select = document.getElementById("namhoc");
		        var selectedOption = select.options[select.selectedIndex];
		        var namHocText = selectedOption.innerText || selectedOption.textContent;
	
		        document.getElementById("namhoctext").value = namHocText;
		    }
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

