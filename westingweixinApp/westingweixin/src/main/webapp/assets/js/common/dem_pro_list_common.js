/** 定义服务需求方和服务供应方列表的公共方法 */
function renderingList(orderId, evaluate, type, status, evaluateContent, dealBy) {
	ratyInit(orderId, evaluate, type, status);// 初始化星级评价操作
	if (type == "demander") {// 只有服务需求方才有按钮
		ratyBtnInit(orderId, evaluate, status);// 初始化星级评价按钮
		ratyCancelBtnInit(orderId, status);
		ratyDoneBtnInit(orderId, status);
	}
	if (type == "provider") {// 只有服务提供方才有完成申请按钮
		ratyApplyBtnInit(orderId, status, dealBy);// 初始化申请按钮
	}

	// 点击服务需求 显示或隐藏服务需求的全部内容
	$("#content_" + orderId).on(
			"touchstart",
			function() {
				if ($("#content_" + orderId).attr("status") == "1") {// 展开了全部内容
					$("#contentAllDiv_" + orderId).removeClass("show")
							.addClass("hidden");
					$("#contentAll_" + orderId).removeClass("show").addClass(
							"hidden");
					$("#content_" + orderId).attr("status", "0");
				} else {
					$("#content_" + orderId).attr("status", "1");
					$("#contentAllDiv_" + orderId).removeClass("hidden")
							.addClass("show");
					$("#contentAll_" + orderId).removeClass("hidden").addClass(
							"show");
				}
			});

	openInit(orderId, type, evaluate, status, evaluateContent);// 点击展开时，展开服务信息
	foldInit(orderId);// 点击折叠时，折叠服务信息

	if (type == "demander") {
		evaluateContentInit(orderId, evaluate, status);
	}

	// 将除去提交时间之外的所有信息隐藏
	$(".hidden" + orderId).addClass("hidden");
}

/**
 * 初始化星级评价操作
 */
function ratyInit(orderId, evaluate, type, status) {
	if (type == "demander" && (status == "已完成")
			&& (evaluate == null || evaluate == "")) {// 如果为服务需求方,并且没有评价
		$("#raty" + orderId).raty({
			score : evaluate,
			click : function(score, evt) {
				$("#evaluate" + orderId).val(score);
			},
			starOff : 'star-off-big.png',
			starOn : 'star-on-big.png',
			size : 24
		});
	} else if (type == "demander" && (status == "已评价") && evaluate != null
			&& evaluate != "")// 如果为服务需求方，并且已经评价
		$("#raty" + orderId).raty({
			score : evaluate,
			readonly : true,
			starOff : 'star-off-big.png',
			starOn : 'star-on-big.png',
			size : 24
		});
	else if (type == "provider" && (status == "已评价") && evaluate != null
			&& evaluate != "")// 如果为服务提供商，并且已经评价
		$("#raty" + orderId).raty({
			number : evaluate,
			score : evaluate,
			readonly : true,
			starOff : 'star-off-big.png',
			starOn : 'star-on-big.png',
			size : 24
		});
	else {
		// 如果为服务提供商，并且还没评价
		$("#raty" + orderId).css("margin-top", "0px");
		$("#raty" + orderId).html("未评价");
	}

}

/**
 * 初始化星级评价按钮
 */
function ratyBtnInit(orderId, evaluate, status) {
	// 如果已经评价完成，则不能再次评价，隐藏服务评价按钮
	if ((evaluate != null && evaluate != "" && status == "已完成")
			|| status != "已完成") {
		$("#but" + orderId).addClass("hidden");
	}

	// 服务评价
	$("#but" + orderId).click(
			function() {
				if ($("#evaluate" + orderId).val() == null
						|| $("#evaluate" + orderId).val() == "") {
					showTipsError("评分至少一颗星");
					return false;
				}
				$.post(basePath + "/demander/evaluate/"
						+ $("#id" + orderId).val(), {
					evaluate : $("#evaluate" + orderId).val(),
					evaluateContent : $("#evaluateContent" + orderId).val()
				}, function(data, status) {
					if (status == "success") {
						showTipsSucc(data);
						$("#but" + orderId).addClass("hidden");
						$("#status" + orderId).attr("status", "已评价");
						$("#zt" + orderId).html("已评价");
						$("#evaluateContent" + orderId).attr("disabled", true);
					}
				}, "json");
			});
}

function evaluateContentInit(orderId, evaluate, status) {
	// 如果已经评价完成，则不能再次评价，隐藏服务评价按钮
	if ((evaluate != null && evaluate != "" && status == "已完成")
			|| status != "已完成") {
		$("#but" + orderId).addClass("hidden");
		$("#evaluateContentDiv" + orderId).removeClass("show").addClass(
				"hidden");
		$("#evaluateContent" + orderId).removeClass("show").addClass("hidden");
	}
}

/**
 * 初始化撤销按钮
 */
function ratyCancelBtnInit(orderId, status) {
	// 如果是新需求显示撤销按钮，其他状态隐藏撤销按钮
	if (status == "新需求")
		$("#cancelbut" + orderId).addClass("show");
	else
		$("#cancelbut" + orderId).addClass("hidden");

	// 撤销
	$("#cancelbut" + orderId).click(
			function() {
				$.post(basePath + "/demander/cancelServiceOrder/"
						+ $("#id" + orderId).val(), {
					dealBy : $("#createBy").val(),// 工程师
					dealName : $("#dealName").val()
				}, function(data, status) {
					if (status == "success") {
						showTipsSucc(data);
						$("#cancelbut" + orderId).addClass("hidden");
						$("#status" + orderId).attr("status", "废单");
						$("#zt" + orderId).html("废单");
					}
				}, "json");
			});
}

