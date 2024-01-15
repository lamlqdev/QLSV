<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Models.TaiKhoan"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Trang chủ cộng tác sinh viên</title>
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
				<li><a href="<%= request.getContextPath()%>/Ctsv/listTB">Trang chủ</a></li>
                <li><a href="<%=request.getContextPath()%>/Ctsv/info">Thông tin cá nhân</a></li>
                <li><a href="<%=request.getContextPath()%>/Ctsv/listgxn">Quản lý giấy xác nhận</a></li>
                <li><a href="<%=request.getContextPath()%>/Ctsv/listxnnn">Quản lý giấy xác nhận ngành nghề</a></li>
                <li><a href="<%=request.getContextPath()%>/Ctsv/listdkictxh">Quản lý hoạt động CTXH</a></li>
			</ul>
		</div>
	
		<!-- Phần content -->
		<div class="main-content">  
			<input type="radio" id="Khoa" name="option" value="khoa">
			<label for="Khoa" style="font-size: 20px; margin-bottom:10px; margin-right:20px;" >Khoa</label>
			<input type="radio" id="Khoahoc" name="option" value="khoahoc"> 
			<label for="Khoahoc" style="font-size: 20px; margin-bottom:10px">Khóa học</label>
		
			<div id="khoaDropdown" style="width:200px; display:none;">
				<select id="selectKhoa" onchange="showSelectKhoa()" style="font-size: 20px; margin-bottom:20px; border-radius:5px">
					<c:forEach var="khoa" items="${listKhoa}">
	                       <option value="${khoa.tenKhoa}">${khoa.tenKhoa}</option>			                        
		             </c:forEach>   
				</select>
			</div>
		
			<div id="khoahocDropdown" style="width:200px; display:none;">
				<select id="selectKhoaHoc" onchange="showSelectKhoaHoc()" style="font-size: 20px; margin-bottom:20px; border-radius:5px; width:200px;">
					<c:forEach var="KH" items="${listKH}">
	                   	<option value="${KH.getTenKH()}">${KH.getTenKH()}</option>			                                     
			       	</c:forEach>  
				</select>
			</div>

			<div>
				<h5>Số yêu cầu chưa xác nhận: <span id="rowCount" style="color:red">15</span></h5>
			</div>

			<div style="width: 100%;" class ="tables-container">
				<table id="myTable" border="1" style="width: 100%; border-collapse: collapse; font-size: 20px;">                       
					<h3>Thông tin sinh viên </h3> 
					<thead>
						<tr>
							<th>Tên dịch vụ</th>
							<th>Người gửi </th>
							<th>Thời điểm gửi </th>
							<th >Tên khoa</th> <!-- Cột ẩn đi -->
							<th >Tên khóa học</th> <!-- Cột ẩn đi -->
							
						</tr>
					</thead>
					<tbody>
					   <c:forEach var="tb" items="${listTB}">
							<tr>
								<td>${tb.getTenDV()}</td>
								<td>${tb.getTenSV()}</td>  
								<td>${tb.getTGGui()}</td>    
								<td >${tb.getTenKhoa()}</td>  
								<td >${tb.getTenKH()}</td>               
							</tr>							
						</c:forEach>
					</tbody>            
				</table>
			</div>
		</div>
	</div>
	
</body>
<script>
	// Lắng nghe sự kiện onchange của input radio
	document.querySelectorAll('input[type="radio"]').forEach(function(radio) {
		radio.addEventListener('change', function() {
			// Ẩn tất cả các dropdown trước khi hiển thị dropdown tương ứng
			document.getElementById('khoaDropdown').style.display = 'none';
			document.getElementById('khoahocDropdown').style.display = 'none';

			// Nếu chọn radio Khoa, hiển thị dropdown Khoa và ngược lại
			if (this.value === 'khoa') {
				document.getElementById('khoaDropdown').style.display = 'block';
			} else if (this.value === 'khoahoc') {
				document.getElementById('khoahocDropdown').style.display = 'block';
			}
		});
	});
	
	function showSelectKhoa() {
	    var selectedValue = document.getElementById("selectKhoa").value;
	    var rowCount = 0;
	    var table = document.getElementsByTagName("table")[0];
	    var rows = table.getElementsByTagName("tr");

	    for (var i = 0; i < rows.length; i++) {
	        var cells = rows[i].getElementsByTagName("td");

	        if (cells.length >= 4) { // Kiểm tra xem hàng có tồn tại ô dữ liệu thứ tư hay không
	            var cellValue = cells[3].innerText || cells[3].textContent;

	            if (cellValue === selectedValue ) {
	                rows[i].style.display = "table-row";
	                rowCount++;
	            } else {
	                rows[i].style.display = "none";
	            }
	        }
	    }
	    var rowCountSpan = document.getElementById("rowCount");
		rowCountSpan.textContent = rowCount;
	}
	
	function showSelectKhoaHoc() {
	    var selectedValue = document.getElementById("selectKhoaHoc").value;
	    var rowCount = 0;
	    var table = document.getElementsByTagName("table")[0];
	    var rows = table.getElementsByTagName("tr");

	    for (var i = 0; i < rows.length; i++) {
	        var cells = rows[i].getElementsByTagName("td");

	        if (cells.length >= 4) { // Kiểm tra xem hàng có ít nhất 4 ô dữ liệu hay không
	            var cellValue = cells[4].innerText || cells[4].textContent;

	            if (cellValue === selectedValue ) {
	                rows[i].style.display = "table-row";
	                rowCount++;
	            } else {
	                rows[i].style.display = "none";
	            }
	        }
	    }
	    var rowCountSpan = document.getElementById("rowCount");
		rowCountSpan.textContent = rowCount;
	}
	
	var table = document.getElementById("myTable");
	var rows = table.getElementsByTagName("tr");
	var rowCount = 0;

	for (var i = 0; i < rows.length; i++) {
	    if (window.getComputedStyle(rows[i]).display === "table-row") {
	        rowCount++;
	    }
	}
	rowCount = rowCount-1;
	var rowCountSpan = document.getElementById("rowCount");
	rowCountSpan.textContent = rowCount;

</script>
</html>
	
	