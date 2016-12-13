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
<title>批准注册</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/media/css/DT_bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/media/css/style-metro.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/media/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common_list.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/confirm/css/jquery-confirm.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/jqPagination/css/jqpagination.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common_service.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/common/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/media/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/media/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/media/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/jqPagination/js/jquery.jqpagination.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/confirm/js/jquery-confirm.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/registers/approvalRegister.js"></script>
<script type="text/javascript">
	var basePath = "<%=request.getContextPath()%>";
	$(function() {
		initRegisters();
		switchTab('dispatchRegisterApply');
	});
</script>
</head>
<body>
	<div class="linklist">
		<div class="add_head">
			<a class="current"
				href="javascript:switchTab('dispatchRegisterApply')"
				id="dispatchRegisterApplyTab">新注册服务需求方</a> <a
				href="javascript:switchTab('ordersRegisterApply')"
				id="ordersRegisterApplyTab">新注册服务提供商</a> <a
				href="javascript:switchTab('dispatchCustomer')"
				id="dispatchCustomerTab">服务需求方列表</a> <a
				href="javascript:switchTab('ordersCustomer')" id="ordersCustomerTab">服务提供商列表</a>
			<a href="javascript:switchTab('insideCustomer')"
				id="insideCustomerTab">内部提供商列表</a>
		</div>
		<div class="tab_search" id="dispatchRegisterApply">
			<jsp:include page="demanderRegisterApplylist.jsp"></jsp:include>
		</div>
		<div class="tab_search" id="ordersRegisterApply">
			<jsp:include page="providerRegisterApplylist.jsp"></jsp:include>
		</div>
		<div class="tab_search" id="dispatchCustomer">
			<jsp:include page="demanderCustomerlist.jsp"></jsp:include>
		</div>
		<div class="tab_search" id="ordersCustomer">
			<jsp:include page="providerCustomerlist.jsp"></jsp:include>
		</div>
		<div class="tab_search" id="insideCustomer">
			<jsp:include page="insideCustomerlist.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>