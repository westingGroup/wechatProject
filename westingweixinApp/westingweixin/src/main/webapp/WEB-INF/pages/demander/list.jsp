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
<meta name="format-detection" content="telephone=no">
<title>我的服务-服务需求方</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.min.css">
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/demander/list.js"></script>
<link href="/favicon.ico" rel="shortcut icon" type="image/x-icon">
<script type="text/javascript">
	var basePath = "<%=request.getContextPath()%>";
	var currPage = "${orders.currentPage}";
	var totalPage = "${orders.totalPage}";
	var isCanDown = "${orders.isCanDown}";
	$(function() {
		$.fn.raty.defaults.path = basePath + "/assets/raty/demo/img";
		<c:forEach items="${orders.records}" var="order" varStatus="status">
		//渲染列表
		renderingList("${order.id}", "${order.evaluate}", "demander",
				"${order.status}");
		</c:forEach>
		//定义滑动操作
		isTouchDevice("pageMyDemanders");
	});
</script>
</head>
<body>
	<jsp:include page="../showTips.jsp"></jsp:include>
	<div class="subject" style="padding-top: 0px;">
		<div class="content">
			<div class="viewTaskTitle">我的服务单</div>
			<input type="hidden" name="createBy" value="${demanderId}"
				id="createBy" />
			<c:forEach items="${orders.records}" var="order" varStatus="status">
				<div class="container viewTaskCommonStyle" id="order${order.id}">
					<c:if test="${status.index!=0}">
						<hr class="viewHr" />
					</c:if>
					<div class="row serialNumberView hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">流水号：</div>
						<div class="col-md-10 col-xs-7 viewContent">${order.serviceOrderId}</div>
						<div class="col-md-1 col-xs-2">
							<img alt="折叠" class="hidden"
								src="<%=request.getContextPath()%>/assets/img/zhedie.png"
								id="zhedie${order.id}">
						</div>
					</div>
					<hr class="commonHr hidden${order.id}" />
					<div class="row categoryView hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">类别：</div>
						<div class="col-md-11 col-xs-9 viewContent">${order.category }</div>
					</div>
					<hr class="commonHr hidden${order.id}" />
					<div class="row serviceTypeView hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">服务类型：</div>
						<div class="col-md-11 col-xs-9 viewContent">${order.serviceType }</div>
					</div>
					<hr class="commonHr hidden${order.id}" />
					<div class="row statusView hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">状态：</div>
						<div class="col-md-11 col-xs-9 viewContent">${order.status }</div>
					</div>
					<hr class="commonHr hidden${order.id}" />
					<div class="row submitTimeView">
						<div class="col-md-1 col-xs-3 label">提交时间：</div>
						<div class="col-md-10 col-xs-7 viewContent zhedie">${order.createDate }<span
								id="status${order.id }" status="${order.status }">${order.status}</span>
						</div>
						<div class="col-md-1 col-xs-2">
							<img alt="展开" class="show"
								src="<%=request.getContextPath()%>/assets/img/zhankai.png"
								id="zhankai${order.id}">
						</div>
					</div>
					<hr class="commonHr hidden${order.id}" />
					<div class="row serviceDemandView hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">服务需求：</div>
						<div class="col-md-11 col-xs-9 viewContent">${order.content }</div>
					</div>
					<hr class="commonHr hidden${order.id}">
					<div class="row serviceEvaluate hidden${order.id}">
						<div class="col-md-2 col-xs-6" style="padding-left: 0px;">
							<div class="raty viewContent" id="raty${order.id}"
								style="margin-right: 0px;"></div>
						</div>
						<div class="col-md-10 col-xs-6" style="text-align: left;">
							<button class="btn button" id="but${order.id}">服务评价</button>
							<input type="hidden" id="id${order.id}" value="${order.id}" /> <input
								type="hidden" id="evaluate${order.id}" value="${order.evaluate}" />
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>
