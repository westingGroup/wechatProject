<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<STYLE type=text/css>
.font14 {
	FONT-SIZE: 14px
}
.font12 {
	FONT-SIZE: 12px
}
.font12 a {
	FONT-SIZE: 12px;
	color: #CC0000;
	text-decoration: none;
}
</STYLE>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>服务需求方审批</title>
</HEAD>
<BODY>
	<TABLE height='400' cellSpacing='0' cellPadding='0' width='300' align='center'
		background='<%=request.getContextPath()%>/assets/img/x.gif' border='0'>
		<TBODY>
			<TR>
				<TD height='280'></TD>
			</TR>
			<TR>
				<TD vAlign=top>
					<DIV class='font14' align='center'>
					<c:if test="${demander.status==0 }"><STRONG>用户已经<FONT color='#0099ff'>禁用</FONT>,请联系管理员</STRONG></c:if>
					<c:if test="${demander.status==1 }"><STRONG>审批中，请等待</STRONG></c:if>
					</DIV>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</BODY>
</HTML>
