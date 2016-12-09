/**
 * 初始化需求方列表
 */
function initDemanderCustomerList(currPage, pageSize) {
	$('#demanderCustomerPagination').jqPagination({
		link_type : "self",
		link_string : basePath + "/process/listByPage",
		callback_fun : "getDemanderCustomerList",
		current_page : currPage, // 设置当前页 默认为1
		paraData : {
			type : "demander",
			status : 11
		}
	});
}

/**
 * 获取需求方客户列表
 */
function getDemanderCustomerList(currPage, pageSize, totalPage, totalRecords,
		records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#demanderCustomerFirstResult").text(firstRegisterIndex);
	$("#demanderCustomerMaxResult").text(maxRegisterIndex);
	$("#demanderCustomerTotalRecords").text(totalRecords);
	appendDemanderCustomer(records, firstRegisterIndex);
}

/**
 * 添加需求方客户列表
 */
function appendDemanderCustomer() {
	$("#demanderCustomerListBody").empty();
	for (var i = 0; i < registers.length; i++) {
		var register = "<tr>";
		register += "<td>" + (firstRegisterIndex + i) + "</td>";
		register += "<td>" + registers[i].linkname + "</td>";
		register += "<td>" + registers[i].linkphone + "</td>";
		register += "<td>" + registers[i].business + "</td>";
		register += "<td>" + registers[i].company + "</td>";
		register += "<td></td>";
		register += "</tr>";
		$("#demanderCustomerListBody").append(register);
	}
}