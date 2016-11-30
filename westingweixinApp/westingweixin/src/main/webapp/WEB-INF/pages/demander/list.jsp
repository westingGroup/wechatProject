<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=0">
<title>我的申请-服务需求方-我的服务页面</title>
</head>
<body>
<a href="add">申请服务</a>&nbsp;<a href="list">我的服务</a>&nbsp;<a href="<%=request.getContextPath() %>/user/logout">退出</a>
<table width="900" align="center" border="1">
<tr>
<td>ID</td><td>流水号</td><td>类别</td><td>服务类型</td><td>状态</td><td>提交时间</td><td>服务需求</td><td>用户评价</td>
</tr>
<c:forEach items="${orders}" var="order">
	<tr>
	<td>${order.id }</td>
	<td>${order.serviceOrderId }</td>
	<td>${order.category }</td>
	<td>${order.serviceType}</td>
	<td>${order.status }</td>
	<td>${order.createDate }</td>
	<td>${order.content }</td>
	<td>[<a href="evaluate/${order.id }">评价</a>]</td>
	</tr>
</c:forEach>
</table>
</body>
</html>