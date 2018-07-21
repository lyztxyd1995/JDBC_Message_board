<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<title>Login</title>
		<link rel="stylesheet" href="../css/login.css">
		<script type="text/javascript">
            function changeImg() {
                var img = document.getElementById("img");
                img.src = "<%=request.getContextPath()%>/verificationCode.do?date=" + new Date();
            }

            function checkVerificationCode() {
				var verificationCode = document.getElementById('verificationCode').value;
                var flag = (getCookie('v_c_v') == verificationCode);
                if (!flag) {
                    alert('Wrong verification code');
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
					<a href="/regPrompt.do">注册</a>
				</h1>
				<button></button>
			</div>
			<form action="<%=request.getContextPath()%>/main.do" method="post">
				<div class="name">
					<input type="text" id="name" name="username" placeholder="Please Enter username">
					<p></p>
				</div>
				<div class="pwd">
					<input type="password" id="pwd" name="password" placeholder="password with length between 6 to 16, no space, case sensitive">
					<p></p>
				</div>
				<div class="idcode">
					<input type="text" id="verificationCode" placeholder="please enter verification code">
					<a href='#' onclick="javascript:changeImg()">&nbsp;&nbsp;&nbsp;&nbsp;Change to another one</a>
					<span><img id="img" src="<%=request.getContextPath()%>/verificationCode.do"/></span>
					<div class="clear"></div>
				</div>
				<div class="autoLogin">
					<label for="">
						<input type="checkbox" checked="checked">
						Auto login next time
					</label>
					<a href="">Forgot password</a>
				</div>
				<div class="btn-red">
					<input onclick="return checkVerificationCode();" type="submit" value="login" id="login-btn">
				</div>
			</form>
		</div>
	</body>
</html>