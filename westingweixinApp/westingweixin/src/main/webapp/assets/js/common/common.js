/**
 * 转换tab页
 */
function switchTab(tab) {
	// 修改tab标题
	var tabArr = $(".add_head").children("a");
	for (var i = 0; i < tabArr.length; i++) {
		$(tabArr.get(i)).removeClass("current");
	}
	$("#" + tab + "Tab").addClass("current");

	// 修改tab查询信息
	var searchArr = $(".tab_search");
	for (var i = 0; i < searchArr.length; i++) {
		$(searchArr.get(i)).addClass("tab_hidden");
	}
	$("#" + tab).removeClass("tab_hidden");
}

/**
 * 显示提示信息
 * 
 * @param divId显示提示信息的id
 * @param divAddClass增加的class
 * @param divRemoveClass移除的class
 * @param textId显示提示信息的id
 * @param tipContent显示的提示信息
 */
function showTips(divId, divAddClass, divRemoveClass, textId, tipContent) {
	if ($("#" + divId).length > 0) {
		$("#" + textId).text(tipContent);
		$("#" + divId).removeClass(divRemoveClass).addClass(divAddClass).show();
		setTimeout("closeTips('" + divId + "')", 3000);
	} else {
		alert(tipContent);
	}
}

/**
 * 关闭提示信息
 * 
 * @param eleId
 */
function closeTips(eleId) {
	$("#" + eleId).hide();
	window.close();
}

/**
 * 成功时显示页面
 * 
 * @param comm
 */
function showTipsSucc(comm) {
	showTips("showTips", "alert-success", "alert-danger", "strongId", comm);
}
/**
 * 失败时显示页面
 * 
 * @param comm
 */
function showTipsError(comm) {
	showTips("showTips", "alert-danger", "alert-success", "strongId", comm);
}

/**
 * 校验
 */
function validate(form) {
	var flag = true;
	// 校验字符输入值
	$("input[type=text]", $("#" + form)[0]).each(function() {
		// 校验必须项
		if ($("#" + this.id).hasClass("required")) {
			flag = validateRequired(this.id, "text");
			if(!flag)
				return flag;
		}
		// 校验手机号
		if ($("#" + this.id).hasClass("phone")) {
			flag = validatePhone(this.id);
			if(!flag)
				return flag;
		}
		// 校验最大长度
		if ($("#" + this.id).hasClass("maxlength")) {
			flag = validateMaxLength(this.id);
			if(!flag)
				return flag;
		}
	});
	if (!flag)
		return flag;

	// 校验选择框
	$("select", $("#" + form)[0]).each(function() {
		if ($(this).hasClass("required")){
			flag = validateRequired(this.id, "select");
			if(!flag)
				return flag;
		}
			
	});
	if (!flag)
		return flag;

	// 校验文本域
	$("textarea", $("#" + form)[0]).each(function() {
		if ($("#" + this.id).hasClass("required")){
			flag = validateRequired(this.id, "textarea");
			if(!flag)
				return flag;
		}
	});

	return flag;
}

/**
 * 校验必须项
 */
function validateRequired(id, type) {
	if ($("#" + id).val() == null || $("#" + id).val() == "") {
		if (type == "text" || type == "textarea")
			showTipsError("请输入" + $("#" + id).attr("label") + "的值");
		else if (type == "select")
			showTipsError("请选择" + $("#" + id).attr("label") + "的值");
		validateFail(id);
		return false;
	}
	validateSuccess(id);
	return true;
}

/**
 * 校验手机号
 * 
 * @param id
 * @returns {Boolean}
 */
function validatePhone(id) {
	var mobile = /^1[34578]\d{9}$/;
	var phone = /^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	if (!(mobile.test($("#" + id).val()) || phone.test($("#" + id).val()))) {
		showTipsError($("#" + id).attr("label") + "格式不正确");
		validateFail(id);
		return false;
	}
	validateSuccess(id);
	return true;
}

/**
 * 校验最大长度
 * 
 * @param id
 * @returns {Boolean}
 */
function validateMaxLength(id) {
	if ($("#" + id).val().length > $("#" + id).attr("maxlength")) {
		$("#" + id).val(
				$("#" + id).val().substr(0, $("#" + id).attr("maxlength") - 1));
		return false;
	}
	return true;
}

/**
 * 校验成功
 */
function validateSuccess(id) {
	$("#" + id).removeClass("requireStyle").addClass("noRequireStyle");
}

/**
 * 校验失败
 */
function validateFail(id) {
	$("#" + id).removeClass("noRequireStyle").addClass("requireStyle");// 设置红色边框
	$("#" + id).focus();// 聚焦
}