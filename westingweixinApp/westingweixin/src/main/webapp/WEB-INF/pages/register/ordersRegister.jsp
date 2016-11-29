<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<div class="title">服务提供商注册</div>
			<div class="person">
				<img alt="人员信息"
					src="<%=request.getContextPath()%>/assets/img/person.png"
					width="25px" height="25px" />
			</div>
		</div>
		<div class="content">
			<div class="commonStyle contact">
				<div class="contactPerson">
					<span class="label"><img alt="姓名"
						src="<%=request.getContextPath()%>/assets/img/contactPerson.png"
						width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;姓名：</span>
					<input type="text" class="text" />
				</div>
				<hr class="commonHr" />
				<div class="contactPhone">
					<span class="label"><img alt="电话"
						src="<%=request.getContextPath()%>/assets/img/contactPhone.png"
						width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;电话：</span><input
						type="text" class="text" />
				</div>
			</div>
			<div class="commonStyle company">
				<div class="companyBusiness">
					<span class="label">&nbsp;<font color="red"
						style="margin-left: 16px;">*</font>&nbsp;擅长业务：
					</span> <input type="text" class="text" />
				</div>
				<hr class="commonHr" />
				<div class="companyQualifition">
					<span class="label">&nbsp;<font color="red"
						style="margin-left: 16px;">*</font>&nbsp;相关资质：
					</span> <input type="text" class="text" />
				</div>
				<hr class="commonHr" />
				<div class="companyName">
					<span class="label" style="vertical-align: top;"><span
						style="margin-left: 26px;">公司名称：</span></span>
					<textarea rows="3" class="textarea"></textarea>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="btn">提交</div>
		</div>
	</div>
</body>
</html>