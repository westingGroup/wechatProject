<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>添加红红知道详情</title>
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
<link href="<%=request.getContextPath()%>/assets/style/umeditor/default/css/umeditor.css" type="text/css" rel="stylesheet">
</head>
<script type="text/javascript">
	$(function(){
		var htmlContents = '${words.htmlContents}';
		$("#htmlContents").html(htmlContents);
	});
</script>
<body>
<span id="htmlContents"></span>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/assets/js/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath()%>/assets/js/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/umeditor/zh-cn.js"></script>
</body>
</html>