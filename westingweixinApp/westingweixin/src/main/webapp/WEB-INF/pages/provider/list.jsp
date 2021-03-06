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
<title>我的服务</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/raty/demo/css/application.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/common/common_service.css" />
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
	src="<%=request.getContextPath()%>/assets/js/provider/list.js"></script>
<link href="/favicon.ico" rel="shortcut icon" type="image/x-icon">
<script type="text/javascript">
	var basePath = "<%=request.getContextPath()%>";
	var currPage = "${orders.currentPage}";
	var totalPage = "${orders.totalPage}";
	var isCanDown = "${orders.isCanDown}";
	$(function() {
		$.fn.raty.defaults.path = basePath + "/assets/raty/demo/img";
		if ("${orders.records.size()}" != 0) {
			<c:forEach items="${orders.records}" var="order" varStatus="status">
			//渲染列表
			renderingList("${order.id}", "${order.evaluate}", "provider",
					"${order.status}", "${order.evaluateContent}",
					"${order.dealBy}");
			</c:forEach>
			$("#noData").css("display", "none");
		} else {
			$("#noData").css("display", "block");
		}
		//定义滑动操作
		isTouchDevice("pageMyMobileApplys");
	});
</script>
</head>
<body>
	<jsp:include page="../showTips.jsp"></jsp:include>
	<div class="subject" style="padding-top: 0px;">
		<div class="content">
			<div class="viewTaskTitle">我的服务单</div>
			<input type="hidden" id="providerId" value="${providerId}" />
			<c:forEach items="${orders.records}" var="order" varStatus="sta">
				<div class="container viewTaskCommonStyle" id="order${order.id}">
					<c:if test="${sta.index!=0}">
						<hr class="viewHr" />
					</c:if>
					<div class="row serialNumber hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">流水号：</div>
						<div class="col-md-10 col-xs-7 viewContent">${order.serviceOrderId}</div>
						<div class="col-md-1 col-xs-2">
							<img alt="折叠" class="hidden"
								src="<%=request.getContextPath()%>/assets/img/zhedie.png"
								id="zhedie${order.id}">
						</div>
					</div>
					<div class="row categoryView hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">类别：</div>
						<div class="col-md-11 col-xs-9 viewContent">${order.category }</div>
					</div>
					<div class="row serviceTypeView hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">服务类型：</div>
						<div class="col-md-11 col-xs-9 viewContent">${order.serviceType }</div>
					</div>
					<div class="row statusView hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">状态：</div>
						<div class="col-md-11 col-xs-9 viewContent" id="zt${order.id}">${order.status}
						</div>
					</div>
					<div class="row submitTimeView">
						<div class="col-md-1 col-xs-3 label">提交时间：</div>
						<div class="col-md-10 col-xs-7 viewContent zhedie">${order.createDate }&nbsp;<span
								id="status${order.id }" status='${order.status}'>${order.status}</span>
						</div>
						<div class="col-md-1 col-xs-2">
							<img alt="展开" class="show"
								src="<%=request.getContextPath()%>/assets/img/zhankai.png"
								id="zhankai${order.id}">
						</div>
					</div>
					<div class="row serviceDemandView hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">服务需求：</div>
						<div id="content_${order.id}"
							class="col-md-11 col-xs-9 viewContent">${order.content }</div>
					</div>
					<div class="row serviceDemandView hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">价格：</div>
						<div id="content_${order.id}"
							class="col-md-11 col-xs-9 viewContent">${order.price }</div>
					</div>
					<div class="row serviceDemandView viewContentAll hidden${order.id}"
						id="contentAllDiv_${order.id}">
						<div id="contentAll_${order.id}" class="hidden">${order.content }</div>
					</div>
					<div class="row serviceEvaluate hidden${order.id}">
						<div class="col-md-1 col-xs-3 label">客户反馈：</div>
						<div class="col-md-10 col-xs-4" style="padding-left: 0px;">
							<div class="raty viewContent" id="raty${order.id}"
								style="margin-right: 0px; margin-top: -5px;"></div>

						</div>
						<div class="col-md-1 col-xs-5">
							<input type="hidden" id="id${order.id}" value="${order.id}" />
							<button class="btn button btnApply" id="applybut${order.id}">完成申请</button>
						</div>
					</div>
					<div class="row serviceEvaluate hidden${order.id}"
						id="evaluateContentDiv${order.id}">
						<textarea id="evaluateContent${order.id}"
							class="textarea required maxlength" rows="2"
							placeholder="请输入评价内容" label="评价内容" maxlength="100">${order.evaluateContent}</textarea>
					</div>
				</div>
			</c:forEach>
			<div id="noData" style="text-align: center;">
				<font color="grey">没有数据记录</font>
			</div>
		</div>
	</div>
</body>
</html>