<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>微信后台管理系统</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/signin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/confirm/css/jquery-confirm.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/confirm/js/jquery-confirm.js"></script>
<script type="text/javascript">
	var basePath = "<%=request.getContextPath()%>";
	if (window != top)
		top.location.href = location.href;
	$(function() {
		$("#btn").click(function() {
			if ($("#username").val() == "") {
				//alert('请输入用户名！');
				$.alert('请输入用户名！', '提示信息');
				return false;
			}
			if ($("#password").val() == "") {
				//alert('请输入密码！');
				$.alert('请输入密码！', '提示信息');
				return false;
			}
			$.post(basePath + "/inside/login", {
				username : $("#username").val(),
				password : $("#password").val()
			}, function(data, status) {
				if (data == "登录成功") {
					window.location = basePath + "/inside/login";
				} else {
					$.alert(data, '错误信息');
					//alert(data);
				}
			});
		});
	});
	$(document).keydown(function(event) {
		if (event.keyCode == 13) //回车键的键值为13
			$("#btn").click(); //调用登录按钮的登录事件
	});
</script>
</head>
<body>
	<div class="container">

		<form class="form-signin" role="form" method="post">
			<h3>
				<img src="<%=request.getContextPath()%>/assets/img/logo.png">
			</h3>
			<h2 class="form-signin-heading">&nbsp;</h2>
			<input type="text" id="username" name="username" class="form-control"
				placeholder="请输入用户名" required autofocus> <input
				type="password" id="password" name="password" class="form-control"
				placeholder="请输入密码" required>
			<!-- <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div> -->
			<button class="btn button btn-lg btn-primary btn-block" id="btn"
				type="button">登录</button>
		</form>

	</div>
	<!-- /container -->
</body>
</html>