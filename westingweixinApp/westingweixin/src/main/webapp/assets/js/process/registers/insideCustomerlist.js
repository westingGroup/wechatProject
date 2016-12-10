/**
 * 初始化内部提供商列表
 */
function initInsideCustomerList(currPage, pageSize) {
	insideCustomerPagination = $('#insideCustomerPagination').jqPagination({
		link_type : "self",
		link_string : basePath + "/process/listByPage",
		callback_fun : "getInsideCustomerList",
		current_page : currPage, // 设置当前页 默认为1
		paraData : {
			type : "inside",// 内部服务提供商
			status : 1
		// 正常用户
		}
	});
}

/**
 * 获取内部提供商客户列表
 */
function getInsideCustomerList(currPage, pageSize, totalPage, totalRecords,
		records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#insideCustomerFirstResult").text(firstRegisterIndex);
	$("#insideCustomerMaxResult").text(maxRegisterIndex);
	$("#insideCustomerTotalRecords").text(totalRecords);
	appendInsideCustomer(records, firstRegisterIndex);
}

/**
 * 添加内部提供商客户列表
 */
function appendInsideCustomer(registers, firstRegisterIndex) {
	$("#insideCustomerListBody").empty();
	for (var i = 0; i < registers.length; i++) {
		var register = "<tr>";
		register += "<td>" + (firstRegisterIndex + i) + "</td>";
		register += "<td>" + registers[i].username + "</td>";
		register += "<td>" + registers[i].phone + "</td>";
		register += "<td><img alt='修改' src='"
				+ basePath
				+ "/assets/img/edit.png' width='16px' height='16px'>&nbsp;&nbsp;<img alt='删除' src='"
				+ basePath
				+ "/assets/img/删除.png' width='16px' height='16px' /></td>";
		register += "</tr>";
		$("#insideCustomerListBody").append(register);
	}
	$("#insideCustomerPager").show();
	if (registers.length == 0) {
		var noTr = $("<tr></tr>");
		var td = "<td colspan='6' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#insideCustomerListBody").append(noTr);
		$("#insideCustomerPager").hide();
	}
}