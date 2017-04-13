var arryData = [ {
	"menuId" : 10,
	"id" : 10,
	"type" : 0,
	"text" : "需求信息管理",
	"src" : "/process/demanders",
	"pic" : "demander.png",
	"active" : 0
}, {
	"menuId" : 11,
	"id" : 11,
	"type" : 0,
	"text" : "注册信息管理",
	"src" : "/process/registers",
	"pic" : "register.png",
	"active" : 0
}, {
	"menuId" : 12,
	"id" : 12,
	"type" : 0,
	"text" : "了解弘弘维护",
	"src" : "/picword/list",
	"pic" : "know.png",
	"active" : 0
}, ];
/** 初始化菜单 */
function addMenu() {
	var t = "";
	for (var i = 0; i < arryData.length; i++) {
		if (arryData[i].type == 0) {
			if (arryData[i].active == 1) {
				t = t + "<div class='menu_head menu_head_select' id='menu_head"
						+ arryData[i].id + "' onclick='javascript:changeMenu("
						+ arryData[i].id + ")'>";
			} else {
				t = t + "<div class='menu_head' id='menu_head" + arryData[i].id
						+ "' onclick='javascript:changeMenu(" + arryData[i].id
						+ ")'>";
			}
			t = t + "	<table><tr>";
			t = t
					+ "	<td width=20px style='padding-bottom: 10px;'><img class='arrowimg arrowimgright' id='arrowimgright"
					+ arryData[i].id + "' src='" + basePath
					+ "/assets/img/arrowright.png' height='10' width='10'>"
					+ "<img class='arrowimg arrowimgdown' id='arrowimgdown"
					+ arryData[i].id + "' src='" + basePath
					+ "/assets/img/arrowdown.png' height='10' width='10'>"
			"</td>";
			t = t + "	<td width=40px><img src='" + basePath + "/assets/img/"
					+ arryData[i].pic + "' height='30' width='30'></td>";
			t = t + "<td ><div id='menu_body_a=" + arryData[i].id + "'>"
					+ arryData[i].text + "</div></td>";
			t = t + "	<td nowrap>";
			t = t + "</td>";
			t = t + "	</tr></table>";
			t = t + "</div>";
		}
	}

	$("#firstpane").append(t);
	$(".arrowimgdown").hide();
}

function changeMenu(menuId) {
	$(".menu_head").removeClass("menu_head_select");
	$("#menu_head" + menuId).addClass("menu_head_select");
	var menusrc = "";
	for (var i = 0; i < arryData.length; i++) {
		if (arryData[i].id == menuId)
			menusrc = arryData[i].src;
	}
	if (menusrc == "") {
		$("#iframeMain").attr("src", basePath + "/building.htm");
	} else {
		$("#iframeMain").attr("src", basePath + "/" + menusrc);
	}
}

/** 转向主页面 */
function turnToPage(src, leftMenuId) {
	var leftMenuArr = $("#menuLeft").children("a");
	for (var i = 0; i < leftMenuArr.length; i++) {
		$(leftMenuArr.get(i)).removeClass("current");
	}
	$("#leftMenu" + leftMenuId).addClass("current");
	$("#iframeMain").attr("src", basePath + "/" + src);
}
/** 退出页面 */
function quit() {
	location.href = basePath + "/inside/logout.htm";
}
/** 修改密码 */
function updatePwd() {
	$("#iframeMain").attr("src", basePath + "/inside/forgetPwd.htm");
}
function demanderRegisterCount() {
	changeMenu(11);
	$("#iframeMain").attr("src",
			basePath + "/process/registers?type=dispatchRegisterApply");
}
function providerRegisterCount() {
	changeMenu(11);
	$("#iframeMain").attr("src",
			basePath + "/process/registers?type=ordersRegisterApply");
}
function newDemanderCount() {
	changeMenu(10);
	$("#iframeMain").attr("src",
			basePath + "/process/demanders?type=newDemander");
}
function processDemanderCount() {
	changeMenu(10);
	$("#iframeMain").attr("src",
			basePath + "/process/demanders?type=processDemander");
}

