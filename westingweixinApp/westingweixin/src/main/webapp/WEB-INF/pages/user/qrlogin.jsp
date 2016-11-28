<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/assets/bootstrap/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
var interval;
var num = 0;
$(function(){
	interval = setInterval(checklogin,3000);
});

function checklogin() {
	num++;
	if(num>=20) {
		clearInterval(interval);
		alert("二维码已经过期，请刷新获取新的二维码");
		return false;
	} else {
		var url = $("#ctx").val()+"/user/checkqrlogin?snum="+$("#snum").val();
		$.get(url,function(data){
			if(data==1) {
				clearInterval(interval);
				window.location.href=$("#ctx").val()+"/user/list";
			}
		});
	}
}
</script>
</head>
<body>
	<input type="hidden" id="snum" value="${wq.snum }"/>
	<input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
	<img src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${wq.ticket }"/>
</body>
</html>