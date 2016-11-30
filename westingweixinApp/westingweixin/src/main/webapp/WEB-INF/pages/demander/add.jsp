<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务申请</title>
</head>
<body>
	<sf:form modelAttribute="order" id="adminForm" method="post" action="/demander/add">
		<table cellpadding="0" cellspacing="0">
		<tr>
		<td>产品类型</td>
		<td><sf:select path="category" items="${categoryType}" itemLabel="info"/></td>
		</tr>
		<tr>
		<td>服务类型</td>
		<td><sf:select path="serviceType" items="${serviceType}" itemLabel="info"/></td>
		</tr>
		<tr>
		<td>联系人</td>
		<td><sf:input path="linkname"/></td>
		</tr>
		<tr>
		<td>联系电话</td>
		<td><sf:input path="linkphone"/></td>
		</tr>
		<tr>
		<td>服务内容要求</td>
		<td><sf:input path="content"/></td>
		</tr>
		<tr>
		<td colspan="2"><input type="submit"/></td>
		</tr>
		</table>
	</sf:form>
</body>
</html>