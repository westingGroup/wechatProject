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
		<div class="head">
			<div class="back">
				<!-- <a><img alt="返回"
					src="<%=request.getContextPath()%>/assets/img/back.png"
					width="25px" height="25px">返回</a> -->
			</div>
			<div class="title"><!-- 派单服务 --></div>
			<div class="person">
				<!-- <img alt="人员信息"
					src="<%=request.getContextPath()%>/assets/img/person.png"
					width="25px" height="25px" /> -->
			</div>
		</div>
		<sf:form modelAttribute="user" id="adminForm" method="post" action="">
			<div class="content">
				<div class="categoryStyle">
					<div>
						<img alt="电缆"
							src="<%=request.getContextPath()%>/assets/img/电缆.png"
							width="42px" height="42px">
					</div>
					<div>
						<img alt="灯具"
							src="<%=request.getContextPath()%>/assets/img/灯具.png"
							width="42px" height="42px">
					</div>
					<div>
						<img alt="电器件"
							src="<%=request.getContextPath()%>/assets/img/电器件.png"
							width="42px" height="42px">
					</div>
					<div>
						<img alt="其他"
							src="<%=request.getContextPath()%>/assets/img/其他.png"
							width="42px" height="42px">
					</div>
				</div>
				<div class="commonStyle contact">
					<div class="serviceType">
						<span class="label">&nbsp;<font color="red"
							style="margin-left: 16px;">*</font>&nbsp;服务类型：
						</span>
						<sf:select path="" cssClass="select">
							<sf:option value="">安装</sf:option>
							<sf:option value="">调试</sf:option>
							<sf:option value="" selected="selected">检修</sf:option>
							<sf:option value="">保养</sf:option>
							<sf:option value="">其他</sf:option>
						</sf:select>
					</div>
					<hr class="commonHr" />
					<div class="contactPerson">
						<span class="label"><img alt="联系人"
							src="<%=request.getContextPath()%>/assets/img/contactPerson.png"
							width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;联系人：</span>
						<sf:input path="" cssClass="text" />
					</div>
					<hr class="commonHr" />
					<div class="contactPhone">
						<span class="label"><img alt="联系电话"
							src="<%=request.getContextPath()%>/assets/img/contactPhone.png"
							width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;联系电话：</span>
						<sf:input path="" cssClass="text" />
					</div>
				</div>
				<div class="commonStyle service">
					<div class="serviceContent">
						<span class="label" style="vertical-align: top;">&nbsp;<font
							color="red" style="margin-left: 16px;">*</font>&nbsp;服务内容要求：
						</span>
						<sf:textarea path="" cssClass="textarea" rows="3" />
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