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
<input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<c:forEach items="${orders}" var="order" varStatus="stauts">
	<tr>
	<td>${order.id }</td>
	<td>${order.serviceOrderId }</td>
	<td>${order.category }</td>
	<td>${order.serviceType}</td>
	<td>${order.status }</td>
	<td>${order.createDate }</td>
	<td>${order.content }</td>
	<td><input type="button" id="but${stauts.index}" value="评价"/></td>
	<input type="hidden" id="id${stauts.index}" value="${order.id}"/>
	<input type="hidden" id="evaluate${stauts.index}" value="${order.evaluate}"/>
	</tr>
</c:forEach>
</table>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
$(function(){
	<c:forEach items="${orders}" var="order" varStatus="stauts">
		$("#but${stauts.index}").click(
			 function(){
				$("#evaluate${stauts.index}").val("1");
				$.post($("#ctx").val()+"/demander/evaluate/"+$("#id${stauts.index}").val(),
				{evaluate:$("#evaluate${stauts.index}").val()},
				function(data,status){
				  alert(data);
				});
			} 
		);
	</c:forEach>
});
</script>
</body>
</html>