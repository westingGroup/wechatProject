/**
 * 初始化注册列表信息
 */
function initRegisters() {
	initDemanderRegisterApplyList(1, 10);
	initProviderRegisterApplyList(1, 10);
	initDemanderCustomerList(1, 10);
	initProviderCustomerList(1, 10);
}

/**
 * 检测是否存在
 * 
 */
function checkApply(applyId, checked, applyIdInput) {
	var applyIdArr = $("#" + applyIdInput).val().split(",");
	if(applyIdArr == null || applyIdArr == "")
		applyIdArr = new Array();
	var flag = false;
	for (var i = 0; i < applyIdArr.length; i++) {
		if (applyIdArr[i] == applyId) {
			flag = true;
			break;
		}
	}
	if (!flag && checked)
		applyIdArr.push(applyId);
	if (flag && !checked)
		for (var i = 0; i < applyIdArr.length; i++)
			if (applyIdArr[i] == applyId)
				applyIdArr.splice(i, 1);
	$("#" + applyIdInput).val(applyIdArr.toString());
}

/**
 * 审批申请
 */
function approvalDemanderApply(type, dealType, demanderIds, remark) {
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
		alert(data);
	}, "text");
}