<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=0">
<title>弘弘知道管理</title>
</head>
<body>
<a href="add">添加弘弘知道详情</a>&nbsp;<a href="list">列表</a>&nbsp;<a href="<%=request.getContextPath()%>/inside/logout">退出</a>
<table width="900" align="center" border="1">
<tr>
<td>ID</td><td>brief</td><td>status</td><td>lastUpdateTime</td>
</tr>
<c:forEach items="${wordsList}" var="word">
	<tr>
	<td>${word.id }</td>
	<td>${word.brief }[<a href="update/${word.id }">更新</a>&nbsp;<a href="delete/${word.id }">删除</a>&nbsp;<a href="detail/${word.id }">详情</a>]</td>
	<td>${word.status }</td>
	<td>${word.lastUpdateTime }</td>
	</tr>
</c:forEach>
</table>
</body>
</html>