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
<title>服务需求方注册</title>
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
		<div class="head">
			<div class="back">
				<a><img alt="返回"
					src="<%=request.getContextPath()%>/assets/img/back.png"
					width="25px" height="25px">返回</a>
			</div>
			<div class="title">服务需求方注册</div>
			<div class="person">
				<img alt="人员信息"
					src="<%=request.getContextPath()%>/assets/img/person.png"
					width="25px" height="25px" />
			</div>
		</div>
		<sf:form modelAttribute="user" id="adminForm" method="post"
			action="/demander/update">
			<div class="content">
				<sf:hidden path="id" />
				<div class="commonStyle contact">
					<hr class="commonHr" />
					<div class="contactPerson">
						<span class="label"><img alt="联系人"
							src="<%=request.getContextPath()%>/assets/img/contactPerson.png"
							width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;联系人：</span>
						<sf:input path="linkname" cssClass="text" />
					</div>
					<hr class="commonHr" />
					<div class="contactPhone">
						<span class="label"><img alt="联系电话"
							src="<%=request.getContextPath()%>/assets/img/contactPhone.png"
							width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;联系电话：</span>
						<sf:input path="linkphone" cssClass="text" />
					</div>
				</div>
				<div class="commonStyle company">
					<div class="companyBusiness">
						<span class="label">&nbsp;<font color="red"
							style="margin-left: 16px;">*</font>&nbsp;相关业务：
						</span>
						<sf:input path="business" cssClass="text" />
					</div>
					<hr class="commonHr" />
					<div class="companyName">
						<span class="label" style="vertical-align: top;"><span
							style="margin-left: 26px;">公司名称：</span></span>
						<sf:textarea path="company" cssClass="textarea" />
					</div>
				</div>
			</div>
			<div class="footer">
				<button class="btn" type="submit">提交</button>
			</div>
		</sf:form>
	</div>
</body>
</html>