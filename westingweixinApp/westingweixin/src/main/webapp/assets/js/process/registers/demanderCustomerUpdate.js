/**
 * 初始化服务需求方更新页面
 */
function initDemanderCustomerUpdate() {
	// 点击确定按钮
	$("#dcuConfirmBtn").click(function() {
		// 校验姓名
		if (!validateComponent("dcuLinkname", "text"))
			return false;
		// 校验电话
		if (!validateComponent("dcuLinkphone", "text"))
			return false;
		// 校验公司名称
		if (!validateComponent("dcuCompany", "text"))
			return false;
		// 校验行业和相关业务
		if (!validateComponent("dcuBusiness", "textarea"))
			return false;
		$.post(basePath + "/demander/update/" + $("#dcuId").val(), {
			linkname : $("#dcuLinkname").val(),
			linkphone : $("#dcuLinkphone").val(),
			birth : $("#dcuBirth").val(),
			company : $("#dcuCompany").val(),
			business : $("#dcuBusiness").val()
		}, function(data, status) {
			$("#dcuTipsInfo").html(data);
			demanderCustomerPagination.updateSelfInput();// 更新父页面
			clearDemanderCustomerUpdate();// 清空数据信息
			$("#demanderCustomerUpdate").modal("hide");// 隐藏修改页面
		});
	});

	// 点击关闭按钮
	$("#dcuCancelBtn").click(function() {
		clearDemanderCustomerUpdate();
	});
}

/**
 * 初始化服务需求方更新记录信息
 */
function initDemanderCustomerUpdateRecord(registerId) {
	$.get(basePath + "/demander/update/" + registerId, {}, function(data,
			status) {
		$("#dcuId").val(registerId);
		$("#dcuLinkname").val(data.linkname);
		$("#dcuLinkphone").val(data.linkphone);
		$("#dcuBirth").val(data.birthDate);
		$("#dcuCompany").val(data.company);
		$("#dcuBusiness").val(data.business);
	}, "json");
}

/**
 * 清空数据信息
 */
function clearDemanderCustomerUpdate() {
	$("#dcuLinkname").val("");
	$("#dcuLinkphone").val("");
	$("dcuBirth").val("");
	$("#dcuCompany").val("");
	$("#dcuBusiness").val("");
	$("#dcuId").val("");
	$("#dcuTipsInfo").html("");
}