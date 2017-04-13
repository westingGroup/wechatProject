<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=0">
<title>弘弘知道管理</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common_list.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
</head>
<body>
	<table class="resultTable table table-hover table-bordered">
		<thead>
			<tr>
				<th>序号</th>
				<th>标题</th>
				<th>状态</th>
				<th>最后更新时间</th>
			</tr>
		</thead>
		<tbody id="demanderCustomerListBody">
			<c:forEach items="${wordsList}" var="word">
				<tr>
					<td>${word.id }</td>
					<td><div align='center'><div class="registerBusiness" title="${word.brief }">${word.brief }</div></div>[<a href="update/${word.id }">更新</a> &nbsp;
						<a href="delete/${word.id }">删除</a> <c:if test="${word.status==1}">
						&nbsp;<a href="disable/${word.id }">禁用</a>
						</c:if> &nbsp;<a
						href="<%=request.getContextPath()%>/data/detail/${word.id }"
						target="_blank">详情</a> <c:if test="${word.status!=1}">
						&nbsp;<a href="#" id="pubBtn${word.id }">发布</a>
						</c:if> ]
						
					</td>
					<td><c:if test="${word.status==1}">使用</c:if> <c:if
							test="${word.status!=1}">未使用</c:if></td>
					<td>${word.lastUpdateTime }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="approval_info" style="text-align: right;">
		<a class="btn button approvalBtn" id="picWordAddBtn"
			href="<%=request.getContextPath()%>/data/konw" target="_blank">预览</a>

		<a class="btn button rejectBtn" id="picWordAddBtn"
			href="<%=request.getContextPath()%>/picword/add">新增</a>
	</div>

	<script type="text/javascript">
$(function() {
	<c:forEach items="${wordsList}" var="word" varStatus="status">
	$("#pubBtn${word.id }").click(function(){
		$.post("<%=request.getContextPath()%>/picword/publicDetail/${word.id }", {},
				function(data, status) {
					if(data=="发布成功"){
						alert("发布成功");
						window.location = "<%=request.getContextPath()%>/picword/list";
													} else {
														alert("发布失败");
													}
												});
							});
			</c:forEach>
		});
	</script>
</body>
</html>