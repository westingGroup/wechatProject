/**
 * 初始化内部提供商更新页面
 */
function initInsideCustomerUpdate() {
	// 点击确定按钮
	$("#icuConfirmBtn").click(function() {
		// 校验姓名
		if (!validateComponent("icuLinkname", "text"))
			return false;
		// 校验电话
		if (!validateComponent("icuLinkphone", "text"))
			return false;
		$.post(basePath + "/inside/addOrUpdate", {
			id : $("#icuId").val(),
			username : $("#icuLinkname").val(),
			phone : $("#icuLinkphone").val(),
		}, function(data, status) {
			if(data=="添加成功"){
				insideCustomerPagination.updateSelfInput();// 更新父页面
				clearInsideCutomerUpdate();// 清空数据信息
				$("#insideCustomerUpdate").modal("hide");// 隐藏修改页面
			}else{
				$("#icuTipsInfo").html(data);
			}
		});
	});

	// 点击关闭按钮
	$("#icuCancelBtn").click(function() {
		clearInsideCutomerUpdate();
	});
}

/**
 * 初始化内部提供商记录信息
 */
function initInsideCustomerUpdateRecord(registerId) {
	$.get(basePath + "/inside/addOrUpdate", {
		id : registerId
	}, function(data, status) {
		$("#icuId").val(registerId);
		$("#icuLinkname").val(data.username);
		$("#icuLinkphone").val(data.phone);
	},"json");
}

/**
 * 清空内部提供商更新页面
 */
function clearInsideCutomerUpdate() {
	$("#icuLinkname").val("");
	$("#icuLinkphone").val("");
	$("#icuId").val("");
	$("#icuTipsInfo").html("");
}