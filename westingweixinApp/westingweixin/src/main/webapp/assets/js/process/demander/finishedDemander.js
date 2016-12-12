/**
 * 初始化已完成的服务单
 */
function initFinishedDemander() {
	// 点击查询按钮，进行数据的查询
	$("#finishedDemanderBtn").click(function() {
		initFinishedDemanderList(1);
	});
	initFinishedDemanderList(1);
}
/**
 * 初始化已完成的服务单列表
 * 
 * @param currPage当前页
 * @param pageSize每页的记录条数
 */
function initFinishedDemanderList(currPage) {
	finishedDemanderPagination = $('#finishedDemanderPagination').jqPagination(
			{
				link_type : "self",
				link_string : basePath + "/process/listOrdersByPage",
				callback_fun : "getFinishedDemanderList",
				current_page : currPage, // 设置当前页 默认为1
				paraData : {
					type : $("#finishedDemanderType").val(),
					serviceOrderId : $("#finishedDemanderSOI").val(),// 流水号
					linkname : $("#finishedDemanderLN").val(),// 联系人
					linkphone : $("#finishedDemanderLP").val()
				// 联系方式
				}
			});
}

/**
 * 获取已完成服务单列表
 */
function getFinishedDemanderList(currPage, pageSize, totalPage, totalRecords,
		records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#finishedDemanderFirstResult").text(firstRegisterIndex);
	$("#finishedDemanderMaxResult").text(maxRegisterIndex);
	$("#finishedDemanderTotalRecords").text(totalRecords);
	appendFinishedDemander(records, firstRegisterIndex);
}

/**
 * 添加已完成服务单列表
 */
function appendFinishedDemander(registers, firstRegisterIndex) {
	$("#finishedDemanderBody").empty();
	for (var i = 0; i < registers.length; i++) {
		var register = "<tr>";
		register += "<td>" + registers[i].serviceOrderId + "</td>";
		register += "<td>" + registers[i].category + "</td>";
		register += "<td>" + registers[i].serviceType + "</td>";
		register += "<td>" + registers[i].status + "</td>";
		register += "<td>" + registers[i].createDate + "</td>";
		register += "<td>" + registers[i].content + "</td>";
		register += "<td>" + registers[i].linkname + "</td>";
		register += "<td>" + registers[i].linkphone + "</td>";
		register += "<td>" + registers[i].dealname + "</td>";
		register += "</tr>";
		$("#finishedDemanderBody").append(register);
	}
	$("#finishedDemanderPager").show();
	if (registers.length == 0) {
		var noTr = $("<tr></tr>");
		var td = "<td colspan='9' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#finishedDemanderBody").append(noTr);
		$("#finishedDemanderPager").hide();
	}
}