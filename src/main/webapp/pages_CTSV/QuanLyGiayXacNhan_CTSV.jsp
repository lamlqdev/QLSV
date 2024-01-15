<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Models.TaiKhoan"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Trang quản lí giấy xác nhận</title>
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
        
	    <!-- Phần nội dung -->
	    <div class="main-content">
			<fieldset style="border:1px solid black; padding: 10px;">
	    		<legend class="my-legend">Danh sách đăng kí giấy xác nhận</legend>
				<label for="loaigiay">Chọn loại giấy xác nhận:</label>
				<select id="loaigiayCombobox">				
		        	<c:forEach var="lg" items="${lgs}">
				        <option value="${lg.getMaLoaiGiay()}">${lg.getTenLoaiGiay()}</option>
				    </c:forEach>
				    <option value="xemTatCa">Xem tất cả</option>
		    	</select>
		    	<button class="button add-button" type="button" onclick="redirectToThemGiay()">Thêm loại giấy</button>
		    	
		        <table class="mytable">
			        <thead>
			            <tr>
			                <th>Thời gian đăng kí</th>
			                <th>MSSV</th>
			                <th>Tên giấy xác nhận</th>
			                <th>STT</th>
			                <th>Số lượng</th>
			                <th>Duyệt</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach var="gxn" items="${gxns}">
	                        <tr>
	                            <td>${gxn.getThoiGianDangKi()}</td>  
	                            <td>${gxn.getMssv()}</td> 	                            
	                            <td>${gxn.getTenLoaiGiay()}</td>
	                            <td>${gxn.getStt()}</td>
	                            <td>${gxn.getSoLuong()}</td>
	                            <td>
						            <a href="acceptgxn?id=${gxn.getID()}">Duyệt</a>
						        </td>
	                        </tr>        
                    	</c:forEach>     
			        </tbody>
			    </table>
		    </fieldset>
			<button class="button-add" type="button" onclick="window.location.href='<%= request.getContextPath() %>/Ctsv/listConfirmAcceptGXN'">Xác nhận sinh viên đã nhận giấy</button>
   		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	var maLoaiGiayParam = new URLSearchParams(window.location.search).get('maLoaiGiay');
	
	// Nếu giá trị tồn tại, thiết lập giá trị cho select element
	if (maLoaiGiayParam !== null) {
	    document.getElementById('loaigiayCombobox').value = maLoaiGiayParam;
	}
    document.getElementById('loaigiayCombobox').onchange = function () {
        var selectedValue = this.value;
        window.location.href = '${pageContext.request.contextPath}/Ctsv/listgxn?maLoaiGiay=' + encodeURIComponent(selectedValue);
    };
    
    function redirectToThemGiay() {
        window.location.href = '<%=request.getContextPath()%>/pages_CTSV/TrangThemGiayXacNhan.jsp';
    }
    
    $(document).ready(function() {
        var isSuccess = <%= request.getAttribute("isSuccess") %>;
        if (isSuccess != null) {
            if (isSuccess) {
                alert("Duyệt thành công!");
            } else {
                alert("Duyệt thất bại!");
            }
        }
    });
</script>
</html>