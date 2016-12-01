<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>我的服务(服务需求方)</title>
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
	href="<%=request.getContextPath()%>/assets/css/common/common_service.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/raty/demo/css/application.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/common/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/raty/lib/jquery.raty.min.js"></script>
<link href="/favicon.ico" rel="shortcut icon" type="image/x-icon">
<script type="text/javascript">
	$(function() {
		$.fn.raty.defaults.path = '<%=request.getContextPath()%>/assets/raty/lib/img';
		$(".raty").raty();
	});
</script>
</head>
<body>
	<div class="subject">
		<div class="container head">
			<div class="row">
				<div class="col-md-1 col-xs-3 back">
					<a><img alt="返回"
						src="<%=request.getContextPath()%>/assets/img/back.png"
						width="25px" height="25px">返回</a>
				</div>
				<div class="col-md-10 col-xs-6 title">一键服务-我的服务</div>
				<div class="col-md-1 col-xs-3 person">
					<img alt="人员信息"
						src="<%=request.getContextPath()%>/assets/img/person.png"
						width="25px" height="25px" />
				</div>
			</div>
		</div>
		<sf:form>
			<div class="content">
				<div class="viewTaskTitle">我的服务单</div>
				<div class="container viewTaskCommonStyle">
					<div class="row serialNumberView">
						<div class="col-md-1 col-xs-3 label">流水号：</div>
						<div class="col-md-11 col-xs-9 viewContent">201612201</div>
					</div>
					<hr class="viewHr" />
					<div class="row categoryView">
						<div class="col-md-1 col-xs-3 label">类别：</div>
						<div class="col-md-11 col-xs-9 viewContent">电缆</div>
					</div>
					<hr class="viewHr" />
					<div class="row serviceTypeView">
						<div class="col-md-1 col-xs-3 label">服务类型：</div>
						<div class="col-md-11 col-xs-9 viewContent">检修</div>
					</div>
					<hr class="viewHr" />
					<div class="row statusView">
						<div class="col-md-1 col-xs-3 label">状态：</div>
						<div class="col-md-11 col-xs-9 viewContent">已完成</div>
					</div>
					<hr class="viewHr" />
					<div class="row submitTimeView">
						<div class="col-md-1 col-xs-3 label">提交时间：</div>
						<div class="col-md-11 col-xs-9 viewContent">2016年11月22日</div>
					</div>
					<hr class="viewHr" />
					<div class="row serviceDemandView">
						<div class="col-md-1 col-xs-3 label">服务需求：</div>
						<div class="col-md-11 col-xs-9 viewContent">电缆检修</div>
					</div>
					<div class="row serviceEvaluate">
						<div class="col-md-1 col-xs-5">
							<div class="raty" style="margin-right: 0px;"></div>
						</div>
						<div class="col-md-11 col-xs-7" style="text-align: left;">
							<button class="btn">服务评价</button>
						</div>
					</div>
				</div>
			</div>
		</sf:form>
	</div>
</body>
</html>