/**
 * 初始化服务方注册申请列表
 */
function initProviderRegisterApplyList(currPage, pageSize) {
	$('#providerApplyPagination').jqPagination({
		link_type : "self",
		link_string : basePath + "/process/listByPage",
		callback_fun : "getProviderRegisterApplyList",
		current_page : currPage, // 设置当前页 默认为1
		paraData : {
			type : "provider",
			status : 1
		}
	});

	// 通过
	$("#providerApplyApprovalBtn").click(
			function() {
				approvalDemanderApply("provider", 11, "providerApplyIds",
						"providerRemark");
			});

	// 拒绝
	$("#providerApplyRejectBtn").click(
			function() {
				approvalDemanderApply("provider", 10, "providerApplyIds",
						"providerRemark");
			});

	$("#providerApplyIds").val("");
}

/**
 * 获取方法
 */
function getProviderRegisterApplyList(currPage, pageSize, totalPage,
		totalRecords, records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#providerApplyFirstResult").text(firstRegisterIndex);
	$("#providerApplyMaxResult").text(maxRegisterIndex);
	$("#providerApplyTotalRecords").text(totalRecords);
	appendProviderRegisterApply(records, firstRegisterIndex);
}

/**
 * 添加需求注册申请列表
 * 
 * @param registers
 */
function appendProviderRegisterApply(registers, firstRegisterIndex) {
	$("#providerRegisterApplyListBody").empty();
	for (var i = 0; i < registers.length; i++) {
		var register = "<tr>";
		register += "<td><input type='checkbox' name='providerApplyId' value="
				+ registers[i].id + "></td>";
		register += "<td>" + (firstRegisterIndex + i) + "</td>";
		register += "<td>" + registers[i].linkname + "</td>";
		register += "<td>" + registers[i].linkphone + "</td>";
		register += "<td>" + registers[i].business + "</td>";
		register += "<td>" + registers[i].company + "</td>";
		register += "</tr>";
		$("#providerRegisterApplyListBody").append(register);
	}

	$("input[name=providerApplyId]").click(function() {
		checkApply($(this).val(), $(this).prop("checked"), "providerApplyIds");
	});
}
