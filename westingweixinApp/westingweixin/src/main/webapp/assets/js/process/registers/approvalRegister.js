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
			clearDemanderApply();//清除申请的参数
		} else if (type == "provider") {// 如果为服务提供方，刷新新注册服务提供方和服务提供方列表
			providerApplyPagination.updateSelfInput();
			providerCustomerPagination.updateSelfInput();
			clearProviderApply();//清除申请的参数
		}
	}, "text");
}