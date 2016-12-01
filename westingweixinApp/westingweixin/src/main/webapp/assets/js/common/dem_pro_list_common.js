/** 定义服务需求方和服务供应方列表的公共方法 */
function renderingList(index, evaluate, last, type) {
	ratyInit(index, evaluate, type);// 初始化星级评价操作
	if (type == "demander")// 只有服务需求方才有按钮
		ratyBtnInit(index, evaluate);// 初始化星级评价按钮
	openInit(index, last);// 点击展开时，展开服务信息
	foldInit(index, last);// 点击折叠时，折叠服务信息
	// 将除去提交时间之外的所有信息隐藏
	$(".hidden" + index).addClass("hidden");
}

/**
 * 初始化星级评价操作
 */
function ratyInit(index, evaluate, type) {
	if (type == "demander" && (evaluate == null || evaluate == ""))// 如果为服务需求方,并且没有评价
		$("#raty" + index).raty({
			score : evaluate,
			click : function(score, evt) {
				$("#evaluate" + index).val(score);
			}
		});
	else if (type == "demander" && evaluate != null && evaluate != "")// 如果为服务需求方，并且已经评价
		$("#raty" + index).raty({
			score : evaluate,
			readonly : true
		});
	else if (type == "provider" && evaluate != null && evaluate != "")// 如果为服务提供商，并且已经评价
		$("#raty" + index).raty({
			number : evaluate,
			score : evaluate,
			readonly : true
		});
	else
		// 如果为服务提供商，并且还没评价
		$("#raty" + index).html("未评价");
}

/**
 * 初始化星级评价按钮
 */
function ratyBtnInit(index, evaluate) {
	// 如果已经评价完成，则不能再次评价，隐藏服务评价按钮
	if (evaluate != null && evaluate != "")
		$("#but" + index).addClass("hidden");

	// 服务评价
	$("#but" + index).click(function() {
		$.post(basePath + "/demander/evaluate/" + $("#id" + index).val(), {
			evaluate : $("#evaluate" + index).val()
		}, function(data, status) {
			if (status == "success") {
				alert(data);
				$("#but" + index).addClass("hidden");
			}
		});
	});
}

/**
 * 点击展开时，展开服务信息
 */
function openInit(index, last) {
	$("#zhankai" + index).click(function() {
		$(".hidden" + index).removeClass("hidden").addClass("show");
		$(this).removeClass("show").addClass("hidden");
		$("#zhedie" + index).removeClass("hidden").addClass("show");
		if (last != "true")
			$("#order" + index).css("marginBottom", "40px");
	});
}

/**
 * 点击折叠时，折叠服务信息
 */
function foldInit(index, last) {
	$("#zhedie" + index).click(function() {
		$(".hidden" + index).removeClass("show").addClass("hidden");
		$(this).removeClass("show").addClass("hidden");
		$("#zhankai" + index).removeClass("hidden").addClass("show");
		if (last != "true")
			$("#order" + index).css("marginBottom", "0px");
	});
}