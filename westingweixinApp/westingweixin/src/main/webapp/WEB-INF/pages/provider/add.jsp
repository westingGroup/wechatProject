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
	href="<%=request.getContextPath()%>/assets/media/css/datetimepicker.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/media/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common_service.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/media/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/media/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/media/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/common/slide_screen.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/common/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/provider/add.js"></script>
<script type="text/javascript">
	var currPage = "${orders.currentPage}";
	var totalPage = "${orders.totalPage}";
	var isCanDown = "${orders.isCanDown}";
	var basePath = "<%=request.getContextPath()%>";
	$(function() {
		<c:forEach items="${orders.records}" var="order" varStatus="status">
		renderList("${order.id}");
		</c:forEach>
		initDatePickerForDay(new Date());
		//定义滑动操作
		isTouchDevice("mobileApply");
		clearApply();//清空页面信息
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
				<div class="col-md-10 col-xs-6 title">接单服务</div>
				<div class="col-md-1 col-xs-3 person">
					<img alt="人员信息"
						src="<%=request.getContextPath()%>/assets/img/person.png"
						width="25px" height="25px" />
				</div>
			</div>
		</div>
		<jsp:include page="../showTips.jsp"></jsp:include>
		<div class="content">
			<div class="providerOrders">
				<div class="waitingTaskTitle">待接的任务</div>
				<input type="hidden" name="providerId" value="${provider.id}"
					id="providerId" /> <input type="hidden" name="providerName"
					value="${provider.linkname}" id="providerName" /> <input
					type="hidden" name="applyBy" value="${provider.id}" id="applyBy" />
				<input type="hidden" name="id" id="id" value="0" />
				<c:forEach items="${orders.records}" var="order" varStatus="status">
					<div class="container waitingTaskCommonStyle" id="order${order.id}"
						orderId="${order.id}">
						<c:if test="${status.index!=0}">
							<hr class="viewHr" />
						</c:if>
						<div class="row serialNumber hidden${order.id}">
							<div class="col-md-1 col-xs-4 label">流水号：</div>
							<div class="col-md-10 col-xs-6 viewContent">${order.serviceOrderId}</div>
							<div class="col-md-1 col-xs-2">
								<img alt="折叠" class="hidden"
									src="<%=request.getContextPath()%>/assets/img/zhedie.png"
									id="zhedie${order.id}">
							</div>
						</div>
						<div class="row category hidden${order.id}">
							<div class="col-md-1 col-xs-4 label">类别：</div>
							<div class="col-md-11 col-xs-8 viewContent">${order.category }</div>
						</div>
						<div class="row serviceType hidden${order.id}">
							<div class="col-md-1 col-xs-4 label">服务类型：</div>
							<div class="col-md-11 col-xs-8 viewContent">${order.serviceType }</div>
						</div>
						<div class="row submitTime">
							<div class="col-md-1 col-xs-4 label">任务申请时间：</div>
							<div class="col-md-10 col-xs-6 viewContent">${order.createDate }</div>
							<div class="col-md-1 col-xs-2">
								<img alt="展开" class="show"
									src="<%=request.getContextPath()%>/assets/img/zhankai.png"
									id="zhankai${order.id}">
							</div>
						</div>
						<div class="row serviceDemandWaiting hidden${order.id}">
							<div class="col-md-1 col-xs-4 label">服务需求：</div>
							<div class="col-md-11 col-xs-8 viewContent">${order.content }</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="container priceAndFinishTime">
				<div class="row">
					<div class="col-md-1 col-xs-2 label">
						<font color="red">*</font>&nbsp;联系人：
					</div>
					<div class="col-md-4 col-xs-2" style="padding-left: 7px;">
						<input type="text" class="text required maxlength" label="联系人"
							maxlength="255" id="linkname" />
					</div>
					<div class="col-md-2 col-xs-3 label">
						<font color="red">*</font>&nbsp;联系电话：
					</div>
					<div class="col-md-4 col-xs-5" style="padding-left: 7px;">
						<input type="text" class="text required phone maxlength"
							style="width: 110px;" label="联系电话" maxlength="13" id="linkphone" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-1 col-xs-2 label">价格：</div>
					<div class="col-md-4 col-xs-2" style="padding-left: 7px;">
						<input type="text" class="text num" id="price" label="价格" />
					</div>
					<div class="col-md-2 col-xs-3 label">预计完成时间：</div>
					<div class="col-md-4 col-xs-5 input-append date form_datetime_day"
						style="padding-left: 7px;">
						<input type="text" class="text" style="width: 75px;"
							id="completeDate" /><span class="add-on"
							style="padding: 4px 0px;"><i class="icon-remove"></i></span> <span
							class="add-on" style="padding: 4px 0px;"><i
							class="icon-calendar"></i></span>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<button class="btn" type="button" onclick="applyServiceOrder()"
				id="button">任务申领</button>
		</div>
	</div>
</body>
</html>