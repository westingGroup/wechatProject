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
<title>服务提供商注册</title>
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
	src="<%=request.getContextPath()%>/assets/js/common/common.js"></script>
<script type="text/javascript">
$(function() {
	initDatePickerForDay(null, new Date());
});
</script>
</head>
<body>
	<jsp:include page="../showTips.jsp"></jsp:include>
	<div class="subject">
		<sf:form modelAttribute="provider" id="adminForm" method="post"
			action="/provider/register" onsubmit="return validate('adminForm')">
			<sf:hidden path="id" />
			<input type="hidden" name="fromPath" id="fromPath"
				value="${fromPath}"></input>
			<input type="hidden" name="openid" id="openid" value="${openid}"></input>
			<div class="content">
				<div class="container commonStyle contact">
					<hr class="commonHr" />
					<div class="row contactPerson">
						<div class="col-md-1 col-xs-4 label">
							<img alt="姓名"
								src="<%=request.getContextPath()%>/assets/img/contactPerson.png"
								width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;姓名：
						</div>
						<div class="col-md-11 col-xs-8">
							<sf:input path="linkname" cssClass="text required maxlength"
								label="姓名" maxlength="255" />
						</div>
					</div>
					<hr class="commonHr" />
					<div class="row contactPhone">
						<div class="col-md-1 col-xs-4 label">
							<img alt="电话"
								src="<%=request.getContextPath()%>/assets/img/contactPhone.png"
								width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;电话：
						</div>
						<div class="col-md-11 col-xs-8">
							<sf:input path="linkphone"
								cssClass="text required phone maxlength" label="电话"
								maxlength="13" />
						</div>
					</div>

					<hr class="commonHr" />
					<div class="row contactBirthday">
						<div class="col-md-1 col-xs-4 label">
							<img alt="出生年月"
								src="<%=request.getContextPath()%>/assets/img/calendar.png"
								width="16px" height="16px">&nbsp;&nbsp;出生年月：
						</div>
						<div
							class="col-md-11 col-xs-8 input-append date form_datetime_day">
							<div class="row" style="padding-top: 0px;margin: 0px;">
								<div class="col-md-10 col-xs-7" style="padding: 0px;">
									<input type="text" class="text" name="birth"
										readonly="readonly" />
								</div>
								<div class="col-md-2 col-xs-5"
									style="padding: 0px; text-align: right;">
									<span class="add-on" style="margin-right: 5px;"><i
										class="icon-remove"></i></span> <span class="add-on"><i
										class="icon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="container commonStyle company">
					<div class="row companyBusiness">
						<div class="col-md-1 col-xs-4 label">
							&nbsp;<font color="red" style="margin-left: 16px;">*</font>&nbsp;擅长业务：
						</div>
						<div class="col-md-11 col-xs-8">
							<sf:input path="business" cssClass="text required maxlength"
								label="擅长业务" maxlength="255" />
						</div>
					</div>
					<hr class="commonHr" />
					<div class="row companyQualifition">
						<div class="col-md-1 col-xs-4 label">
							<span style="margin-left: 26px;"></span>职称/资质：
						</div>
						<div class="col-md-11 col-xs-8">
							<sf:input path="qualification" cssClass="text maxlength"
								label="职称/资质" maxlength="255" />
						</div>
					</div>
					<hr class="commonHr" />
					<div class="row companyName">
						<div class="col-md-1 col-xs-4 label">
							<span style="margin-left: 26px;">公司名称：</span>
						</div>
						<div class="col-md-11 col-xs-8">
							<sf:input path="company" cssClass="text maxlength" label="公司名称"
								maxlength="255" />
						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				<button class="btn button" type="submit">提交</button>
			</div>
		</sf:form>
	</div>
</body>
</html>