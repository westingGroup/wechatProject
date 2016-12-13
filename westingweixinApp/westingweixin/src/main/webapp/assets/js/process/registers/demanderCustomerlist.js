/**
 * 初始化需求方列表
 */
function initDemanderCustomer() {
	// 点击查询按钮，进行查询操作
	$("#demanderCustomerBtn").click(function() {
		initDemanderCustomerList(1);
	});
	initDemanderCustomerList(1);
}
/**
 * 初始化需求方列表
 */
function initDemanderCustomerList(currPage) {
	demanderCustomerPagination = $('#demanderCustomerPagination').jqPagination(
			{
				link_type : "self",
				link_string : basePath + "/process/listByPage",
				callback_fun : "getDemanderCustomerList",
				current_page : currPage, // 设置当前页 默认为1
				paraData : {
					type : "demander",// 服务需求方
					status : 11,
					// 已经注册通过的
					linkname : $("#demanderCustomerLN").val(),// 联系人
					linkphone : $("#demanderCustomerLP").val()
				// 联系方式
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
function appendDemanderCustomer(registers, firstRegisterIndex) {
	$("#demanderCustomerListBody").empty();
	for (var i = 0; i < registers.length; i++) {
		var register = "<tr>";
		register += "<td>" + (firstRegisterIndex + i) + "</td>";
		register += "<td>" + registers[i].linkname + "</td>";
		register += "<td>" + registers[i].linkphone + "</td>";
		register += "<td>" + registers[i].business + "</td>";
		register += "<td>" + registers[i].company + "</td>";
		register += "<td><img alt='修改' src='"
				+ basePath
				+ "/assets/img/edit.png' width='16px' height='16px' registerId="
				+ registers[i].id
				+ " class='imgUpdate demanderCustomerUpdateImg'>&nbsp;&nbsp;<img alt='删除' src='"
				+ basePath
				+ "/assets/img/delete.png' width='16px' height='16px' registerId="
				+ registers[i].id
				+ " class='imgDelete demanderCustomerDeleteImg' /></td>";
		register += "</tr>";
		$("#demanderCustomerListBody").append(register);
	}
	$("#demanderCustomerPager").show();
	if (registers.length == 0) {
		var noTr = $("<tr></tr>");
		var td = "<td colspan='6' style='text-align:center;'>暂无符合条件的记录</td>";
		noTr.html(td);
		$("#demanderCustomerListBody").append(noTr);
		$("#demanderCustomerPager").hide();
	}

	// 更新操作
	$(".demanderCustomerUpdateImg").click(function() {
		alert("更新操作=================================");
	});

	// 删除操作
	$(".demanderCustomerDeleteImg").click(
			function() {
				var registerId = $(this).attr("registerId");
				$.confirm({
					title : '确认',
					content : '确定要删除吗?',
					confirm : function() {
						$.get(basePath + "/demander/delete/" + registerId, {},
								function(data, status) {
									showTipsSucc(data);
									demanderCustomerPagination
											.updateSelfInput();
								}, "text");
					},
					cancel : function() {
					}
				});
			});
}