<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>我的服务-服务需求方</title>
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/common/slide_screen.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/common/dem_pro_list_common.js"></script>
<link href="/favicon.ico" rel="shortcut icon" type="image/x-icon">
<script type="text/javascript">
	var basePath = "<%=request.getContextPath()%>";
	$(function() {
		$.fn.raty.defaults.path = basePath + "/assets/raty/lib/img";
		<c:forEach items="${orders}" var="order" varStatus="status">
			//渲染列表
			renderingList("${status.index}", "${order.evaluate}", "${status.last}", "demander");
		</c:forEach>

		//定义滑动操作
		isTouchDevice();
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
		<div class="content">
			<div class="viewTaskTitle">我的服务单</div>
			<c:forEach items="${orders}" var="order" varStatus="status">
				<div class="container viewTaskCommonStyle" id="order${status.index}">
					<c:if test="${status.index!=0}">
						<hr class="viewHr" />
					</c:if>
					<div class="row serialNumberView hidden${status.index}">
						<div class="col-md-1 col-xs-3 label">流水号：</div>
						<div class="col-md-10 col-xs-7 viewContent">${order.serviceOrderId}</div>
						<div class="col-md-1 col-xs-2">
							<img alt="折叠" class="hidden"
								src="<%=request.getContextPath()%>/assets/img/zhedie.png"
								id="zhedie${status.index}">
						</div>
					</div>
					<hr class="commonHr hidden${status.index}" />
					<div class="row categoryView hidden${status.index}">
						<div class="col-md-1 col-xs-3 label">类别：</div>
						<div class="col-md-11 col-xs-9 viewContent">${order.category }</div>
					</div>
					<hr class="commonHr hidden${status.index}" />
					<div class="row serviceTypeView hidden${status.index}">
						<div class="col-md-1 col-xs-3 label">服务类型：</div>
						<div class="col-md-11 col-xs-9 viewContent">${order.serviceType }</div>
					</div>
					<hr class="commonHr hidden${status.index}" />
					<div class="row statusView hidden${status.index}">
						<div class="col-md-1 col-xs-3 label">状态：</div>
						<div class="col-md-11 col-xs-9 viewContent">${order.status }</div>
					</div>
					<hr class="commonHr hidden${status.index}" />
					<div class="row submitTimeView">
						<div class="col-md-1 col-xs-3 label">提交时间：</div>
						<div class="col-md-10 col-xs-7 viewContent zhedie">${order.createDate }</div>
						<div class="col-md-1 col-xs-2">
							<img alt="展开" class="show"
								src="<%=request.getContextPath()%>/assets/img/zhankai.png"
								id="zhankai${status.index}">
						</div>
					</div>
					<hr class="commonHr hidden${status.index}" />
					<div class="row serviceDemandView hidden${status.index}">
						<div class="col-md-1 col-xs-3 label">服务需求：</div>
						<div class="col-md-11 col-xs-9 viewContent">${order.content }</div>
					</div>
					<hr class="commonHr hidden${status.index}">
					<div class="row serviceEvaluate hidden${status.index}">
						<div class="col-md-1 col-xs-5">
							<div class="raty" id="raty${status.index}"
								style="margin-right: 0px;"></div>
						</div>
						<div class="col-md-11 col-xs-7" style="text-align: left;">
							<button class="btn" id="but${status.index}">服务评价</button>
							<input type="hidden" id="id${status.index}" value="${order.id}" />
							<input type="hidden" id="evaluate${status.index}"
								value="${order.evaluate}" />
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>