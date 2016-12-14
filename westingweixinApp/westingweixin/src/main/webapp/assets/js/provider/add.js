/**
 * 渲染列表
 * 
 * @param orderId
 */
function renderList(orderId) {
	$("#zhankai" + orderId).click(function() {
		$("#id").val(orderId);
		openInit(orderId);
	});
	$("#zhedie" + orderId).click(function() {
		$("#id").val(0);
		foldInit(orderId);
	});

	// 价格判断不能输入中文
	$("#price").keyup(function(event) {
		// 如果不为tab键并且不为backspace键时校验是否输入了除去数字的其他值
		if (event.which != 9 && event.which != 8)
			if (!validateComponent("price", "text"))
				return false;
	});
	// 将除去提交时间之外的所有信息隐藏
	$(".hidden" + orderId).addClass("hidden");
	$("#order" + orderId).css("backgroundColor", "white");
}

/**
 * 点击展开时，展开服务信息
 */
function openInit(orderId) {
	var count = 0;
	// 将所有的折叠掉
	$(".waitingTaskCommonStyle").each(function() {
		count++;
		if (count == $(".waitingTaskCommonStyle").length)// 最后一个
			foldInit($(this).attr("orderId"));
		else
			// 非最后一个
			foldInit($(this).attr("orderId"));
	});

	$(".hidden" + orderId).removeClass("hidden").addClass("show");
	$("#zhankai" + orderId).removeClass("show").addClass("hidden");
	$("#zhedie" + orderId).removeClass("hidden").addClass("show");
	$("#order" + orderId).css("backgroundColor", "rgb(247, 156, 127)");
	viewApply(orderId);
}

/**
 * 点击折叠时，折叠服务信息
 */
function foldInit(orderId) {
	$(".hidden" + orderId).removeClass("show").addClass("hidden");
	$("#zhedie" + orderId).removeClass("show").addClass("hidden");
	$("#zhankai" + orderId).removeClass("hidden").addClass("show");
	$("#order" + orderId).css("backgroundColor", "white");
}

/**
 * 获取分页信息
 */
function mobileApply() {
	if (isCanDown == 1)// 如果可以向下翻页
		$.post(basePath + "/provider/mobileApply", {
			currentPage : parseInt(currPage) + 1,
			applyBy : $("#applyBy").val()
		}, function(data, status) {
			var records = data.records;
			currPage = data.currentPage;
			totalPage = data.totalPage;
			isCanDown = data.isCanDown;
			for (var i = 0; i < records.length; i++) {
				appendRecord(records[i]);
			}
		}, "json");
}

/**
 * 添加服务信息
 */
function appendRecord(recordContent) {
	var record = "<div class='container waitingTaskCommonStyle' id='order"
			+ recordContent.id + "' orderId = " + recordContent.id + ">";
	record += "<hr class='viewHr'/>";
	record += "<div class='row serialNumber hidden" + recordContent.id + "'>";
	record += "<div class='col-md-1 col-xs-4 label'>流水号：</div>";
	record += "<div class='col-md-10 col-xs-6 viewContent'>"
			+ recordContent.serviceOrderId + "</div>";
	record += "<div class='col-md-1 col-xs-2'>";
	record += "<img alt='折叠' class='hidden' src='" + basePath
			+ "/assets/img/zhedie.png' id='zhedie" + recordContent.id + "'>";
	record += "</div>";
	record += "</div>";
	record += "<div class='row category hidden" + recordContent.id + "'>";
	record += "<div class='col-md-1 col-xs-4 label'>类别：</div>";
	record += "<div class='col-md-11 col-xs-8 viewContent'>"
			+ recordContent.category + "</div>";
	record += "</div>";
	record += "<div class='row serviceType hidden" + recordContent.id + "'>";
	record += "<div class='col-md-1 col-xs-4 label'>服务类型：</div>";
	record += "<div class='col-md-11 col-xs-8 viewContent'>"
			+ recordContent.serviceType + "</div>";
	record += "</div>";
	record += "<div class='row submitTime'>";
	record += "<div class='col-md-1 col-xs-4 label'>任务申请时间：</div>";
	record += "<div class='col-md-10 col-xs-6 viewContent'>"
			+ recordContent.createDate + "</div>";
	record += "<div class='col-md-1 col-xs-2'>";
	record += "<img alt='展开' class='show' src='" + basePath
			+ "/assets/img/zhankai.png' id='zhankai" + recordContent.id + "'>";
	record += "</div>";
	record += "</div>";
	record += "<div class='row serviceDemandWaiting hidden" + recordContent.id
			+ "'>";
	record += "<div class='col-md-1 col-xs-4 label'>服务需求：</div>";
	record += "<div class='col-md-11 col-xs-8 viewContent'>"
			+ recordContent.content + "</div>";
	record += "</div>";
	record += "</div>";

	$(".content").append(record);
	renderList(recordContent.id);
}

/**
 * 申领任务
 */
function applyServiceOrder() {
	// 校验是否选择要申领的服务
	if ($("#id").val() == 0) {
		showTipsError("请选择要申领的服务");
		return false;
	}
	// 校验联系人信息
	if (!validateComponent("linkname", "text"))
		return false;
	// 校验联系电话信息
	if (!validateComponent("linkphone", "text"))
		return false;

	var price = 0;
	if ($("#price").val() == null || $("#price").val() == "")
		price = 0;
	else
		price = $("#price").val();
	$.post(basePath + "/provider/add/" + $("#id").val(), {
		linkname : $("#linkname").val(),
		linkphone : $("#linkphone").val(),
		price : price,
		completeDateStr : $("#completeDate").val(),
		providerId : $("#providerId").val(),
		providerName : $("#providerName").val()
	}, function(data, status) {
		showTipsSucc(data);
		$("#order" + $("#id").val()).attr("linkname", $("#linkname").val());
		$("#order" + $("#id").val()).attr("linkphone", $("#linkphone").val());
		$("#order" + $("#id").val()).attr("price", $("#price").val());
		$("#order" + $("#id").val()).attr("completeDate",
				$("#completeDate").val());
		disableApply();
	}, "text");
}

/**
 * 查看申领信息
 * 
 * @param orderId
 */
function viewApply(orderId) {
	// 如果联系人不为空，代表已经申领了
	if ($("#order" + orderId).attr("linkname") != null
			&& $("#order" + orderId).attr("linkname") != "") {
		$("#linkname").val($("#order" + orderId).attr("linkname"));
		$("#linkphone").val($("#order" + orderId).attr("linkphone"));
		$("#price").val($("#order" + orderId).attr("price"));
		$("#completeDate").val($("#order" + orderId).attr("completeDate"));
		disableApply();
	} else {
		$("#linkname").val("");
		$("#linkphone").val("");
		$("#price").val("");
		$("#completeDate").val("");
		enableApply();
	}

}

/**
 * 使申领信息可以操作
 */
function enableApply() {
	$("#linkname").attr("readonly", false);
	$("#linkphone").attr("readonly", false);
	$("#price").attr("readonly", false);
	$("#completeDate").attr("disabled", false);
	$("#button").attr("disabled", false);
}

/**
 * 使申领信息不可以操作
 */
function disableApply() {
	$("#linkname").attr("readonly", true);
	$("#linkphone").attr("readonly", true);
	$("#price").attr("readonly", true);
	$("#completeDate").attr("disabled", true);
	$("#button").attr("disabled", true);
}
