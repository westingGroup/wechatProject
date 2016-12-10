var demanderApplyPagination = null;
var demanderCustomerPagination = null;
var providerApplyPagination = null;
var providerCustomerPagination = null;
var insideCustomerPagination = null;
/**
 * 初始化注册列表信息
 */
function initRegisters() {
	initDemanderRegisterApplyList(1, 10);
	initProviderRegisterApplyList(1, 10);
	initDemanderCustomerList(1, 10);
	initProviderCustomerList(1, 10);
	initInsideCustomerList(1,10);
}

/**
 * 检测是否全选(如果全选，将其他的都选中，并将值填充到选中字符串中)---用于选择
 * 
 * @param applyAllId是否全选的id
 * @param applyIdName//所有的选中的id
 * @param applyIdsId
 */
function checkAllApplyForSelect(applyAllId, applyIdName, applyIdsId) {
	var checked = $("#" + applyAllId).prop("checked");
	$("input[name=" + applyIdName + "]").each(function() {
		if (checked)// 如果全选选中，则将其他的都选中
			$(this).prop("checked", true);
		else
			// 如果全选去除掉，则将其他的都不选中
			$(this).prop("checked", false);
		// 根据选中或者不选中，将值添加到选中列表中或者从选中列表中删除
		checkApplyForSelect(1, $(this).val(), checked, applyIdsId);
	});
}

/**
 * 检测是否存在，并根据是否存在将值添加到选中列表中或者从选中列表中删除---用于选择 type ==
 * 1,只是用来判断，type==2：判断完成后，并判断是否全选
 * 
 */
function checkApplyForSelect(type, applyIdValue, checked, applyIdsId,
		applyIdName, applyAllId) {
	var applyIdArr = $("#" + applyIdsId).val().split(",");
	if (applyIdArr == null || applyIdArr == "")
		applyIdArr = new Array();
	var flag = checkApplyInArrForSelect(applyIdValue, applyIdArr);
	if (!flag && checked)
		applyIdArr.push(applyIdValue);
	if (flag && !checked)
		for (var i = 0; i < applyIdArr.length; i++)
			if (applyIdArr[i] == applyIdValue)
				applyIdArr.splice(i, 1);
	$("#" + applyIdsId).val(applyIdArr.toString());
	if (type == 2)// 如果为从页面中选择，则判断是否全选
		checkAllInStrForJudge(applyIdName, applyIdsId, applyAllId);
}

/**
 * 检测是否存在于数组中---用于选择
 * 
 * @param applyId
 * @param applyIdArr
 */
function checkApplyInArrForSelect(applyIdValue, applyIdArr) {
	var flag = false;
	for (var i = 0; i < applyIdArr.length; i++) {
		if (applyIdArr[i] == applyIdValue) {
			flag = true;
			break;
		}
	}
	return flag;
}

/**
 * 检测当前页的所有的申请id是否存在于字符串中 用于判断
 * 
 * @param demanderApplyId
 */
function checkAllInStrForJudge(applyIdName, applyIdsId, applyAllId) {
	var flag = true;
	if ($("input[name=" + applyIdName + "]").length != 0)// 如果多余一条记录，判断是否可以多选
		$("input[name=" + applyIdName + "]").each(function() {
			if (!checkApplyInStrForJudge($(this).val(), applyIdsId)) {// 如果当前值没有在字符串中
				flag = false;
				return flag;
			}
		});
	else
		// 如果没有一条记录，则不能全选
		flag = false;
	$("#" + applyAllId).prop("checked", flag);
}

/**
 * 检测是否存在于字符串中 用于判断
 */
function checkApplyInStrForJudge(applyIdValue, applyIdsId) {
	var applyIdArr = $("#" + applyIdsId).val().split(",");
	if (applyIdArr == null || applyIdArr == "")
		applyIdArr = new Array();
	var flag = false;
	for (var i = 0; i < applyIdArr.length; i++) {
		if (applyIdArr[i] == applyIdValue) {
			flag = true;
			break;
		}
	}
	return flag;
}

/**
 * 审批申请
 */
function approvalDemanderApply(type, dealType, demanderIds, remark,
		applyPagination) {
	var approvalDemanderIds = $("#" + demanderIds).val();
	var approvalRemark = $("#" + remark).val();
	if (approvalDemanderIds == null || approvalDemanderIds == "") {
		if (type == "demander")// 需求方
			showTipsSucc("请选择要审批的服务需求方");
		else
			showTipsSucc("请选择要审批的服务提供商");
		return false;
	}
	if (dealType == 10)// 如果被拒绝，必须填写拒绝原因
		if (approvalRemark == null || approvalRemark == "") {
			showTipsSucc("请输入备注信息");
			return false;
		}
	$.post(basePath + "/process/dealRegister", {
		type : type,
		demanderIds : approvalDemanderIds,
		remark : approvalRemark,
		dealType : dealType
	}, function(data, status) {
		$("#" + demanderIds).val("");
		showTipsSucc(data);
		if (type == "demander") {// 如果为服务需求方,刷新新注册服务需求方和服务需求方列表
			demanderApplyPagination.updateSelfInput();// 刷新新注册服务需求方
			demanderCustomerPagination.updateSelfInput();// 刷新服务需求方列表
		} else if (type == "provider") {// 如果为服务提供方，刷新新注册服务提供方和服务提供方列表
			providerApplyPagination.updateSelfInput();
			providerCustomerPagination.updateSelfInput();
		}
	}, "text");
}