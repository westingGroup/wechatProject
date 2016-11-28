<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=0">
<title>Insert title here</title>
</head>
<body>
<h1>绑定新用户</h1>
	<form method="post">
		<table cellpadding="0" cellspacing="0">
		<tr>
		<td>username</td>
		<td><input type="text" name="username"/></td>
		</tr>
		<tr>
		<td>password</td>
		<td><input type="text" name="password"/></td>
		</tr>
		<tr>
		<td>confirm-password</td>
		<td><input type="text" name="confirmPwd"/></td>
		</tr>
		<tr>
		<td colspan="2">
			<input type="submit"/>
			<a href="bindExistUser">绑定已存在用户</a>
		</td>
		</tr>
		</table>
	</form>
</body>
</html>