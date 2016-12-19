/**
 * 初始化废单服务单
 */
function initWasteDemander() {
	// 点击查询按钮，进行数据的查询
	$("#wasteDemanderBtn").click(function() {
		initWasteDemanderList(1);
	});
	initWasteDemanderList(1);
}
/**
 * 初始化废单服务单列表
 * 
 * @param currPage
 * @param pageSize
 */
function initWasteDemanderList(currPage, pageSize) {
	wasteDemanderPagination = $('#wasteDemanderPagination').jqPagination({
		link_type : "self",
		link_string : basePath + "/process/listOrdersByPage",
		callback_fun : "getWasteDemanderList",
		current_page : currPage, // 设置当前页 默认为1
		paraData : {
			type : $("#wasteDemanderType").val(),
			serviceOrderId : $("#wasteDemanderSOI").val(),// 流水号
			linkname : $("#wasteDemanderLN").val(),// 联系人
			linkphone : $("#wasteDemanderLP").val(),
			category : $("#wasteDemanderCG").val(),
			serviceType : $("#wasteDemanderST").val()
		// 联系方式
		}
	});
}

/**
 * 获取废单服务单列表
 */
function getWasteDemanderList(currPage, pageSize, totalPage, totalRecords,
		records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#wasteDemanderFirstResult").text(firstRegisterIndex);
	$("#wasteDemanderMaxResult").text(maxRegisterIndex);
	$("#wasteDemanderTotalRecords").text(totalRecords);
	appendWasteDemander(records, firstRegisterIndex);
}

/**
 * 添加废单服务单列表
 */
function appendWasteDemander(registers, firstRegisterIndex) {
	$("#wasteDemanderBody").empty();
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
		register += "</tr>";
		$("#wasteDemanderBody").append(register);
	}
	$("#wasteDemanderPager").show();
	if (registers.length == 0) {
		var noTr = $("<tr></tr>");
		var td = "<td colspan='8' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#wasteDemanderBody").append(noTr);
		$("#wasteDemanderPager").hide();
	}
}
