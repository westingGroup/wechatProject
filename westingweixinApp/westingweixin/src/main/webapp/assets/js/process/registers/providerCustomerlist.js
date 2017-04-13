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
		register += "<td>" + registers[i].birthDate + "</td>";
		register += "<td><div align='center'><div class='registerBusiness' title='"
				+ registers[i].business
				+ "'>"
				+ registers[i].business
				+ "</div></div></td>";
		register += "<td>" + registers[i].qualification + "</td>";
		register += "<td><div align='center'><div class='registerBusiness' title='"
				+ registers[i].company
				+ "'>"
				+ registers[i].company
				+ "</div></div></td>";
		register += "<td>" + registers[i].status + "</td>";
		register += "<td>" + registers[i].type + "</td>";
		register += "<td><img alt='修改' src='"
				+ basePath
				+ "/assets/img/edit.png' width='16px' height='16px' registerId="
				+ registers[i].id
				+ " class='imgUpdate providerCustomerUpdateImg' title='修改'>&nbsp;&nbsp;<img alt='删除' src='"
				+ basePath
				+ "/assets/img/delete.png' width='16px' height='16px' registerId="
				+ registers[i].id
				+ " class='imgDelete providerCustomerDeleteImg' title='删除'>&nbsp;&nbsp;<img alt='启用' src='"
				+ basePath
				+ "/assets/img/enable.png' width='16px' height='16px' registerId="
				+ registers[i].id
				+ " class='imgDelete providerCustomerEnableImg' title='启用'></td>";
		register += "</tr>";
		$("#providerCustomerListBody").append(register);
	}
	if (registers.length == 0) {
		var noTr = $("<tr></tr>");
		var td = "<td colspan='10' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#providerCustomerListBody").append(noTr);
		$("#providerCustomerPager").css("display", "none");
	} else {
		$("#providerCustomerPager").css("display", "inline-table");
	}

	// 更新操作
	$(".providerCustomerUpdateImg").click(function() {
		initProviderCustomerUpdateRecord($(this).attr("registerId"));
		$("#providerCustomerUpdate").modal("show");
	});

	// 删除操作
	$(".providerCustomerDeleteImg")
			.click(
					function() {
						var registerId = $(this).attr("registerId");
						$
								.confirm({
									title : '确认',
									content : '确定要删除吗?',
									confirm : function() {
										$
												.get(
														basePath
																+ "/provider/delete/"
																+ registerId,
														{},
														function(data, status) {
															if (data != null
																	&& data != "") {
																showTipsSucc(data);
																providerCustomerPagination
																		.updateSelfInput();
															}

														}, "text")
												.complete(
														function(jqXHR,
																textStatus) {
															var sessionstatus = jqXHR
																	.getResponseHeader("sessionstatus");
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

	// 启用操作
	$(".providerCustomerEnableImg")
			.click(
					function() {
						var registerId = $(this).attr("registerId");
						$
								.confirm({
									title : '确认',
									content : '确定要启用吗?',
									confirm : function() {
										$
												.post(
														basePath
																+ "/provider/enable/"
																+ registerId,
														{},
														function(data, status) {
															if (data != null
																	&& data != "") {
																showTipsSucc(data);
																providerCustomerPagination
																		.updateSelfInput();
															}
														}, "json")
												.complete(
														function(jqXHR,
																textStatus) {
															var sessionstatus = jqXHR
																	.getResponseHeader("sessionstatus");
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