function processCount() {
	$.post(basePath + "/process/processCount", {}, function(data, status) {
		processCountTask(data);
	}, "json");
}
function processCountTask(data) {
	$("#processCountBtn").empty();
	var total = 0;
	var notifications = "";
	if (data.demanderRegisterCount > 0) {
		total += data.demanderRegisterCount;
	}
	if (data.providerRegisterCount > 0) {
		total += data.providerRegisterCount;
	}
	if (data.newDemanderCount > 0) {
		total += data.newDemanderCount;
	}
	if (data.processDemanderCount > 0) {
		total += data.processDemanderCount;
	}
	if (total > 0) {
		notifications += "<a data-toggle='dropdown' class='dropdown-toggle' href='#' style='position: relative;top: 13px;height:30px;'>";
		notifications += "<i class='icon-bell-alt icon-animated-bell'></i>";
		notifications += "<span class='badge badge-important'>" + total
				+ "</span></a>";

		notifications += "<ul class='pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close'>";
		notifications += "<li class='dropdown-header'>";
		notifications += "<i class='icon-warning-sign'></i>";
		notifications += total + "条记录待处理";
		notifications += "</li>";
		if (data.newDemanderCount > 0) {
			notifications += "<li><a href='#' onclick='newDemanderCount();'>";
			notifications += "<div class='clearfix'>";
			notifications += "<span class='pull-left'>";
			notifications += "<i class='btn btn-xs no-hover btn-success icon-comment-alt'></i>";
			notifications += "新需求";
			notifications += "</span><span class='pull-right badge badge-success'>+"
					+ data.newDemanderCount + "</span></div></a></li>";
			notifications += "</li>";
		}
		if (data.processDemanderCount > 0) {
			notifications += "<li><a href='#' onclick='processDemanderCount();'>";
			notifications += "<div class='clearfix'>";
			notifications += "<span class='pull-left'>";
			notifications += "<i class='btn btn-xs no-hover btn-info icon-check'></i>";
			notifications += "处理中需求";
			notifications += "</span><span class='pull-right badge badge-info'>+"
					+ data.processDemanderCount + "</span></div></a></li>";
			notifications += "</li>";
		}
		if (data.demanderRegisterCount > 0) {
			notifications += "<li><a href='#' onclick='demanderRegisterCount();'>";
			notifications += "<div class='clearfix'>";
			notifications += "<span class='pull-left'>";
			notifications += "<i class='btn btn-xs no-hover btn-warning icon-comments-alt'></i>";
			notifications += "新注册需求服务方";
			notifications += "</span><span class='pull-right badge badge-warning'>+"
					+ data.demanderRegisterCount + "</span></div></a></li>";
			notifications += "</li>";
		}
		if (data.providerRegisterCount > 0) {
			notifications += "<li><a href='#' onclick='providerRegisterCount();'>";
			notifications += "<div class='clearfix'>";
			notifications += "<span class='pull-left'>";
			notifications += "<i class='btn btn-xs no-hover btn-pink icon-user'></i>";
			notifications += "新注册需求提供商";
			notifications += "</span><span class='pull-right badge badge-pink'>+"
					+ data.providerRegisterCount + "</span></div></a></li>";
			notifications += "</li>";
		}
		notifications += "</ul>";
	} else {
	/*	notifications = "<a data-toggle='dropdown' class='dropdown-toggle' href='#' style='position: relative;top: 13px;height:30px;'>";
		notifications += "<i class='icon-bell-alt icon-animated-bell'></i>";
		notifications += "<span class='badge badge-important'>" + total
				+ "</span></a>";

		notifications += "<ul class='pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close'>";
		notifications += "<li class='dropdown-header'>";
		notifications += "<i class='icon-warning-sign'></i>";
		notifications += total;
		notifications += "</li></ul>";*/
	}
	$("#processCountBtn").append(notifications);
}
