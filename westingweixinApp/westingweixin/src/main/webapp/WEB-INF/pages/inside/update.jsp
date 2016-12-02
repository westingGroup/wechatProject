<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>inside update</title>
</head>
<body>
	<sf:form modelAttribute="inside" id="adminForm" method="post">
		<table cellpadding="0" cellspacing="0">
		<tr>
		<td>用户名</td>
		<td><sf:input path="username"/></td>
		</tr>
		<tr>
		<td>电话</td>
		<td><sf:input path="phone"/></td>
		</tr>
		<tr>
		<td>password</td>
		<td><sf:input path="password"/></td>
		</tr>
		<tr>
		<td colspan="2"><input type="submit"/></td>
		</tr>
		</table>
	</sf:form>
</body>
</html>