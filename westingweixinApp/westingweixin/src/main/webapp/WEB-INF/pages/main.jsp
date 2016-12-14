<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/main/main.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/assets/css/main/menu.css">
<script
	src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/main/main.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/main/menu.js"></script>
<script type="text/javascript">
	var basePath = "<%=request.getContextPath()%>";
	$(function() {
		var userName = '${sessionScope.insiderProvider.username}';
		addMenu();
		$('#usernamediv').html(userName);
	});
</script>
</head>
<body>
	<div class="page_all">
		<div class="page_top">
			<div class="logo">
				<img alt="" src="<%=request.getContextPath()%>/assets/img/west.png"
					height="48" width="80">
			</div>

			<table id="menuTable" class="menuTable">
				<tr height=50px>
					<td></td>
					<td width=100px align=center style="padding-top: 10px;">
						<div style="width: 30px; float: left;">
							<img id='userhead'
								src="<%=request.getContextPath()%>/assets/img/userhead.png"
								height="28" width="28"> <span></span>
						</div>
						<div id='usernamediv'
							style="width: 60px; height: 30px; line-height: 30px; display: table; vertical-align: middle; text-align: left;">赵子龙</div>
					</td>
					<td width=60px style="padding-top: 10px;"><img title="修改密码"
						alt="修改密码" style="cursor: pointer;"
						src="<%=request.getContextPath()%>/assets/img/updatePwd.png"
						onclick="updatePwd();">&nbsp;&nbsp;<img title="登出" alt="登出"
						style='cursor: pointer;' onclick='quit();'
						src="<%=request.getContextPath()%>/assets/img/logout.png"
						width="20px" height="20px"></td>
				</tr>
			</table>

		</div>
		<div class="page_content">
			<div class="page_left">
				<div id="firstpane" class="menu_list"></div>
			</div>
			<div class="page_right" style="font-size: 0;">
				<iframe frameborder="0" height=100% width=100%
					style="display: inline-block; margin-right: -3px;"
					src="<%=request.getContextPath()%>/process/index.do"
					id="iframeMain"></iframe>
			</div>
		</div>
	</div>
</body>
</html>