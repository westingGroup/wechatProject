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
<title>服务申请</title>
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
				<div class="col-md-10 col-xs-6 title">派单服务</div>
				<div class="col-md-1 col-xs-3 person">
					<img alt="人员信息"
						src="<%=request.getContextPath()%>/assets/img/person.png"
						width="25px" height="25px" />
				</div>
			</div>
		</div>
		<jsp:include page="../showTips.jsp"></jsp:include>
		<sf:form modelAttribute="order" id="adminForm" method="post"
			action="/demander/add" onsubmit="return validate('adminForm')">
			<div class="content">
				<div class="categoryStyle">
					<div>
						<img id="img1" alt="电缆"
							src="<%=request.getContextPath()%>/assets/img/category_cable.png"
							width="42px" height="42px">
					</div>
					<div>
						<img id="img2" alt="灯具"
							src="<%=request.getContextPath()%>/assets/img/category_light.png"
							width="42px" height="42px">
					</div>
					<div>
						<img id="img3" alt="电器件"
							src="<%=request.getContextPath()%>/assets/img/category_electrical.png"
							width="42px" height="42px">
					</div>
					<div>
						<img id="img4" alt="其他"
							src="<%=request.getContextPath()%>/assets/img/category_other.png"
							width="42px" height="42px">
					</div>
				</div>
				<sf:hidden id="categoryId" path="category" value="电缆" />
				<sf:hidden path="createBy" value="${demander.id }" />
				<sf:hidden path="createname" value="${demander.linkname }" />
				<div class="container commonStyle contact">
					<div class="row serviceType">
						<div class="col-md-1 col-xs-4 label">
							<img alt="服务类型"
								src="<%=request.getContextPath()%>/assets/img/edit.png"
								width="16px" height="16px"> &nbsp;<font color="red">*</font>&nbsp;服务类型：
						</div>
						<div class="col-md-11 col-xs-8">
							<sf:select id="orderTypes" path="serviceType"
								cssClass="select required" label="服务类型">
								<sf:options items="${serviceType}" itemLabel="info"
									itemValue="info" />
							</sf:select>

						</div>
					</div>
					<hr class="commonHr" />
					<div class="row contactPerson">
						<div class="col-md-1 col-xs-4 label">
							<img alt="联系人"
								src="<%=request.getContextPath()%>/assets/img/contactPerson.png"
								width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;联系人：
						</div>
						<div class="col-md-11 col-xs-8">
							<sf:input path="linkname" cssClass="text required maxlength"
								label="联系人" maxlength="255" />
						</div>
					</div>
					<hr class="commonHr" />
					<div class="row contactPhone">
						<div class="col-md-1 col-xs-4 label">
							<img alt="联系电话"
								src="<%=request.getContextPath()%>/assets/img/contactPhone.png"
								width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;联系电话：
						</div>
						<div class="col-md-11 col-xs-8">
							<sf:input path="linkphone"
								cssClass="text required phone maxlength" label="联系电话"
								maxlength="13" />
						</div>
					</div>
				</div>
				<div class="container commonStyle service">
					<div class="row serviceContent">
						<div class="col-md-1 col-xs-5 label">
							&nbsp;<font color="red" style="margin-left: 16px;">*</font>&nbsp;服务内容要求：
						</div>
						<div class="col-md-11 col-xs-7">
							<sf:textarea path="content"
								cssClass="textarea required maxlength" rows="3" label="服务内容要求"
								maxlength="255" />
						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				<button class="btn" type="submit">提交</button>
			</div>
			<input type="hidden" id="_orderTypes" value="检修" />
		</sf:form>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var _orderType = $("#_orderTypes").val();
			$("#orderTypes").val(_orderType);
			$("#categoryId").val("电缆");
			$("#img1").addClass("currentimg");
			$("#img2").removeClass("currentimg");
			$("#img3").removeClass("currentimg");
			$("#img4").removeClass("currentimg");
			$("#img1").click(function() {
				$("#categoryId").val("电缆");
				$("#img1").addClass("currentimg");
				$("#img2").removeClass("currentimg");
				$("#img3").removeClass("currentimg");
				$("#img4").removeClass("currentimg");
			});
			$("#img2").click(function() {
				$("#categoryId").val("灯具");
				$("#img1").removeClass("currentimg");
				$("#img2").addClass("currentimg");
				$("#img3").removeClass("currentimg");
				$("#img4").removeClass("currentimg");
			});
			$("#img3").click(function() {
				$("#categoryId").val("电器件");
				$("#img1").removeClass("currentimg");
				$("#img2").removeClass("currentimg");
				$("#img3").addClass("currentimg");
				$("#img4").removeClass("currentimg");
			});
			$("#img4").click(function() {
				$("#categoryId").val("其他");
				$("#img1").removeClass("currentimg");
				$("#img2").removeClass("currentimg");
				$("#img3").removeClass("currentimg");
				$("#img4").addClass("currentimg");
			});

		});
	</script>
</body>
</html>