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
<title>服务申领</title>
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/common/common.js"></script>
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
				<div class="col-md-10 col-xs-6 title">接单服务</div>
				<div class="col-md-1 col-xs-3 person">
					<img alt="人员信息"
						src="<%=request.getContextPath()%>/assets/img/person.png"
						width="25px" height="25px" />
				</div>
			</div>
		</div>
		<sf:form>
			<div class="content">
				<div class="waitingTaskTitle">待接的任务</div>
				<div class="container waitingTaskCommonStyle waitingTaskInfo">
					<div class="row serialNumberWaiting">
						<div class="col-md-1 col-xs-4 label">流水号：</div>
						<div class="col-md-11 col-xs-8 viewContent">2016112201</div>
					</div>
					<div class="row categoryWaiting">
						<div class="col-md-1 col-xs-4 label">类别：</div>
						<div class="col-md-11 col-xs-8 viewContent">电缆</div>
					</div>
					<div class="row serviceTypeWaiting">
						<div class="col-md-1 col-xs-4 label">服务类型：</div>
						<div class="col-md-11 col-xs-8 viewContent">检修</div>
					</div>
					<div class="row statusWaiting">
						<div class="col-md-1 col-xs-4 label">状态：</div>
						<div class="col-md-11 col-xs-8 viewContent">已申领待分配</div>
					</div>
					<div class="row submitTimeWaiting">
						<div class="col-md-1 col-xs-4 label">任务申请时间：</div>
						<div class="col-md-11 col-xs-8 viewContent">2016年11月22号</div>
					</div>
					<div class="row serviceDemandWaiting">
						<div class="col-md-1 col-xs-4 label">服务需求：</div>
						<div class="col-md-11 col-xs-8 viewContent">电缆检修</div>
					</div>
				</div>
				<div class="container taskPriceAndFinishTime">
					<div class="row">
						<div class="col-md-1 col-xs-3 label">价格：</div>
						<div class="col-md-1 col-xs-3">
							<input type="text" class="text" />
						</div>
						<div class="col-md-1 col-xs-3 label">预计完成时间：</div>
						<div class="col-md-1 col-xs-3">
							<input type="text" class="text" />
						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				<button class="btn" type="submit">任务申领</button>
			</div>
		</sf:form>
	</div>
</body>
</html>