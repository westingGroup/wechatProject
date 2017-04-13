/**
 * 初始化已申请需求
 */
function initApplyDemander() {
	$("#applyDemanderBtn").click(function() {
		initApplyDemanderList(1);
	});

	// 转新需求
	$("#applyDemanderReturnBtn").click(function() {
		approvalApplyDemander(0);
	});

	// 转处理中
	$("#applyDemanderProcessBtn").click(function() {
		approvalApplyDemander(12);
	});

	// 审批通过
	$("#applyDemanderApprovalBtn").click(function() {
		approvalApplyDemander(90);
	});

	// 审批拒绝
	$("#applyDemanderRejectBtn").click(function() {
		approvalApplyDemander(100);
	});

	// 全选时
	$("#applyDemanderAll").click(
			function() {
				checkAllApplyForSelect("applyDemanderAll", "applyDemanderId",
						"applyDemanderIds");
			});
	initApplyDemanderList(1);
}
/**
 * 初始化处理中需求列表
 * 
 * @param currPage
 * @param pageSize
 */
function initApplyDemanderList(currPage) {
	applyDemanderPagination = $('#applyDemanderPagination').jqPagination({
		link_type : "self",
		link_string : basePath + "/process/listOrdersByPage",
		callback_fun : "getApplyDemanderList",
		current_page : currPage, // 设置当前页 默认为1
		paraData : {
			type : $("#applyDemanderType").val(),
			serviceOrderId : $("#applyDemanderSOI").val(),// 流水号
			linkname : $("#applyDemanderLN").val(),// 联系人
			linkphone : $("#applyDemanderLP").val(),
			category : $("#applyDemanderCG").val(),
			serviceType : $("#applyDemanderST").val()
		// 联系方式
		}
	});
	clearApplyDemander();
}

/**
 * 获取方法
 */
function getApplyDemanderList(currPage, pageSize, totalPage, totalRecords,
		records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#applyDemanderFirstResult").text(firstRegisterIndex);
	$("#applyDemanderMaxResult").text(maxRegisterIndex);
	$("#applyDemanderTotalRecords").text(totalRecords);
	appendApplyDemander(records, firstRegisterIndex);
}

/**
 * 添加需求注册申请列表
 * 
 * @param registers
 */
function appendApplyDemander(registers, firstRegisterIndex) {
	$("#applyDemanderBody").empty();
	for (var i = 0; i < registers.length; i++) {
		var register = "<tr>";
		register += "<td>";
		if (checkApplyInStrForJudge(registers[i].id, "applyDemanderIds"))// 如果选中
			register += "<input type='checkbox' name='applyDemanderId' value="
					+ registers[i].id + " checked>";
		else
			// 如果未选中
			register += "<input type='checkbox' name='applyDemanderId' value="
					+ registers[i].id + ">";
		register += "</td>";
		register += "<td>" + registers[i].serviceOrderId + "</td>";
		register += "<td>" + registers[i].category + "</td>";
		register += "<td>" + registers[i].serviceType + "</td>";
		register += "<td>" + registers[i].status + "</td>";
		register += "<td>" + registers[i].createDate + "</td>";
		register += "<td><div class='demanderContent' title='"
				+ registers[i].content + "'>" + registers[i].content
				+ "</div></td>";
		register += "<td>" + registers[i].price + "</td>";
		register += "<td>" + registers[i].linkname + "</td>";
		register += "<td>" + registers[i].linkphone + "</td>";
		register += "<td>" + registers[i].dealname + "</td>";
		register += "</tr>";
		$("#applyDemanderBody").append(register);
	}
	$("#applyDemanderPager").show();
	$("#applyDemanderApproval").show();

	if (registers.length == 0) {// 如果没有记录
		var noTr = $("<tr></tr>");
		var td = "<td colspan='11' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#applyDemanderBody").append(noTr);
		$("#applyDemanderPager").css("display", "none");
		$("#applyDemanderApproval").css("display", "none");
	} else {
		$("#applyDemanderPager").css("display", "inline-table");
		$("#applyDemanderApproval").css("display", "inline-table");
	}

	// 如果当前页的所有项都被选中，则全选按钮被选中;如果当前页的所有项没有都被选中，则全选按钮不选中
	checkAllInStrForJudge("applyDemanderId", "applyDemanderIds",
			"applyDemanderAll");

	$("input[name=applyDemanderId]").click(
			function() {
				checkApplyForSelect(2, $(this).val(), $(this).prop("checked"),
						"applyDemanderIds", "applyDemanderId",
						"applyDemanderAll");
			});
}

/**
 * 审批申请
 */
function approvalApplyDemander(dealType) {
	var applyDemanderIds = $("#applyDemanderIds").val();
	var applyDemanderRemark = $("#applyDemanderRemark").val();
	if (applyDemanderIds == null || applyDemanderIds == "") {
		showTipsError("请选择需要处理的服务单");
		return false;
	}
	if (dealType == 100)// 如果转为废单
		if (!validateComponent("applyDemanderRemark", "textarea"))
			return false;

	$.post(basePath + "/process/dealDemander", {
		from :"pc",
		type : $("#applyDemanderType").val(),// 处理中
		demanderIds : applyDemanderIds,// 选择的需求id
		remark : applyDemanderRemark,// 备注信息
		dealType : dealType,// 处理类型
		dealBy : 0,// 工程师
	}, function(data, status) {// 更新信息
		if (data != null && data != "") {
			showTipsSucc(data);
			newDemanderPagination.updateSelfInput();
			processDemanderPagination.updateSelfInput();
			applyDemanderPagination.updateSelfInput();
			finishedDemanderPagination.updateSelfInput();
			wasteDemanderPagination.updateSelfInput();
			clearApplyDemander();
		}
	},"json").complete(function(jqXHR, textStatus) {
		var sessionstatus = jqXHR.getResponseHeader("sessionstatus");
		if (sessionstatus == "timeout") {
			alert("请求超时，请联系管理员");
			var url = window.parent.location.pathname;
			window.parent.location.href = url;
		}
	});
}

/**
 * 清空处理中服务
 */
function clearApplyDemander() {
	$("#applyDemanderIds").val("");
	$("#applyDemanderRemark").val("");
}