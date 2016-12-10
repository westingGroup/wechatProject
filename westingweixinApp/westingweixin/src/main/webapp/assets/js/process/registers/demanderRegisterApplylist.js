/**
 * 初始化需求方注册申请列表
 */
function initDemanderRegisterApplyList(currPage, pageSize) {
	demanderApplyPagination = $('#demanderApplyPagination').jqPagination({
		link_type : "self",
		link_string : basePath + "/process/listByPage",
		callback_fun : "getDemanderRegisterApplyList",
		current_page : currPage, // 设置当前页 默认为1
		paraData : {
			type : "demander",
			status : 1
		}
	});

	// 通过
	$("#demanderApplyApprovalBtn").click(
			function() {
				approvalDemanderApply("demander", 11, "demanderApplyIds",
						"demanderRemark");
			});

	// 拒绝
	$("#demanderApplyRejectBtn").click(
			function() {
				approvalDemanderApply("demander", 10, "demanderApplyIds",
						"demanderRemark");
			});

	$("#demanderApplyAll").click(
			function() {
				checkAllApplyForSelect("demanderApplyAll", "demanderApplyId",
						"demanderApplyIds");
			});

	$("#demanderApplyIds").val("");
}

/**
 * 获取方法
 */
function getDemanderRegisterApplyList(currPage, pageSize, totalPage,
		totalRecords, records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#demanderApplyFirstResult").text(firstRegisterIndex);
	$("#demanderApplyMaxResult").text(maxRegisterIndex);
	$("#demanderApplyTotalRecords").text(totalRecords);
	appendDemanderRegisterApply(records, firstRegisterIndex);
}

/**
 * 添加需求注册申请列表
 * 
 * @param registers
 */
function appendDemanderRegisterApply(registers, firstRegisterIndex) {
	$("#demanderRegisterApplyListBody").empty();
	for (var i = 0; i < registers.length; i++) {
		var register = "<tr>";
		register += "<td>";
		if (checkApplyInStrForJudge(registers[i].id, "demanderApplyIds"))// 如果选中
			register += "<input type='checkbox' name='demanderApplyId' value="
					+ registers[i].id + " checked>";
		else
			// 如果未选中
			register += "<input type='checkbox' name='demanderApplyId' value="
					+ registers[i].id + ">";
		register += "</td>";
		register += "<td>" + (firstRegisterIndex + i) + "</td>";
		register += "<td>" + registers[i].linkname + "</td>";
		register += "<td>" + registers[i].linkphone + "</td>";
		register += "<td>" + registers[i].business + "</td>";
		register += "<td>" + registers[i].company + "</td>";
		register += "</tr>";
		$("#demanderRegisterApplyListBody").append(register);
	}
	$("#demanderApplyPager").show();
	$("#demanderApplyApproval").show();

	if (registers.length == 0) {// 如果没有记录
		var noTr = $("<tr></tr>");
		var td = "<td colspan='6' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#demanderRegisterApplyListBody").append(noTr);
		$("#demanderApplyPager").hide();
		$("#demanderApplyApproval").hide();
	}

	// 如果当前页的所有项都被选中，则全选按钮被选中;如果当前页的所有项没有都被选中，则全选按钮不选中
	checkAllInStrForJudge("demanderApplyId", "demanderApplyIds",
			"demanderApplyAll");

	$("input[name=demanderApplyId]").click(
			function() {
				checkApplyForSelect(2, $(this).val(), $(this).prop("checked"),
						"demanderApplyIds", "demanderApplyId",
						"demanderApplyAll");
			});
}