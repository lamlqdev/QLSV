<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Models.TaiKhoan"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Trang xác nhận đã lấy</title>
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
    
    	<div class="main-content">    
			<fieldset style="border:1px solid black; padding: 10px;">
	    		<legend class="my-legend">Danh sách chờ xác nhận đã nhận giấy</legend>
	    		<input type="text" id="mssv" name="mssv" placeholder="Nhập mã số sinh viên">
	    		<button id="searchBtn">Tìm kiếm</button>	    	
		        <table class="mytable">
			        <thead>
			            <tr>
			                <th>MSSV</th>
			                <th>Tên giấy xác nhận</th>
			                <th>STT</th>
			                <th>Số lượng</th>
			                <th>Xác nhận đã lấy</th>
			            </tr>
			        </thead>
			        <tbody>
			        	<c:forEach var="gxn" items="${gxns}">
	                        <tr>
	                            <td>${gxn.getMssv()}</td> 	                            
	                            <td>${gxn.getTenLoaiGiay()}</td>
	                            <td>${gxn.getStt()}</td>
	                            <td>${gxn.getSoLuong()}</td>
	                            <td>
						            <a href="confirmGXN?id=${gxn.getID()}">Đã nhận</a>
						        </td>
	                        </tr>        
                    	</c:forEach>   			               
			        </tbody>
			    </table>
			</fieldset>
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
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
    
    $(document).ready(function() {
        $("#searchBtn").click(function() {
            var mssvToSearch = $("#mssv").val();
            
            if (mssvToSearch === "") {
                alert("Vui lòng nhập MSSV để tìm kiếm.");
                return;
            }
            var found = false;

            // Remove previous highlighting
            $("table.mytable tbody tr").css("background-color", "");

            // Loop through table rows
            $("table.mytable tbody tr").each(function() {
                var currentMssv = $(this).find("td:first-child").text();

                if (currentMssv === mssvToSearch) {
                    // Found the matching MSSV, scroll to the row
                    $("html, body").animate({
                        scrollTop: $(this).offset().top
                    }, 500);

                    // Highlight the row
                    $(this).css("background-color", "yellow");

                    found = true;
                    return false; // Stop the loop
                }
            });

            if (!found) {
                // Display a message if MSSV is not found
                alert("Không tìm thấy sinh viên có MSSV: " + mssvToSearch);
            }
        });
    });
</script>
</html>