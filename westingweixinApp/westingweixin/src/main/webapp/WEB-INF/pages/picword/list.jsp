<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=0">
<title>弘弘知道管理</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
</head>
<body>
<a href="add">添加弘弘知道详情</a>
<table width="900" align="center" border="1">
<tr>
<td>ID</td><td>brief</td><td>status</td><td>lastUpdateTime</td>
</tr>
<c:forEach items="${wordsList}" var="word">
	<tr>
	<td>${word.id }</td>
	<td>${word.brief }[<a href="update/${word.id }">更新</a>&nbsp;<a href="delete/${word.id }">删除</a>&nbsp;<a href="disable/${word.id }">禁用</a>&nbsp;<a href="<%=request.getContextPath()%>/data/detail/${word.id }" target="_blank">详情</a>&nbsp;<a href="#" id="pubBtn${word.id }" >发布</a>]</td>
	<td>${word.status }</td>
	<td>${word.lastUpdateTime }</td>
	</tr>
</c:forEach>
</table>
<script type="text/javascript">
$(function() {
	<c:forEach items="${wordsList}" var="word" varStatus="status">
	$("#pubBtn${word.id }").click(function(){
		$.post("<%=request.getContextPath()%>/picword/publicDetail/${word.id }", {},
				function(data, status) {
					if(data=="发布成功"){
						alert("发布成功");
						window.location = "<%=request.getContextPath()%>/picword/list";
					}else{
						alert("发布失败");
					}
				});
	});
	</c:forEach>
});
</script>
</body>
</html>