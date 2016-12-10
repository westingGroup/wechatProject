/**
 * 初始化服务方注册申请列表
 */
function initProviderRegisterApplyList(currPage, pageSize) {
	providerApplyPagination = $('#providerApplyPagination').jqPagination({
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

	$("#providerApplyAll").click(
			function() {
				checkAllApplyForSelect("providerApplyAll", "providerApplyId",
						"providerApplyIds");
			});
	clearProviderApply();//清除服务提供商参数
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
		if (checkApplyInStrForJudge(registers[i].id, "providerApplyIds"))// 如果选中
			register += "<td><input type='checkbox' name='providerApplyId' value="
					+ registers[i].id + " checked></td>";
		else
			// 如果未选中
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

	$("#providerApplyPager").show();
	$("#providerApplyApproval").show();
	if (registers.length == 0) {// 如果没有记录
		var noTr = $("<tr></tr>");
		var td = "<td colspan='6' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#providerRegisterApplyListBody").append(noTr);
		$("#providerApplyPager").hide();
		$("#providerApplyApproval").hide();
	}

	// 如果当前页的所有项都被选中，则全选按钮被选中;如果当前页的所有项没有都被选中，则全选按钮不选中
	checkAllInStrForJudge("providerApplyId", "providerApplyIds",
			"providerApplyAll");

	$("input[name=providerApplyId]").click(
			function() {
				checkApplyForSelect(2, $(this).val(), $(this).prop("checked"),
						"providerApplyIds", "providerApplyId",
						"providerApplyAll");
			});
}

/**
 * 清除服务申请参数
 */
function clearProviderApply(){
	$("#providerApplyIds").val("");
	$("#providerRemark").val("");
}
