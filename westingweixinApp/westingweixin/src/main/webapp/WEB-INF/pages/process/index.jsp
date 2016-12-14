<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/index.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/index.js"></script>
<title>待处理</title>
</head>
<body>
	<div id="" style="width: 100%; text-align: center;">
		<div style="padding-top: 40px; padding-bottom: 10px;">
			新注册<a style="color: red;">${processCount.demanderRegisterCount }</a>个需求服务方
		</div>
		<div style="padding-top: 10px; padding-bottom: 10px;">
			新注册<a style="color: red">${processCount.providerRegisterCount }</a>个需求提供商
		</div>
		<div style="padding-top: 10px; padding-bottom: 10px;">新需求${processCount.newDemanderCount }个</div>
		<div style="padding-top: 10px; padding-bottom: 10px;">处理中需求${processCount.processDemanderCount }个</div>
	</div>
</body>
</html>