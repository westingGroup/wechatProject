var insideProviderPagination = null;
/**
 * 初始化内部员工列表
 */
function initInsideProvider() {
	// 确定按钮
	$("#insideProviderConfirmBtn")
			.click(
					function() {
						if ($("#insideProviderId").val() == null
								|| $("#insideProviderId").val() == "") {
							$("#insideProviderTipsInfo").html("请选择要处理的内部人员");
							return false;
						}

						$("#newDemanderEngineer option:first").remove();
						$("#newDemanderEngineer").prepend(
								"<option value=" + $("#insideProviderId").val()
										+ " sideType='inside' selected>"
										+ $("#insideProviderName").val()
										+ "</option>");
						$("#insideProviderTipsInfo").html("操作成功");
						setTimeout(clearInsideProvider(), 5000);// 5秒后执行方法
					});
	// 取消按钮
	$("#insideProviderCancelBtn").click(function() {
		clearInsideProvider();
	});

	// 查询按钮
	$("#insideProviderBtn").click(function() {
		initInsideProviderList(1);
	});
}
/**
 * 初始化内部员工列表
 */
function initInsideProviderList(currPage) {
	insideProviderPagination = $('#insideProviderPagination').jqPagination({
		link_type : "self",
		link_string : basePath + "/process/listInsideProviderByPage",
		callback_fun : "initInsideProviderListCallback",
		current_page : currPage, // 设置当前页 默认为1
		paraData : {
			linkname : $("#userName").val(),
			linkphone : $("#phone").val()
		}
	});
}

/**
 * 初始化内部员工列表回调函数
 */
function initInsideProviderListCallback(currPage, pageSize, totalPage,
		totalRecords, records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#insideProviderFirstResult").text(firstRegisterIndex);
	$("#insideProviderMaxResult").text(maxRegisterIndex);
	$("#insideProviderTotalRecords").text(totalRecords);
	appendInsideProvider(records, firstRegisterIndex);
}

/**
 * 添加内部人员数据
 */
function appendInsideProvider(records, firstRegisterIndex) {
	$("#insideProviderBody").empty();
	for (var i = 0; i < records.length; i++) {
		var record = "<tr>";
		record += "<td style='text-align:center;'><input type = 'radio' name='insideProviderId' value="
				+ records[i].id + " username=" + records[i].linkname + "></td>";
		record += "<td style='text-align:center;'>" + records[i].linkname
				+ "</td>";
		record += "<td style='text-align:center;'>" + records[i].linkphone
				+ "</td>";
		record += "</tr>";
		$("#insideProviderBody").append(record);
	}

	$("input[name='insideProviderId']").click(function() {
		$("#insideProviderId").val($(this).val());
		$("#insideProviderName").val($(this).attr("username"));
	});
}

/**
 * 清空数据信息
 */
function clearInsideProvider() {
	$("#userName").val("");
	$("#phone").val("");
	$("#insideProviderId").val("");
	$("#insideProviderName").val("");
	$("#insideProviderTipsInfo").html("");
	$("#insideProvider").modal("hide");
}