<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务提供商注册</title>
</head>
<body>
	<sf:form modelAttribute="user" id="adminForm" method="post" action="/provider/update">
		<sf:hidden path="id"/>
		<table cellpadding="0" cellspacing="0">
		<tr>
		<td>*姓名：</td>
		<td><sf:input path="linkname"/></td>
		</tr>
		<tr>
		<td>*电话：</td>
		<td><sf:input path="linkphone"/></td>
		</tr>
		<tr>
		<td>*擅长业务：</td>
		<td><sf:input path="business"/></td>
		</tr>
		<tr>
		<tr>
		<td>*相关资质：</td>
		<td><sf:input path="qualification"/></td>
		</tr>
		<tr>
		<td>公司名称：</td>
		<td><sf:input path="company"/></td>
		</tr>
		<tr>
		<td colspan="2"><input type="submit"/></td>
		</tr>
		</table>
	</sf:form>
</body>
</html>