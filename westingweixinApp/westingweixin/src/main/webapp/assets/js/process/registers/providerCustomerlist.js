/**
 * 初始化服务提供商页面
 */
function initProviderCustomer() {
	// 点击查询按钮，进行查询操作
	$("#providerCustomerBtn").click(function() {
		initProviderCustomerList(1);
	});
	initProviderCustomerList(1);
}
/**
 * 初始化服务方列表
 */
function initProviderCustomerList(currPage) {
	providerCustomerPagination = $('#providerCustomerPagination').jqPagination(
			{
				link_type : "self",
				link_string : basePath + "/process/listByPage",
				callback_fun : "getProviderCustomerList",
				current_page : currPage, // 设置当前页 默认为1
				paraData : {
					type : "provider",// 服务提供方
					status : 11,
					// 已经注册通过的
					linkname : $("#providerCustomerLN").val(),// 联系人
					linkphone : $("#providerCustomerLP").val()
				// 联系方式
				}
			});
}

/**
 * 获取提供商客户列表
 */
function getProviderCustomerList(currPage, pageSize, totalPage, totalRecords,
		records) {
	var firstRegisterIndex = (parseInt(currPage) - 1) * parseInt(pageSize) + 1;
	var maxRegisterIndex = 0;
	if (currPage != totalPage)
		maxRegisterIndex = parseInt(currPage) * parseInt(pageSize);
	else
		maxRegisterIndex = totalRecords;
	$("#providerCustomerFirstResult").text(firstRegisterIndex);
	$("#providerCustomerMaxResult").text(maxRegisterIndex);
	$("#providerCustomerTotalRecords").text(totalRecords);
	appendProviderCustomer(records, firstRegisterIndex);
}

/**
 * 添加提供商客户列表
 */
function appendProviderCustomer(registers, firstRegisterIndex) {
	$("#providerCustomerListBody").empty();
	for (var i = 0; i < registers.length; i++) {
		var register = "<tr>";
		register += "<td>" + (firstRegisterIndex + i) + "</td>";
		register += "<td>" + registers[i].linkname + "</td>";
		register += "<td>" + registers[i].linkphone + "</td>";
		register += "<td>" + registers[i].business + "</td>";
		register += "<td>" + registers[i].company + "</td>";
		register += "<td>" + registers[i].status + "</td>";
		register += "<td>" + registers[i].type + "</td>";
		register += "<td><img alt='修改' src='"
				+ basePath
				+ "/assets/img/edit.png' width='16px' height='16px' registerId="
				+ registers[i].id
				+ " class='imgUpdate providerCustomerUpdateImg'>&nbsp;&nbsp;<img alt='删除' src='"
				+ basePath
				+ "/assets/img/delete.png' width='16px' height='16px' registerId="
				+ registers[i].id
				+ " class='imgDelete providerCustomerDeleteImg' />&nbsp;&nbsp;<img alt='启用' src='"
				+ basePath
				+ "/assets/img/enable.png' width='16px' height='16px' registerId="
				+ registers[i].id
				+ " class='imgDelete providerCustomerEnableImg' /></td>";
		register += "</tr>";
		$("#providerCustomerListBody").append(register);
	}
	$("#providerCustomerPager").show();
	if (registers.length == 0) {
		var noTr = $("<tr></tr>");
		var td = "<td colspan='8' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#providerCustomerListBody").append(noTr);
		$("#providerCustomerPager").hide();
	}

	// 更新操作
	$(".providerCustomerUpdateImg").click(function() {
		initProviderCustomerUpdateRecord($(this).attr("registerId"));
		$("#providerCustomerUpdate").modal("show");
	});

	// 删除操作
	$(".providerCustomerDeleteImg").click(
			function() {
				var registerId = $(this).attr("registerId");
				$.confirm({
					title : '确认',
					content : '确定要删除吗?',
					confirm : function() {
						$.get(basePath + "/provider/delete/" + registerId, {},
								function(data, status) {
									showTipsSucc(data);
									providerCustomerPagination
											.updateSelfInput();
								}, "text");
					},
					cancel : function() {
					}
				});
			});
	
	// 启用操作
	$(".providerCustomerEnableImg").click(
			function() {
				var registerId = $(this).attr("registerId");
				$.confirm({
					title : '确认',
					content : '确定要启用吗?',
					confirm : function() {
						$.post(basePath + "/provider/enable/" + registerId, {},
								function(data, status) {
									showTipsSucc(data);
									providerCustomerPagination
											.updateSelfInput();
								});
					},
					cancel : function() {
					}
				});
			});
}
