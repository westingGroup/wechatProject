/**
 * 初始化服务提供商更新页面
 */
function initProviderCustomerUpdate() {
	// 点击确定按钮
	$("#pcuConfirmBtn").click(function() {
		if (!validateComponent("pcuLinkName", "text"))
			return false;
		if (!validateComponent("pcuLinkPhone", "text"))
			return false;
		if (!validateComponent("pcuBusiness", "text"))
			return false;
		$.post(basePath + "/provider/update/" + $("#pcuId").val(), {
			linkname : $("#pcuLinkName").val(),
			linkphone : $("#pcuLinkPhone").val(),
			birth : $("#pcuBirth").val(),
			business : $("#pcuBusiness").val(),
			qualification : $("#pcuQualification").val(),
			company : $("#pcuCompany").val()
		}, function(data, status) {
			$("#pcuTipsInfo").html(data);
			providerCustomerPagination.updateSelfInput();// 更新父页面
			clearProviderCustomerUpdate();// 清空数据信息
			$("#providerCustomerUpdate").modal("hide");// 隐藏修改页面
		});
	});

	// 点击取消按钮
	$("#pcuCancelBtn").click(function() {
		clearProviderCustomerUpdate();
	});
}

/**
 * 初始化要更新的服务提供商记录信息
 */
function initProviderCustomerUpdateRecord(registerId) {
	$.get(basePath + "/provider/update/" + registerId, {}, function(data,
			status) {
		$("#pcuId").val(registerId);
		$("#pcuLinkName").val(data.linkname);
		$("#pcuLinkPhone").val(data.linkphone);
		$("#pcuBirth").val(data.business);
		$("#pcuBusiness").val(data.company);
		$("#pcuQualification").val(data.qualification);
		$("#pcuCompany").val(data.company);
	}, "json");
}

/**
 * 清空服务提供商更新页面信息
 */
function clearProviderCustomerUpdate() {
	$("#pcuLinkName").val("");
	$("#pcuLinkPhone").val("");
	$("#pcuBirth").val("");
	$("#pcuBusiness").val("");
	$("#pcuQualification").val("");
	$("#pcuCompany").val("");
	$("#pcuId").val("");
	$("#pcuTipsInfo").html("");
}