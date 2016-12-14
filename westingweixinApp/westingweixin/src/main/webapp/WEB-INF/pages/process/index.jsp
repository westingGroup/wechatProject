<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/index.css" />
<title>待处理</title>
</head>
<body>
	<div class="content">
		<div>
			新注册<a href="<%=request.getContextPath()%>/process/registers?type=dispatchRegisterApply">${processCount.demanderRegisterCount }</a>个需求服务方
		</div>
		<div>
			新注册<a href="<%=request.getContextPath()%>/process/registers?type=ordersRegisterApply">${processCount.providerRegisterCount }</a>个需求提供商
		</div>
		<div>
			新需求<a href="<%=request.getContextPath()%>/process/demanders?type=newDemander">${processCount.newDemanderCount }</a>个
		</div>
		<div>
			处理中需求<a href="<%=request.getContextPath()%>/process/demanders?type=processDemander">${processCount.processDemanderCount }</a>个
		</div>
	</div>
</body>
</html>