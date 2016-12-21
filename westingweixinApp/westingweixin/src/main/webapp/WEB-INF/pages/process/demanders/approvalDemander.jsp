<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>处理需求</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/media/css/DT_bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/media/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common_list.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/jqPagination/css/jqpagination.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/media/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/media/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/media/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/jqPagination/js/jquery.jqpagination.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/common/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/demander/approvalDemander.js"></script>
<script type="text/javascript">
	var basePath = "<%=request.getContextPath()%>
	";
	$(function() {
		initDemander();
		if ("${type}" != null && "${type}" != "")
			switchTab("${type}");
		else
			switchTab('newDemander');
	});
</script>
</head>
<body>
	<div class="linklist">
		<div class="add_head">
			<a class="current" href="javascript:switchTab('newDemander')"
				id="newDemanderTab">新需求</a> <a
				href="javascript:switchTab('processDemander')"
				id="processDemanderTab">处理中</a> <a
				href="javascript:switchTab('finishedDemander')"
				id="finishedDemanderTab">已完成</a> <a
				href="javascript:switchTab('wasteDemander')" id="wasteDemanderTab">废单</a>
		</div>
		<div class="tab_search" id="newDemander">
			<jsp:include page="newDemander.jsp"></jsp:include>
		</div>
		<div class="tab_search" id="processDemander">
			<jsp:include page="processDemander.jsp"></jsp:include>
		</div>
		<div class="tab_search" id="finishedDemander">
			<jsp:include page="finishedDemander.jsp"></jsp:include>
		</div>
		<div class="tab_search" id="wasteDemander">
			<jsp:include page="wasteDemander.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>