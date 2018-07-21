<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<title>Register</title>
		<link rel="stylesheet" href="../css/login.css">
		<script>
			var errorMessage="<%=session.getAttribute("errorMessage")%>";
			if(errorMessage != "null"){
				alert(errorMessage);
			}
		</script>
		<script type="text/javascript">
            function changeImg() {
                var img = document.getElementById("img");
                img.src = "<%=request.getContextPath()%>/verificationCode.do?date=" + new Date();
            }

            function checkVerificationCode() {
				var verificationCode = document.getElementById('verificationCode').value;
                var flag = (getCookie('v_c_v') == verificationCode);
                if (!flag) {
                    alert('Wrong validation code');
                }
				return flag;
            }

            function getCookie(cookie_name) {
                var allCookies = document.cookie;
                var cookie_pos = allCookies.indexOf(cookie_name);  
                if (cookie_pos != -1) {
                    cookie_pos += cookie_name.length + 1;
                    var cookie_end = allCookies.indexOf(";", cookie_pos);
                    if (cookie_end == -1) {
                        cookie_end = allCookies.length;
                    }
                    return unescape(allCookies.substring(cookie_pos, cookie_end));
                }
                return null;
            }
		</script>
	</head>
	<body>
		<div class="login">
			<div class="header">
				<h1>
					<a href="<%=request.getContextPath()%>/login.do">Login</a>
					<a href="<%=request.getContextPath()%>/regPrompt.do">Register</a>
				</h1>
				<button></button>
			</div>
			<form id="registerForm" action="<%=request.getContextPath()%>/reg.do" method="post">
				<div class="name">
					<input type="text" id="name" name="username" placeholder="Enter Register username" required>
					<p></p>
				</div>
				<div class="pwd">
					<input type="password" id="pwd" name="password" placeholder="password with length between 6-16, case sensitive" required>
					<p></p>
				</div>
				<div class="pwd">
					<input type="password" id="pwd1" name="password1" placeholder="affirm password" required>
					<p></p>
				</div>				
				<div class="idcode">
					<input type="text" id="verificationCode" placeholder="please enter validation code">
					<a href='#' onclick="javascript:changeImg()">&nbsp;&nbsp;&nbsp;&nbsp;change into another one</a>
					<span><img id="img" src="<%=request.getContextPath()%>/verificationCode.do"/></span>
					<div class="clear"></div>
				</div>
				<div class="btn-red">
					<input onclick="return checkVerificationCode();" type="submit" value="register" id="register-btn">
				</div>
			</form>
		</div>
	</body>
</html>