<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新密码</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/media/css/DT_bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/media/css/style-metro.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/media/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common_list.css" />
<script
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/common/common.js"></script>
<script type="text/javascript">
	var basePath = "<%=request.getContextPath()%>";
	$(function() {
		//点击修改密码
		$("#updatePwd").click(function() {
			//校验旧密码
			if (!validateComponent("oldPwd", "text"))
				return false;
			//校验新密码
			if (!validateComponent("newPwd", "text"))
				return false;
			//校验确认新密码
			if (!validateComponent("confirmPwd", "text"))
				return false;
			//校验新密码和确认新密码是否相同
			if ($("#newPwd").val() != $("#confirmPwd").val()) {
				showTipsError("新密码和确认新密码不相同");
				return false;
			}
			$.post(basePath + "/inside/forgetPwd", {
				oldPwd : $("#oldPwd").val(),
				newPwd : $("#newPwd").val(),
				confirmPwd : $("#confirmPwd").val()
			}, function(data, status) {// 更新信息
				if (data == "修改成功")
					showTipsSuccess(data);
				else
					showTipsError(data)
			});
		});
	});
</script>
</head>
<body>
	<table class="resultTable table table-hover">
		<tr>
			<td>姓名</td>
			<td>${inside.username }</td>
		</tr>
		<tr>
			<td>电话</td>
			<td>${inside.phone }</td>
		</tr>
		<tr>
			<td>旧密码</td>
			<td><input type="password" class="text required" id="oldPwd" label="旧密码"/></td>
		</tr>
		<tr>
			<td>新密码</td>
			<td><input type="password" class="text required" id="newPwd" label="新密码"/></td>
		</tr>
		<tr>
			<td>确认新密码</td>
			<td><input type="password" class="text required" id="confirmPwd" label="确认新密码"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" class="btn" value="修改密码"
				id="updatePwd" /></td>
		</tr>
	</table>
</body>
</html>