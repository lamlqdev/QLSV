<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Models.TaiKhoan"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Tải lên tệp Excel</title>
      <link rel="stylesheet" href="../css/style.css">
    
    <style>
    /* Style cho form */
    .upload-form {
        max-width: 600px;
        margin: 0 auto;
        margin-top: 80px;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #f9f9f9;
    }

    /* Style cho nhãn và input file */
    .form-group {
        margin-bottom: 15px;
    }

    label {
        display: block;
         width: 100%;
        margin-bottom: 15px;
        font-weight: bold;
        text-align: center;
        font-size:28px;
    }

    input[type="file"] {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 3px;
        width: 100%;
        font-size:20px;
    }

    /* Style cho button */
    button[type="button"] {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 3px;
        cursor: pointer;
        font-size: 16px;
    }

    button[type="button"]:hover {
        background-color: #45a049;
    }
    
    .error-message {
    	font-style: italic;
        color: red;
        font-size: 20px;
        margin-top: 5px;
    }
     #centerDiv {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            margin-top:220px;
        }
</style>
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
	<jsp:include page="/pages/header.jsp"></jsp:include>
	 
    <div class="upload-form">
	    <form id="uploadForm" action="<%=request.getContextPath()%>/Qtv/insert_SV_excel" method="post" enctype="multipart/form-data">
	        <div class="form-group">
	            <label for="excelFile">Chọn tệp Excel:</label>
	            <input type="file" id="excelFile" name="excelFile" accept=".xlsx, .xls">
	            <div id="fileError" class="error-message" style="display: none;">Vui lòng chọn một tệp.</div>
	        </div>
	        <div class="form-group">
	        	 <button type="button" onclick="location.href='<%= request.getContextPath() %>/Qtv/listSV'" style="background-color: rgb(159, 108, 108);">Quay lại </button>
	            <button type="button" onclick="validateForm()">Tải lên</button>
        </div>        
   		 </div>
	    </form>
	</div>
	<div id="centerDiv"> <!-- Đặt bảng trong một div và căn giữa nó -->
	    <table style="border-collapse: collapse; display: inline-block;"> <!-- Sử dụng inline-block để bảng chỉ chiếm không gian cần thiết -->
	        <thead>
	            <tr style="background-color: #ffcccc;"> <!-- Màu đỏ cho phần tiêu đề -->
	                <th style="font-style: italic; display : none;" id="dong">Dòng</th> <!-- Chữ nghiêng -->
	                <th style="font-style: italic; display : none;" id="noidung">Nội dung</th> <!-- Chữ nghiêng -->
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="err" items="${listERR}">
	                <tr style="border: 1px solid #ddd;"> <!-- Đường viền cho từng dòng -->
	                    <td>${err.getDong()}</td>
	                    <td>${err.getnoidung()}</td>
	                </tr>
	            </c:forEach>
	            <!-- Thêm các hàng dữ liệu khác vào đây -->
	        </tbody>
	    </table>
	</div>
			
</body>
</html>

<script>
    function validateForm() {
        var fileInput = document.getElementById('excelFile');
        var fileError = document.getElementById('fileError');
        
        // Kiểm tra nếu input file rỗng
        if (fileInput.files.length === 0) {
            fileError.style.display = 'block';
        } else {
            fileError.style.display = 'none';
            document.getElementById('uploadForm').submit();
        }
    }
    
    var errMsg = '<%= request.getAttribute("errMsg") %>';
    var succMsg ='<%= request.getAttribute("succMsg") %>';
    if (errMsg !== 'null' && succMsg === 'null') {
        document.getElementById('dong').style.display = 'table-cell';
        document.getElementById('noidung').style.display = 'table-cell';
    } else {
        document.getElementById('dong').style.display = 'none';
        document.getElementById('noidung').style.display = 'none';
    }
    if(errMsg === 'null' && succMsg !=='null'){
    	alert(succMsg );
    }
</script>