/**
 * 初始化完成申请按钮
 */
function ratyDoneBtnInit(orderId, status) {
	// 如果是已申请,可以确认完成转已完成
	if (status == "已申请")
		$("#donebut" + orderId).addClass("show");
	else
		$("#donebut" + orderId).addClass("hidden");

	// 确认完成
	$("#donebut" + orderId).click(
			function() {
				$.post(basePath + "/process/dealDemander", {
					from :"wx",
					type : "20",// 处理中
					demanderIds : $("#id" + orderId).val(),// 选择的需求id
					remark : "需求方手机端确认完成",// 备注信息
					dealType : "90",// 处理类型
					dealBy : $("#createBy").val(),// 工程师
				}, function(data, status) {// 更新信息
					if (data != null && data != "") {
						showTipsSucc(data);
						$("#donebut" + orderId).addClass("hidden");
						$("#status" + orderId).attr("status", "已完成");
						$("#zt" + orderId).html("已完成");
						// 星星 显示
						$("#but" + orderId).removeClass("hidden").addClass(
								"show");
						$("#evaluateContentDiv" + orderId)
								.removeClass("hidden").addClass("show");
						$("#evaluateContent" + orderId).removeClass("hidden")
								.addClass("show");
						$("#raty" + orderId).html("");
						$("#raty" + orderId).raty({
							score : "",
							click : function(score, evt) {
								$("#evaluate" + orderId).val(score);
							},
							starOff : 'star-off-big.png',
							starOn : 'star-on-big.png',
							size : 24
						});
					}
				}, "json");
			});
}

/**
 * 初始化申请按钮
 */
function ratyApplyBtnInit(orderId, status, dealBy) {
	var providerId = $("#providerId").val();
	// 如果是处理中显示申请按钮，其他状态隐藏申请按钮
	if (status == "待完成" && providerId == dealBy)
		$("#applybut" + orderId).addClass("show");
	else
		$("#applybut" + orderId).addClass("hidden");

	// 完成申请
	$("#applybut" + orderId).click(function() {
		$.post(basePath + "/demander/apply/" + $("#id" + orderId).val(), {
			providerId : $("#providerId").val()
		}, function(data, status) {
			if (status == "success") {
				showTipsSucc(data);
				$("#applybut" + orderId).addClass("hidden");
				$("#status" + orderId).attr("status", "已申请");
				$("#zt" + orderId).html("已申请");
			}
		}, "json");
	});
}

/**
 * 点击展开时，展开服务信息
 */
function openInit(orderId, type, evaluate, status, evaluateContent) {
	$("#zhankai" + orderId).click(
			function() {
				$(".hidden" + orderId).removeClass("hidden").addClass("show");
				// 把服务需求的div hidden
				$("#contentAllDiv_" + orderId).removeClass("show").addClass(
						"hidden");
				$("#content_" + orderId).attr("status", "0");

				if (type == "demander") {// 把评价内容div hidden
					if ((evaluate != null && evaluate != "" && status == "已完成")
							|| status != "已完成") {
						$("#evaluateContentDiv" + orderId).removeClass("show")
								.addClass("hidden");
						$("#evaluateContent" + orderId).removeClass("show")
								.addClass("hidden");
					}

					if (evaluateContent != null && evaluateContent != ""
							&& status == "已评价") {
						$("#evaluateContentDiv" + orderId)
								.removeClass("hidden").addClass("show");
						$("#evaluateContent" + orderId).removeClass("hidden")
								.addClass("show");
						$("#evaluateContent" + orderId).attr("disabled", true);
					}
				}
				if (type == "provider") {
					if (evaluateContent != null && evaluateContent != ""
							&& status == "已评价") {
						$("#evaluateContentDiv" + orderId)
								.removeClass("hidden").addClass("show");
						$("#evaluateContent" + orderId).removeClass("hidden")
								.addClass("show");
						$("#evaluateContent" + orderId).attr("disabled", true);
					} else {
						$("#evaluateContentDiv" + orderId).removeClass("show")
								.addClass("hidden");
						$("#evaluateContent" + orderId).removeClass("show")
								.addClass("hidden");
					}
				}

				$(this).removeClass("show").addClass("hidden");
				$("#zhedie" + orderId).removeClass("hidden").addClass("show");
				$("#status" + orderId).html("");
				$("#order" + orderId).css("backgroundColor",
						"rgb(247, 156, 127)");
			});
}

/**
 * 点击折叠时，折叠服务信息
 */
function foldInit(orderId) {
	$("#zhedie" + orderId).click(function() {
		$(".hidden" + orderId).removeClass("show").addClass("hidden");
		$(this).removeClass("show").addClass("hidden");
		$("#zhankai" + orderId).removeClass("hidden").addClass("show");
		$("#status" + orderId).html($("#status" + orderId).attr("status"));
		$("#order" + orderId).css("backgroundColor", "white");
	});
}