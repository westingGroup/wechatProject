<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link href="/<%=request.getContextPath()%>/assets/css/common/common.css"
	rel="stylesheet" />
<script src="/<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="linklist" >
		<div class="add_head">
			<a class="current" href="javascript:switchTab('planDetail')" id="planDetailTab">新需求</a> 
			<a href="javascript:switchTab('planManual')" id="planManualTab">处理中</a>
			<a href="javascript:switchTab('planChecklist')" id="planChecklistTab">已完成</a>
			<a href="javascript:switchTab('planServicebom')" id="planServicebomTab">已完结</a>
		</div>
	</div>
</body>
</html>