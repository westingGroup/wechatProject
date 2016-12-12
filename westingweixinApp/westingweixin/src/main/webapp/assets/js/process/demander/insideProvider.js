/**
 * 初始化内部员工列表
 */
function initInsideProvider() {
	getInsideProvider(1);
	// 确定按钮
	$("#insideProviderConfirmBtn")
			.click(
					function() {
						var option
						$("#newDemanderEngineer option:first").remove();
						$("#newDemanderEngineer").prepend(
								"<option value=" + $("#insideProviderId").val()
										+ " sideType='inside'>"
										+ $("#insideProviderName").val()
										+ "</option>");
						$("#newDemanderEngineer").prepend(
								"<option value=''>工程师</option>");
						clearInsideProvider();
					});
	// 取消按钮
	$("#insideProviderCancelBtn").click(function() {
		clearInsideProvider();
	});

	// 查询按钮
	$("#insideProviderBtn").click(function() {
		getInsideProvider(1);
	});
}
/**
 * 初始化内部员工列表
 */
function getInsideProvider(currPage) {
	$.post(basePath + "/process/listInsideProviderByPage", {
		currentPage : parseInt(currPage) + 1,
		paraData : {
			linkname : $("#userName").val(),
			linkphone : $("#phone").val()
		}
	}, function(data, status) {
		appendInsideProvider(data.records);
	}, "json");
}

/**
 * 添加内部人员数据
 */
function appendInsideProvider(records) {
	$("#insideProviderBody").empty();
	for (var i = 0; i < records.length; i++) {
		var record = "<tr>";
		record += "<td style='text-align:center;'><input type = 'radio' name='insideProviderId' value="
				+ records[i].id + " username=" + records[i].username + "></td>";
		record += "<td style='text-align:center;'>" + records[i].username
				+ "</td>";
		record += "<td style='text-align:center;'>" + records[i].phone
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
	$("#realName").val("");
	$("#insideProviderName").val("");
	$("#insideProviderConfirmBtn").val("");
	$("#insideProviderTipsInfo").html("");
	$("#insideProvider").modal("hide");
	$("#insideProviderConfirmBtn").unbind("click");
	$("#insideProviderCancelBtn").unbind("click");
	$("#insideProviderBtn").unbind("click");
}