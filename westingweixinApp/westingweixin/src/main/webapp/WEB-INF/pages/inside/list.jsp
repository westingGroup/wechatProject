<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=0">
<title>insides list</title>
</head>
<body>
<a href="add">添加用户</a>&nbsp;<a href="forgetPwd">修改密码</a>&nbsp;<a href="list">用户列表</a>&nbsp;<a href="logout">退出</a>
<table width="900" align="center" border="1">
<tr>
<td>ID</td><td>username</td><td>phone</td><td>status</td>
</tr>
<c:forEach items="${insides}" var="provider">
	<tr>
	<td>${provider.id }</td>
	<td>${provider.username }[<a href="update/${provider.id }">更新</a>&nbsp;<a href="delete/${provider.id }">删除</a>]</td>
	<td>${provider.phone}</td>
	<Td>${provider.status }</Td>
	</tr>
</c:forEach>
</table>
</body>
</html>