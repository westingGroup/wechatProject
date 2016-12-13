/**
 * 初始化服务需求方更新页面
 */
function initDemanderCustomerUpdate() {
	// 点击确定按钮
	$("#demanderCustomerUpdateConfirmBtn")
			.click(
					function() {
						// 校验姓名
						if (!validateComponent("demanderCustomerUpdateLN",
								"text"))
							return false;
						// 校验电话
						if (!validateComponent("demanderCustomerUpdateLP",
								"text"))
							return false;
						// 校验公司名称
						if (!validateComponent("demanderCustomerUpdateCompany",
								"text"))
							return false;
						// 校验行业和相关业务
						if (!validateComponent(
								"demanderCustomerUpdateBusiness", "textarea"))
							return false;
						$.post(basePath + "/demander/update/"
								+ $("#demanderCustomerUpdateId").val(),
								{
									linkname : $("#demanderCustomerUpdateLN")
											.val(),
									linkphone : $("#demanderCustomerUpdateLP")
											.val(),
									birth : $("#demanderCustomerUpdateBirth")
											.val(),
									company : $(
											"#demanderCustomerUpdateCompany")
											.val(),
									business : $(
											"#demanderCustomerUpdateBusiness")
											.val()
								}, function(data, status) {
									$("#demanderCustomerUpdateTipsInfo").html(
											data);
									demanderCustomerPagination
											.updateSelfInput();// 更新父页面
									clearDemanderCustomerUpdate();// 清空数据信息
									$("#demanderCustomerUpdate").modal("hide");// 隐藏修改页面
								}, "text");
					});

	// 点击关闭按钮
	$("#demanderCustomerUpdateCancelBtn").click(function() {
		clearDemanderCustomerUpdate();
	});
}

/**
 * 初始化服务需求方更新记录信息
 */
function initDemanderCustomerUpdateRecord(registerId) {
	$.get(basePath + "/demander/update/" + registerId, {}, function(data,
			status) {
		$("#demanderCustomerUpdateId").val(registerId);
		$("#demanderCustomerUpdateLN").val(data.linkname);
		$("#demanderCustomerUpdateLP").val(data.linkphone);
		$("#demanderCustomerUpdateBirth").val(data.birthDate);
		$("#demanderCustomerUpdateCompany").val(data.company);
		$("#demanderCustomerUpdateBusiness").val(data.business);
	}, "json");
}

/**
 * 清空数据信息
 */
function clearDemanderCustomerUpdate() {
	$("#demanderCustomerUpdateLN").val("");
	$("#demanderCustomerUpdateLP").val("");
	$("demanderCustomerUpdateBirth").val("");
	$("#demanderCustomerUpdateCompany").val("");
	$("#demanderCustomerUpdateBusiness").val("");
	$("#demanderCustomerUpdateId").val("");
	$("#demanderCustomerUpdateTipsInfo").html("");
}