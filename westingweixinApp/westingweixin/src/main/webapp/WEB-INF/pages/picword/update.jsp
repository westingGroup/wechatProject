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
<title>更新弘弘知道</title>
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
<link
	href="<%=request.getContextPath()%>/assets/style/umeditor/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
</head>

<script type="text/javascript">
$(function(){
	$('#brief').val("${words.brief}");
	var detailsQ = '${words.htmlContents}';
	insertHtml(detailsQ);
});

function update() {
	var htmlEle = UM.getEditor('myEditor').getContent();
	$("#mytable").empty();
	$("#mytable").append(htmlEle);
	//$("#mytable").find("img").css("max-width","250px").css("max-height","250px");
	$.post("<%=request.getContextPath()%>/picword/update/"+$("#id").val() , {
		brief:$("#brief").val(),
		htmlContents:htmlEle
	}, function(data, status) {
		//alert(data);
		window.location="<%=request.getContextPath()%>/picword/list";
						});
	}
</script>
<body>
	<input type="hidden" id="id" value="${words.id}" />
	<div style="margin-top: 20px; margin-left: 5px;">
		标题：<input type="text" name="brief" id="brief"
			style="margin-right: 10px; width: 72%;" maxlength="255" placeholder="请输入标题"/> <span
			style="float: right; margin-right: 110px;"> <input
			type="submit" class="btn button" onclick="update();" value="提交" />
		</span>
	</div>
	
	<div id="mytable" style="display: none;"></div>
	<div style="margin-top: 20px;">
		<script type="text/plain" id="myEditor"
			style="width: 1050px; height: 500px;">
    <p id="firstPrag" style="font-family:微软雅黑;font-size:12px;">提示信息:标题为14号字体，内容为12号字体，统一都选微软雅黑字体</p>
</script>
	</div>
	<script type="text/javascript" charset="utf-8"
		src="<%=request.getContextPath()%>/assets/js/umeditor/umeditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=request.getContextPath()%>/assets/js/umeditor/umeditor.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/assets/js/umeditor/zh-cn.js"></script>
	<script>
		//实例化编辑器
		var um = UM.getEditor('myEditor');

		um.addListener('blur', function() {
			$('#focush2').html('编辑器失去焦点了')
		});
		um.addListener('focus', function() {
			//  alert("hihi");
			$("#firstPrag").remove();
		});
		//按钮的操作
		function insertHtml(str) {
			//var value = prompt('插入html代码', '');
			um.execCommand('insertHtml', str)
		}
		function isFocus() {
			alert(um.isFocus())
		}
		function doBlur() {
			um.blur()
		}
		function createEditor() {
			enableBtn();
			um = UM.getEditor('myEditor');
		}
		function getAllHtml() {
			alert(UM.getEditor('myEditor').getAllHtml())
		}
		function getContent() {
			var arr = [];
			arr.push("使用editor.getContent()方法可以获得编辑器的内容");
			arr.push("内容为：");
			arr.push(UM.getEditor('myEditor').getContent());
			alert(arr.join("\n"));
		}
		function getPlainTxt() {
			var arr = [];
			arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
			arr.push("内容为：");
			arr.push(UM.getEditor('myEditor').getPlainTxt());
			alert(arr.join('\n'))
		}
		function setContent(isAppendTo) {
			var arr = [];
			arr.push("使用editor.setContent('欢迎使用umeditor')方法可以设置编辑器的内容");
			UM.getEditor('myEditor').setContent('欢迎使用umeditor', isAppendTo);
			alert(arr.join("\n"));
		}
		function setDisabled() {
			UM.getEditor('myEditor').setDisabled('fullscreen');
			disableBtn("enable");
		}

		function setEnabled() {
			UM.getEditor('myEditor').setEnabled();
			enableBtn();
		}

		function getText() {
			//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
			var range = UM.getEditor('myEditor').selection.getRange();
			range.select();
			var txt = UM.getEditor('myEditor').selection.getText();
			alert(txt)
		}

		function getContentTxt() {
			var arr = [];
			arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
			arr.push("编辑器的纯文本内容为：");
			arr.push(UM.getEditor('myEditor').getContentTxt());
			alert(arr.join("\n"));
		}
		function hasContent() {
			var arr = [];
			arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
			arr.push("判断结果为：");
			arr.push(UM.getEditor('myEditor').hasContents());
			alert(arr.join("\n"));
		}
		function setFocus() {
			UM.getEditor('myEditor').focus();
		}
		function deleteEditor() {
			disableBtn();
			UM.getEditor('myEditor').destroy();
		}
		function disableBtn(str) {
			var div = document.getElementById('btns');
			var btns = domUtils.getElementsByTagName(div, "button");
			for (var i = 0, btn; btn = btns[i++];) {
				if (btn.id == str) {
					domUtils.removeAttributes(btn, [ "disabled" ]);
				} else {
					btn.setAttribute("disabled", "true");
				}
			}
		}
		function enableBtn() {
			var div = document.getElementById('btns');
			var btns = domUtils.getElementsByTagName(div, "button");
			for (var i = 0, btn; btn = btns[i++];) {
				domUtils.removeAttributes(btn, [ "disabled" ]);
			}
		}
	</script>
</body>
</html>