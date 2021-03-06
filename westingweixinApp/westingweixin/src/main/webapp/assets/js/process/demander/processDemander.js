/**
 * 初始化处理中需求
 */
function initProcessDemander() {
	$("#processDemanderBtn").click(function() {
		initProcessDemanderList(1);
	});

	// 转带分配或是新需求
	$("#processDemanderReturnBtn").click(function() {
		approvalProcessDemander(0);
	});

	// 审批通过
	$("#processDemanderApprovalBtn").click(function() {
		approvalProcessDemander(90);
	});

	// 审批拒绝
	$("#processDemanderRejectBtn").click(function() {
		approvalProcessDemander(100);
	});

	// 全选时
	$("#processDemanderAll").click(
			function() {
				checkAllApplyForSelect("processDemanderAll",
						"processDemanderId", "processDemanderIds");
			});
	initProcessDemanderList(1);
}
/**
 * 初始化处理中需求列表
 * 
 * @param currPage
 * @param pageSize
 */
function initProcessDemanderList(currPage) {
	processDemanderPagination = $('#processDemanderPagination').jqPagination({
		link_type : "self",
		link_string : basePath + "/process/listOrdersByPage",
		callback_fun : "getProcessDemanderList",
		current_page : currPage, // 设置当前页 默认为1
		paraData : {
			type : $("#processDemanderType").val(),
			serviceOrderId : $("#processDemanderSOI").val(),// 流水号
			linkname : $("#processDemanderLN").val(),// 联系人
			linkphone : $("#processDemanderLP").val(),
			category : $("#processDemanderCG").val(),
			serviceType : $("#processDemanderST").val()
		// 联系方式
		}
	});
	clearProcessDemander();
}

/**
 * 获取方法
 */
function getProcessDemanderList(currPage, pageSize, totalPage, totalRecords,
		records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#processDemanderFirstResult").text(firstRegisterIndex);
	$("#processDemanderMaxResult").text(maxRegisterIndex);
	$("#processDemanderTotalRecords").text(totalRecords);
	appendProcessDemander(records, firstRegisterIndex);
}

/**
 * 添加需求注册申请列表
 * 
 * @param registers
 */
function appendProcessDemander(registers, firstRegisterIndex) {
	$("#processDemanderBody").empty();
	for (var i = 0; i < registers.length; i++) {
		var register = "<tr>";
		register += "<td>";
		if (checkApplyInStrForJudge(registers[i].id, "processDemanderIds"))// 如果选中
			register += "<input type='checkbox' name='processDemanderId' value="
					+ registers[i].id + " checked>";
		else
			// 如果未选中
			register += "<input type='checkbox' name='processDemanderId' value="
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
		register += "<td>";
		register += "<input type='text' class='text required num maxlength' maxlength='10' label='价格' placeholder='请输入价格(必输)'";
		register += " id='price" + registers[i].applyId + "' value='"
				+ registers[i].price + "'";
		register += " style='background: rgb(247, 156, 127) !important; border-bottom: 1px solid white;' />";
		register += "<img alt='修改' src='"
				+ basePath
				+ "/assets/img/edit.png' width='16px' height='16px' apply_id="
				+ registers[i].applyId
				+ " class='imgUpdate processDemanderImg' title='修改'>";
		register += "</td>";
		register += "<td>" + registers[i].linkname + "</td>";
		register += "<td>" + registers[i].linkphone + "</td>";
		register += "<td>" + registers[i].dealname + "</td>";
		register += "</tr>";
		$("#processDemanderBody").append(register);
	}
	$("#processDemanderPager").show();
	$("#processDemanderApproval").show();

	if (registers.length == 0) {// 如果没有记录
		var noTr = $("<tr></tr>");
		var td = "<td colspan='11' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#processDemanderBody").append(noTr);
		$("#processDemanderPager").css("display", "none");
		$("#processDemanderApproval").css("display", "none");
	} else {
		$("#processDemanderPager").css("display", "inline-table");
		$("#processDemanderApproval").css("display", "inline-table");
	}

	// 如果当前页的所有项都被选中，则全选按钮被选中;如果当前页的所有项没有都被选中，则全选按钮不选中
	checkAllInStrForJudge("processDemanderId", "processDemanderIds",
			"processDemanderAll");

	$("input[name=processDemanderId]").click(
			function() {
				checkApplyForSelect(2, $(this).val(), $(this).prop("checked"),
						"processDemanderIds", "processDemanderId",
						"processDemanderAll");
			});
	
	//修改价格操作
	$(".processDemanderImg").click(function() {
		var applyId = $(this).attr("apply_id");
		// 校验价格信息
		if (!validateComponent("price" + applyId, "text"))
			return false;
		$.confirm({
			title : '确认',
			content : '确定要修改吗?',
			confirm : function() {
				$.get(basePath + "/process/priceUpdate/" + applyId, {
					price : $("#price"+applyId).val()
				}, function(data, status) {
					if (data != null && data != "") {
						showTipsSucc(data);
						processDemanderPagination.updateSelfInput();
					}
				}, "text").complete(function(jqXHR, textStatus) {
					var sessionstatus = jqXHR.getResponseHeader("sessionstatus");
					if (sessionstatus == "timeout") {
						alert("请求超时，请联系管理员");
						var url = window.parent.location.pathname;
						window.parent.location.href = url;
					}
				});
			},
			cancel : function() {
			}
		});
	});

}

/**
 * 审批申请
 */
function approvalProcessDemander(dealType) {
	var processDemanderIds = $("#processDemanderIds").val();
	var processDemanderRemark = $("#processDemanderRemark").val();
	if (processDemanderIds == null || processDemanderIds == "") {
		showTipsError("请选择需要处理的服务单");
		return false;
	}
	if (dealType == 100)// 如果转为废单
		if (!validateComponent("processDemanderRemark", "textarea"))
			return false;

	$.post(basePath + "/process/dealDemander", {
		from : "pc",
		type : $("#processDemanderType").val(),// 处理中
		demanderIds : processDemanderIds,// 选择的需求id
		remark : processDemanderRemark,// 备注信息
		dealType : dealType,// 处理类型
		dealBy : 0,// 工程师
	}, function(data, status) {// 更新信息
		if (data != null && data != "") {
			showTipsSucc(data);
			newDemanderPagination.updateSelfInput();
			processDemanderPagination.updateSelfInput();
			finishedDemanderPagination.updateSelfInput();
			wasteDemanderPagination.updateSelfInput();
			clearProcessDemander();
		}
	}, "json").complete(function(jqXHR, textStatus) {
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
function clearProcessDemander() {
	$("#processDemanderIds").val("");
	$("#processDemanderRemark").val("");
}
