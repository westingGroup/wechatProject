/**
 * 初始化需求方注册申请列表
 */
function initNewDemanderList(currPage, pageSize) {
	newDemanderPagination = $('#newDemanderPagination').jqPagination({
		link_type : "self",
		link_string : basePath + "/process/listOrdersByPage",
		callback_fun : "getNewDemanderList",
		current_page : currPage, // 设置当前页 默认为1
		paraData : {
			type : $("#newDemanderType").val()
		}
	});

	// 通过
	$("#newDemanderApprovalBtn").click(function() {
		approvalNewDemander(2);
	});

	// 拒绝
	$("#newDemanderRejectBtn").click(function() {
		approvalNewDemander(10);
	});

	clearNewDemander();
}

/**
 * 获取方法
 */
function getNewDemanderList(currPage, pageSize, totalPage, totalRecords,
		records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#newDemanderFirstResult").text(firstRegisterIndex);
	$("#newDemanderMaxResult").text(maxRegisterIndex);
	$("#newDemanderTotalRecords").text(totalRecords);
	appendNewDemander(records, firstRegisterIndex);
}

/**
 * 添加需求注册申请列表
 * 
 * @param registers
 */
function appendNewDemander(registers, firstRegisterIndex) {
	$("#newDemanderBody").empty();
	for (var i = 0; i < registers.length; i++) {
		var register = "<tr>";
		register += "<td>";
		register += "<input type='radio' name='newDemanderId' value="
				+ registers[i].id + " sideType='outside'>";
		register += "</td>";
		register += "<td>" + registers[i].serviceOrderId + "</td>";
		register += "<td>" + registers[i].category + "</td>";
		register += "<td>" + registers[i].serviceType + "</td>";
		register += "<td>" + registers[i].status + "</td>";
		register += "<td>" + registers[i].createDate + "</td>";
		register += "<td>" + registers[i].content + "</td>";
		register += "<td>" + registers[i].linkname + "</td>";
		register += "<td>" + registers[i].linkphone + "</td>";
		register += "</tr>";
		$("#newDemanderBody").append(register);
	}
	$("#newDemanderPager").show();
	$("#newDemanderApproval").show();

	if (registers.length == 0) {// 如果没有记录
		var noTr = $("<tr></tr>");
		var td = "<td colspan='9' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#newDemanderBody").append(noTr);
		$("#newDemanderPager").hide();
		$("#newDemanderApproval").hide();
	}

	$("input[name=newDemanderId]").click(
			function() {
				$("#selectNewDemanderId").val($(this).val());
				$("#insideOrOutSide").val($(this).attr("sideType"));
				$.post(basePath + "/process/outsideProviderList", {
					sId : $(this).val(),
				}, function(data, status) {
					$("#newDemanderEngineer").empty();
					var options = "<option value=''>工程师</option>";
					for (var i = 0; i < data.length; i++) {
						options += "<option value=" + data[i].applyBy + ">"
								+ data[i].applyname + "</option>";
					}
					$("#newDemanderEngineer").html(options);
				}, "json");
			});
}

/**
 * 处理新需求
 */
function approvalNewDemander(dealType) {
	var selectNewDemanderId = $("#selectNewDemanderId").val();
	var insideOrOutSide = $("#insideOrOutSide").val();
	var remark = $("#newDemanderRemark").val();
	var dealBy = "";
	var dealName = "";
	if (selectNewDemanderId == null || selectNewDemanderId == "") {
		showTipsError("请选择需要处理的需求");
		return false;
	}
	if (dealType == 2) {
		// 如果转处理中，需要选择处理的工程师
		if (!validateComponent("newDemanderEngineer", "select"))
			return false;
		dealBy = $("#newDemanderEngineer").val();
		dealName = $('#newDemanderEngineer option:selected').text();
	} else if (dealType == 10) {// 如果转废单，则需要输入备注信息
		if (!validateComponent("newDemanderRemark", "textarea"))
			return false;
		dealBy = 0;
		dealName = "";
	}
	$.post(basePath + "/process/dealDemander", {
		type : $("#newDemanderType").val(),// 新需求
		demanderIds : selectNewDemanderId,// 选择的需求id
		remark : remark,// 备注信息
		dealType : dealType,// 处理类型
		InsideOrOutSide : insideOrOutSide,// 内部人员或者外部人员
		dealBy : dealBy,// 工程师
		dealName : dealName
	}, function(data, status) {// 更新信息
		showTipsSucc(data);
		newDemanderPagination.updateSelfInput();
		processDemanderPagination.updateSelfInput();
		wasteDemanderPagination.updateSelfInput();
		clearNewDemander();
	}, "text");
}

/**
 * 清空新需求信息
 */
function clearNewDemander() {
	$("#selectNewDemanderId").val("");
	$("#insideOrOutSide").val("");
	$("#newDemanderRemark").val("");
	$("#newDemanderEngineer").empty();
	$("#newDemanderEngineer").html("<option value=''>工程师</option>");
}