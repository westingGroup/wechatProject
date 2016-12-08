/** 定义服务需求方和服务供应方列表的公共方法 */
function renderingList(orderId, evaluate, type) {
	ratyInit(orderId, evaluate, type);// 初始化星级评价操作
	if (type == "demander")// 只有服务需求方才有按钮
		ratyBtnInit(orderId, evaluate);// 初始化星级评价按钮
	openInit(orderId);// 点击展开时，展开服务信息
	foldInit(orderId);// 点击折叠时，折叠服务信息
	// 将除去提交时间之外的所有信息隐藏
	$(".hidden" + orderId).addClass("hidden");
}

/**
 * 初始化星级评价操作
 */
function ratyInit(orderId, evaluate, type) {
	if (type == "demander" && (evaluate == null || evaluate == ""))// 如果为服务需求方,并且没有评价
		$("#raty" + orderId).raty({
			score : evaluate,
			click : function(score, evt) {
				$("#evaluate" + orderId).val(score);
			}
		});
	else if (type == "demander" && evaluate != null && evaluate != "")// 如果为服务需求方，并且已经评价
		$("#raty" + orderId).raty({
			score : evaluate,
			readonly : true
		});
	else if (type == "provider" && evaluate != null && evaluate != "")// 如果为服务提供商，并且已经评价
		$("#raty" + orderId).raty({
			number : evaluate,
			score : evaluate,
			readonly : true
		});
	else
		// 如果为服务提供商，并且还没评价
		$("#raty" + orderId).html("未评价");
}

/**
 * 初始化星级评价按钮
 */
function ratyBtnInit(orderId, evaluate) {
	// 如果已经评价完成，则不能再次评价，隐藏服务评价按钮
	if (evaluate != null && evaluate != "")
		$("#but" + orderId).addClass("hidden");

	// 服务评价
	$("#but" + orderId).click(function() {
		$.post(basePath + "/demander/evaluate/" + $("#id" + orderId).val(), {
			evaluate : $("#evaluate" + orderId).val()
		}, function(data, status) {
			if (status == "success") {
				alert(data);
				$("#but" + orderId).addClass("hidden");
			}
		});
	});
}

/**
 * 点击展开时，展开服务信息
 */
function openInit(orderId) {
	$("#zhankai" + orderId).click(function() {
		$(".hidden" + orderId).removeClass("hidden").addClass("show");
		$(this).removeClass("show").addClass("hidden");
		$("#zhedie" + orderId).removeClass("hidden").addClass("show");
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
	});
}