<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Models.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../css/style_SV.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<style type="text/css">
		.tdnew {
		    padding-left: 20px; /* Thêm dòng này để tạo khoảng cách giữa các thẻ <td> */
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
		response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
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
                  <li><a href="<%=request.getContextPath()%>/SV/thongbao" >Trang của bạn</a></li>      
                <li><a href="<%=request.getContextPath()%>/SV/info" class="special-link">Thông tin cá nhân</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/DKXNNN" >Xác nhận ngành nghề</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/DKGXN">Đăng kí giấy xác nhận</a></li>
                <li><a href="<%=request.getContextPath()%>/SV/CTXH" >Đăng kí tham gia CTXH</a></li>
            </ul>
        </div>
    
        <!-- Phần content -->
        <div class="main-content">
            <div class="tables-container">
            	<div class="frame-container">
	            	<div class="label-frame">
		                <!-- Bảng đầu tiên -->
		                <div class="table-wrapper"  >
		                    <table >
		                        
		                        <h3>Thông tin cá nhân </h3> 
		                            
		                        <tbody>
		                        	<tr>
		                                <td style="width: 190px">Mã số sinh viên</td>
		                                <td class="tdnew">: <c:out value="${ttSV.MSSV}"/></td>                     
		                            </tr>
		                            
		                            <tr>
		                                <td>Họ tên</td>
		                                <td class="tdnew">: <c:out value="${ttSV.hoTen}"/></td>                     
		                            </tr>
		                            
		                            <tr>
		                                <td>Ngày sinh</td>
		                                <td class="tdnew">: <c:out value="${ttSV.ngaySinh}"/></td>                     
		                            </tr>
		                            
		                            <tr>
		                                <td>Giới tính</td>
		                                <td class="tdnew">: <c:out value="${ttSV.gioiTinh}"/></td>                     
		                            </tr>
		                            
		                            <tr>
		                                <td>Quê quán</td>
		                                <td class="tdnew">: <c:out value="${ttSV.queQuan}"/></td>                     
		                            </tr>
		        
		                            <tr>
		                                <td>Tình trạng</td>
		                                <td class="tdnew">: <c:out value="${ttSV.trangThai}"/></td>                     
		                            </tr>
		        					
		        					<tr>
		                                <td>Khoa</td>
		                                <td class="tdnew">: <c:out value="${ttSV.tenKhoa}"/></td>                     
		                            </tr>
		                            
		                            <!-- Thêm các hàng dữ liệu khác vào đây -->
		                        </tbody>            
		                    </table>
		        
		                </div>
		            </div>
		            
		            <div class="label-frame">
		                <!-- Bảng thứ hai -->
		                <div class="table-wrapper">
		                    <table >
		                        <h3>Thông tin liên lạc </h3> 
		
		                        <tbody>
		                            
		                            <tr>
		                                <td>Điện thoại: </td>
		                                <td>
		                                    <input type="text" name ="phoneInput"  id="phoneInput" value="${ttSV.getSoDienThoai()}" style="width: 230px; font-size: 18px;" disabled>
		       								 <span id="phoneError" style="color: red;"></span>
		                                </td>                    
		                            </tr>
		                            <tr>
	    							<td colspan="2">&nbsp;</td>
									</tr>
		                            <tr>
		                                <td>Email: </td>
		                                <td>
		                                    <input type="text" name ="emailInput" id="emailInput" value="${ttSV.getEmail()}" style="width: 230px; font-size: 18px;" disabled>
                							<span id="emailError" style="color: red;"></span>
		                                </td>                     
		                            </tr>
		                            <tr>
	    							<td colspan="2">&nbsp;</td>
									</tr>
		                            <tr>
		                                <td>
		                                     <button class="button edit-button" onclick="enableInputs()">Cập nhật</button>                                      
		                                </td>
		
		                                <td>
		                                    
									        <button class="button save-button" onclick="updateTT()" >Lưu</button>
							                <button class="button cancel-button" onclick="cancelChanges()" >Hủy</button>                                     
		                               </td>
		                               
		                            </tr>                                            
		                           
		                    </table>
		                </div>
		        	</div>
		        </div>
                <div class="frame-container">
	                <div class="label-frame">
		                <!-- Bảng thứ ba -->
		                <div class="table-wrapper">
		                    <table >
		                        <h3>Thông tin khóa học</h3> 
		                        
		                        <tbody>
		                            <tr>
		                                <td>Khóa học </td>
		                                <td class="tdnew">
		                                    : <c:out value="${ttSV.tenKH}"/>
		                                </td>                    
		                            </tr>
		                            <tr>
		                                <td>Năm nhập học </td>
		                                <td class="tdnew">
		                                    : <c:out value="${ttSV.namBD}"/>
		                                </td>                     
		                            </tr>
		                            <tr>
		                                <td>Năm hết thời gian đào tạo </td>
		                                <td class="tdnew">
		                                    : <c:out value="${ttSV.namKT}"/>
		                                </td>                     
		                            </tr>
		                            <tr>
	    							<td colspan="2">&nbsp;</td>
									</tr>                                
		                           
		                    </table>
		                </div>
		        	</div>
		        	<!-- Button đổi mật khẩu -->
		        	<div style="text-align: center; margin-top: 30px; margin-right:200px"> 
		            	<button class="button change-password-button" type="button"  onclick="window.location.href='<%= request.getContextPath() %>/pages_Main/ThaydoiMK.jsp'" >Đổi mật khẩu</button>
					</div>
		        </div>
            </div>
            
        </div>
    </div>
    
</body>
</html>
<script>

    function enableInputs() {
        document.getElementById('phoneInput').removeAttribute('disabled');
        document.getElementById('emailInput').removeAttribute('disabled');
    }

    function saveChanges() {
        // Thực hiện lưu thay đổi ở đây

        // Sau khi lưu, đặt lại thuộc tính disabled cho input
        document.getElementById('phoneInput').disabled = true;
        document.getElementById('emailInput').disabled = true;
    }

    function cancelChanges() {
        // Thực hiện hủy thay đổi ở đây

        // Sau khi hủy, đặt lại thuộc tính disabled cho input
        document.getElementById('phoneInput').disabled = true;
        document.getElementById('emailInput').disabled = true;
        window.location.href = '<%=request.getContextPath()%>/SV/info';
    }
    function updateTT() {
        var isValid = validateInputs();

        if (!isValid) {
            return;
        }

        saveChanges();
        var phoneInputValue = document.getElementById("phoneInput").value;
        var emailInputValue = document.getElementById("emailInput").value;

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // Xử lý kết quả nếu cần
                console.log("Dữ liệu đã được gửi thành công đến Servlet");
            }
        };
        xhttp.open("POST", "<%=request.getContextPath()%>/SV/updateTT", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("phoneInput=" + phoneInputValue + "&emailInput=" + emailInputValue);
    }

    function validateInputs() {
        var phoneInputValue = document.getElementById("phoneInput").value;
        var emailInputValue = document.getElementById("emailInput").value;

        var phoneError = document.getElementById("phoneError");
        var emailError = document.getElementById("emailError");

        phoneError.innerHTML = "";
        emailError.innerHTML = "";

        // Kiểm tra số điện thoại có 10 chữ số
        if (phoneInputValue.length !== 10 || isNaN(phoneInputValue)) {
            phoneError.innerHTML = "Vui lòng nhập đúng 10 chữ số.";
            return false;
        }

        // Kiểm tra định dạng email
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(emailInputValue)) {
            emailError.innerHTML = "Vui lòng nhập email đúng định dạng.";
            return false;
        }

        return true;
    }
</script>

