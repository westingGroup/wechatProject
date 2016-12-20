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
	$("#button").attr("disabled", false);
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
	if ($("#linkname" + orderId).val() != null
			&& $("#linkname" + orderId).val() != "")
		$("#button").attr("disabled", true);
	else
		$("#button").attr("disabled", false);
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

// 检测是否已经取到了数据，如果已经取到了数据，flag设置为true；如果没有取到数据，则设置为false
var flag = true;
/**
 * 获取分页信息
 */
function mobileApply() {
	// 防止多次联系查询
	if (!flag)
		return;
	if (isCanDown == 1) {// 如果可以向下翻页
		flag = false;
		$.post(basePath + "/provider/mobileApply", {
			currentPage : parseInt(currPage) + 1,
			applyBy : $("#applyBy").val()
		}, function(data, status) {
			flag = true;
			var records = data.records;
			currPage = data.currentPage;
			totalPage = data.totalPage;
			isCanDown = data.isCanDown;
			for (var i = 0; i < records.length; i++) {
				appendRecord(records[i]);
			}
		}, "json");
	}
}

/**
 * 添加服务信息
 */
function appendRecord(recordContent) {
	var record = "<div class='container waitingTaskCommonStyle' id='order"
			+ recordContent.id + "' orderId = " + recordContent.id + ">";
	record += "<hr class='viewHr'/>";
	record += "<div class='row serialNumber hidden" + recordContent.id + "'>";
	record += "<div class='col-md-2 col-xs-4 label'>流水号：</div>";
	record += "<div class='col-md-9 col-xs-6 viewContent'>"
			+ recordContent.serviceOrderId + "</div>";
	record += "<div class='col-md-1 col-xs-2'>";
	record += "<img alt='折叠' class='hidden' src='" + basePath
			+ "/assets/img/zhedie.png' id='zhedie" + recordContent.id + "'>";
	record += "</div>";
	record += "</div>";
	record += "<div class='row category hidden" + recordContent.id + "'>";
	record += "<div class='col-md-2 col-xs-4 label'>类别：</div>";
	record += "<div class='col-md-10 col-xs-8 viewContent'>"
			+ recordContent.category + "</div>";
	record += "</div>";
	record += "<div class='row serviceType hidden" + recordContent.id + "'>";
	record += "<div class='col-md-2 col-xs-4 label'>服务类型：</div>";
	record += "<div class='col-md-10 col-xs-8 viewContent'>"
			+ recordContent.serviceType + "</div>";
	record += "</div>";
	record += "<div class='row submitTime'>";
	record += "<div class='col-md-2 col-xs-4 label'>任务申请时间：</div>";
	record += "<div class='col-md-9 col-xs-6 viewContent'>"
			+ recordContent.createDate + "</div>";
	record += "<div class='col-md-1 col-xs-2'>";
	record += "<img alt='展开' class='show' src='" + basePath
			+ "/assets/img/zhankai.png' id='zhankai" + recordContent.id + "'>";
	record += "</div>";
	record += "</div>";
	record += "<div class='row serviceDemandWaiting hidden" + recordContent.id
			+ "'>";
	record += "<div class='col-md-2 col-xs-4 label'>服务需求：</div>";
	record += "<div class='col-md-10 col-xs-8 viewContent'>"
			+ recordContent.content + "</div>";
	record += "</div>";
	record += "<div class='row hidden"+recordContent.id+"'>";
	record += "<div class='col-md-6 col-xs-6' style='padding-left: 7px;'>";
	record += "<input type='text' class='text required maxlength' label='联系人' maxlength='255' placeholder='*联系人' id='linkname"+recordContent.id+"' style='background: rgb(247, 156, 127) !important; border-bottom: 1px solid white;' />";
	record += "</div>";
	record += "<div class='col-md-6 col-xs-6' style='padding-left: 7px;'>";
	record += "<input type='text' class='text required phone maxlength' label='联系电话' maxlength='13' placeholder='*联系电话' id='linkphone"+recordContent.id+"' style='background: rgb(247, 156, 127) !important; border-bottom: 1px solid white;' />";
	record += "</div>";
	record += "</div>";
	record += "<div class='row hidden"+recordContent.id+"'>";
	record += "<div class='col-md-6 col-xs-6' style='padding-left: 7px;'>";
	record += "<input type='text' class='text num' label='价格' placeholder='价格' id='price"+recordContent.id+"' style='background: rgb(247, 156, 127) !important; border-bottom: 1px solid white;' />";
	record += "</div>";
	record += "<div class='col-md-6 col-xs-6 input-append date form_datetime_day' style='padding-left: 7px; padding-right: 0px;'>";
	record += "<div class='row' style='padding-top: 0px; margin-left: 0px; padding-right: 0px;'>";
	record += "<div class='col-md-11 col-xs-8' style='padding-left: 0px; padding-right: 0px;'>";
	record += "<input type='text' class='text' placeholder='预计完成时间' id='completeDate"+recordContent.id+"' style='background: rgb(247, 156, 127) !important; border-bottom: 1px solid white;' />";
	record += "</div>";
	record += "<div class='col-md-1 col-xs-4' style='padding-left: 0px; padding-right: 0px;'>";
	record += "<span class='add-on'><i class='icon-remove'></i></span> <span class='add-on'><i class='icon-calendar'></i></span>";
	record += "</div>";
	record += "</div>";
	record += "</div>";
	record += "</div>";
	record += "</div>";
	
	$(".providerOrders").append(record);
	renderList(recordContent.id);
}

/**
 * 申领任务
 */
function applyServiceOrder() {
	var id = $("#id").val();
	// 校验是否选择要申领的服务
	if (id == 0) {
		showTipsError("请选择要申领的服务");
		return false;
	}
	// 校验联系人信息
	if (!validateComponent("linkname" + id, "text"))
		return false;
	// 校验联系电话信息
	if (!validateComponent("linkphone" + id, "text"))
		return false;

	var price = 0;
	if ($("#price" + id).val() == null || $("#price" + id).val() == "")
		price = 0;
	else
		price = $("#price" + id).val();
	$.post(basePath + "/provider/add/" + id, {
		linkname : $("#linkname" + id).val(),
		linkphone : $("#linkphone" + id).val(),
		price : price,
		completeDateStr : $("#completeDate" + id).val(),
		providerId : $("#providerId").val(),
		providerName : $("#providerName").val(),
		providerType : $("#providerType").val()
	}, function(data, status) {
		showTipsSucc(data);
		disableApply(id);
	});
}

/**
 * 使申领信息不可以操作
 */
function disableApply(id) {
	$("#linkname" + id).attr("readonly", true);
	$("#linkphone" + id).attr("readonly", true);
	$("#price" + id).attr("readonly", true);
	$("#completeDate" + id).attr("disabled", true);
	$("#button").attr("disabled", true);
}