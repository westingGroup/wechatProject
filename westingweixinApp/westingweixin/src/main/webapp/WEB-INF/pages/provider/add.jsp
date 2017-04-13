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
<meta name="format-detection" content="telephone=no">
<title>接单服务</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.min.css">
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
	var linkName = "${provider.linkname}";
	var linkPhone = "${provider.linkphone}";
	$(function() {
		if ("${orders.records.size()}" != 0) {
			<c:forEach items="${orders.records}" var="order" varStatus="status">
			renderList("${order.id}");
			</c:forEach>
			$("#noData").css("display", "none");
		} else {
			$("#noData").css("display", "block");
		}

		initDatePickerForDay(new Date());
		//定义滑动操作
		isTouchDevice("mobileApply");
		if ("${orders.records.size()}" == 0) {
			$("#button").addClass("hidden");
		} else {
			$("#button").addClass("show").attr("disabled", false);
		} 
	});
</script>
</head>
<body>
	<jsp:include page="../showTips.jsp"></jsp:include>
	<div class="subject" style="padding-top: 0px;">
		<div class="content">
			<div class="waitingTaskTitle">待接的任务</div>
			<div class="providerOrders">
				<input type="hidden" name="providerType" value="${provider.type}"
					id="providerType" /> <input type="hidden" name="providerId"
					value="${provider.id}" id="providerId" /> <input type="hidden"
					name="providerName" value="${provider.linkname}" id="providerName" />
				<input type="hidden" name="applyBy" value="${provider.id}"
					id="applyBy" /><input type="hidden" name="openID" value="${provider.openid}"
					id="openID" /> <input type="hidden" name="id" id="id" value="0" />
				<c:forEach items="${orders.records}" var="order" varStatus="status">
					<div class="container waitingTaskCommonStyle" id="order${order.id}"
						orderId="${order.id}" btnstatus="${order.status}" applyflag="${order.applyflag}">
						<c:if test="${status.index!=0}">
							<hr class="viewHr" />
						</c:if>
						<div class="row serialNumber hidden${order.id}">
							<div class="col-md-2 col-xs-4 label">流水号：</div>
							<div class="col-md-9 col-xs-6 viewContent">${order.serviceOrderId}</div>
							<div class="col-md-1 col-xs-2">
								<img alt="折叠" class="hidden"
									src="<%=request.getContextPath()%>/assets/img/zhedie.png"
									id="zhedie${order.id}">
							</div>
						</div>
						<div class="row category hidden${order.id}">
							<div class="col-md-2 col-xs-4 label">类别：</div>
							<div class="col-md-10 col-xs-8 viewContent">${order.category }</div>
						</div>
						<div class="row serviceType hidden${order.id}">
							<div class="col-md-2 col-xs-4 label">服务类型：</div>
							<div class="col-md-10 col-xs-8 viewContent">${order.serviceType }</div>
						</div>
						<div class="row submitTime">
							<div class="col-md-2 col-xs-4 label">任务申请时间：</div>
							<div class="col-md-9 col-xs-6 viewContent">${order.createDate }</div>
							<div class="col-md-1 col-xs-2">
								<img alt="展开" class="show"
									src="<%=request.getContextPath()%>/assets/img/zhankai.png"
									id="zhankai${order.id}">
							</div>
						</div>
						<div class="row serviceDemandWaiting hidden${order.id}">
							<div class="col-md-2 col-xs-4 label">服务需求：</div>
							<div id="content_${order.id}" class="col-md-10 col-xs-8 viewContent">${order.content }</div>
						</div>
						<div class="row serviceDemandWaiting viewContentAll hidden${order.id}" id="contentAllDiv_${order.id}">
							<div id="contentAll_${order.id}" class="hidden">${order.content }</div>
						</div>
						<div class="row hidden${order.id }">
							<div class="col-md-6 col-xs-6" style="padding-left: 7px;">
								<input type="text" class="text required maxlength" label="联系人"
									maxlength="255" placeholder="*联系人" id="linkname${order.id }"
									style="background: rgb(247, 156, 127) !important; border-bottom: 1px solid white;"
									value="${provider.linkname }" />
							</div>
							<div class="col-md-6 col-xs-6" style="padding-left: 7px;">
								<input type="text" class="text required phone maxlength"
									label="联系电话" maxlength="13" placeholder="*联系电话"
									id="linkphone${order.id }"
									style="background: rgb(247, 156, 127) !important; border-bottom: 1px solid white;"
									value="${provider.linkphone }" />
							</div>
						</div>
						<div class="row hidden${order.id }">
							<div class="col-md-6 col-xs-6" style="padding-left: 7px;">
								<input type="text" class="text required num maxlength" maxlength="10" label="价格" placeholder="*价格（必输）"
									id="price${order.id }"
									style="background: rgb(247, 156, 127) !important; border-bottom: 1px solid white;" />
							</div>
							<div
								class="col-md-6 col-xs-6 input-append date form_datetime_day"
								style="padding-left: 7px; padding-right: 0px;">
								<div class="row"
									style="padding-top: 0px; margin-left: 0px; padding-right: 0px;">
									<div class="col-md-11 col-xs-8"
										style="padding-left: 0px; padding-right: 0px;">
										<input type="text" class="text" placeholder="预计完成时间"
											id="completeDate${order.id }"
											style="background: rgb(247, 156, 127) !important; border-bottom: 1px solid white;" />
									</div>
									<div class="col-md-1 col-xs-4"
										style="padding-left: 0px; padding-right: 0px;">
										<span class="add-on"><i class="icon-remove"></i></span> <span
											class="add-on"><i class="icon-calendar"></i></span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div id="noData" style="text-align: center;">
				<font color="grey">没有数据记录</font>
			</div>
		</div>
		<div class="footer">
			<button class="btn button" type="button" 
				onclick="applyServiceOrder()" id="button">任务申领</button>
		</div>
	</div>
</body>
</html>