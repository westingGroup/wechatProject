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
<title>我的申请-服务需求方-我的服务页面</title>
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
<link href="/favicon.ico" rel="shortcut icon" type="image/x-icon">
<script type="text/javascript">
	var basePath = "<%=request.getContextPath()%>";
	$(function() {
		$.fn.raty.defaults.path = basePath + "/assets/raty/lib/img";
		<c:forEach items="${orders}" var="order" varStatus="status">
		//添加星级评价
		$("#raty${status.index}").raty({
			score : "${order.evaluate}",
			click : function(score, evt) {
				$("#evaluate${status.index}").val(score);
			}
		});
		//如果已经评价完成，则不能再次评价，隐藏服务评价按钮
		if ("${order.evaluate != null && order.evaluate != ''}" == "true")
			$("#but${status.index}").addClass("hidden");
		
		//服务评价
		$("#but${status.index}").click(
				function() {
					$.post(basePath + "/demander/evaluate/"
							+ $("#id${status.index}").val(), {
						evaluate : $("#evaluate${status.index}").val()
					}, function(data, status) {
						if(status == "success"){
							alert(data);
							$("#but${status.index}").addClass("hidden");
						}
					});
				});

		//将除去提交时间之外的所有信息隐藏
		$(".hidden${status.index}").addClass("hidden");
		//点击展开时，展开服务信息
		$("#zhankai${status.index}").click(function() {
			$(".hidden${status.index}").removeClass("hidden").addClass("show");
			$(this).removeClass("show").addClass("hidden");
			$("#zhedie${status.index}").removeClass("hidden").addClass("show");
			if ("${status.last}" != "true")
				$("#order${status.index}").css("marginBottom", "40px");
		});

		//点击折叠时，折叠服务信息
		$("#zhedie${status.index}").click(
				function() {
					$(".hidden${status.index}").removeClass("show").addClass(
							"hidden");
					$(this).removeClass("show").addClass("hidden");
					$("#zhankai${status.index}").removeClass("hidden")
							.addClass("show");
					if ("${status.last}" != "true")
						$("#order${status.index}").css("marginBottom", "0px");
				});
		</c:forEach>

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