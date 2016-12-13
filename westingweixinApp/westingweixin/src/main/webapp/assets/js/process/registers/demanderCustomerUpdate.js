/**
 * 初始化服务需求方更新页面
 */
function initDemanderCustomerUpdate() {
	// 点击确定按钮
	$("#demanderCustomerUpdateConfirmBtn").click(function() {
		// if(!validateComponent())
	});

	// 点击关闭按钮
	$("#demanderCustomerUpdateCancelBtn").click(function() {

	});
}

/**
 * 初始化服务需求方更新记录信息
 */
function initDemanderCustomerUpdateRecord(registerId) {
	$.get(basePath + "/demander/update/" + registerId, {}, function(data,
			status) {
		alert(data.linkname);
		$("#linkname").val(data.linkname);
		$("#linkphone").val(data.linkphone);
		$("#birth").val(data.birthDate);
		$("#company").val(data.company);
		$("#business").val(data.business);
	}, "json");
}