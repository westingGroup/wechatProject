/**
 * 初始化内部提供商页面
 */
function initInsideCustomer() {
	// 点击查询按钮，执行查询操作
	$("#insideCustomerBtn").click(function() {
		initInsideCustomerList(1);
	});
	// 新增页面
	$("#insideCustomerAddBtn").click(function() {
		initInsideCustomerUpdateRecord(0);
		$("#insideCustomerUpdate").modal("show");
	});
	initInsideCustomerList(1);
}
/**
 * 初始化内部提供商列表
 */
function initInsideCustomerList(currPage) {
	insideCustomerPagination = $('#insideCustomerPagination').jqPagination({
		link_type : "self",
		link_string : basePath + "/process/listByPage",
		callback_fun : "getInsideCustomerList",
		current_page : currPage, // 设置当前页 默认为1
		paraData : {
			type : "inside",// 内部服务提供商
			status : 1,
			// 正常用户
			linkname : $("#insideCustomerLN").val(),// 联系人
			linkphone : $("#insideCustomerLP").val()
		// 联系方式
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
				+ "/assets/img/edit.png' width='16px' height='16px' registerId="
				+ registers[i].id
				+ " class='imgUpdate insideCustomerUpdateImg'>&nbsp;&nbsp;<img alt='删除' src='"
				+ basePath
				+ "/assets/img/delete.png' width='16px' height='16px' registerId="
				+ registers[i].id
				+ " class='imgDelete insideCustomerDeleteImg' /></td>";
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

	// 修改操作
	$(".insideCustomerUpdateImg").click(function() {
		initInsideCustomerUpdateRecord($(this).attr("registerId"));
		$("#insideCustomerUpdate").modal("show");
	});

	// 删除操作
	$(".insideCustomerDeleteImg").click(
			function() {
				var registerId = $(this).attr("registerId");
				$.confirm({
					title : '确认',
					content : '确定要删除吗?',
					confirm : function() {
						$.get(basePath + "/inside/delete/" + registerId, {},
								function(data, status) {
									showTipsSucc(data);
									insideCustomerPagination.updateSelfInput();
								}, "text");
					},
					cancel : function() {
					}
				});

			});
}