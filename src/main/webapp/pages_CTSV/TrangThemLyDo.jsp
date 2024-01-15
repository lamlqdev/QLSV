<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Trang thêm lí do</title>
	<link rel="stylesheet" href="../css/style_CTSV.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<style>
	label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
    </style>
</head>
<body>
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
	    	<form action="<%=request.getContextPath()%>/Ctsv/addlydo" method="post" onsubmit="return validateForm()">
		    	<fieldset style="border:1px solid black; padding: 10px;">
		    		<legend class="my-legend">Thêm lý do</legend>
				    <div style="margin-bottom: 10px;">
				        <label for="tenLydo">Tên lý do:</label>
				        <input type="text" id="tenLydo" name="tenLydo">
				    </div>
				
				    <div style="margin-bottom: 10px;">
				        <label for="noiDung">Nội dung:</label>
				        <textarea id="noiDung" name="noiDung" style="width: 100%; box-sizing: border-box; height: 80px;"></textarea>
				    </div>
				
				    <button class="button" type="submit">Lưu</button>
				    <button class="button" type="button" onclick="location.href='<%=request.getContextPath()%>/Ctsv/listxnnn'">Quay lại</button>
				 </fieldset>
			</form>
		</div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var isSuccess = <%= request.getAttribute("isSuccessInsert") %>;
        if (isSuccess != null) {
            if (isSuccess) {
                alert("Thêm lý do thành công!");
            } else {
                alert("Thêm lý do thất bại!");
            }
        }
    });
    
    function validateForm() {
        var tenLydo = document.getElementById('tenLydo').value.trim();
        var noiDung = document.getElementById('noiDung').value.trim();

        if (tenLydo === '' || noiDung === '') {
            alert('Vui lòng nhập đầy đủ thông tin.');
            return false; 
        }

        return true;
    }
</script>
</html>