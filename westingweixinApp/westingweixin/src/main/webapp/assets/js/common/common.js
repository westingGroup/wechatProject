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
 * 校验
 */
function validate(form) {
	var flag = true;
	// 校验字符输入值
	$("input[type=text]", $("#" + form)[0]).each(function() {
		// 校验必须项
		if ($("#" + this.id).hasClass("required")) {
			if (!validateRequired(this.id)) {
				flag = false;
				return flag;
			}
		}
		// 校验手机号
		if ($("#" + this.id).hasClass("phone")) {
			if (!validatePhone(this.id)) {
				flag = false;
				return flag;
			}
		}
		// 校验最大长度
		if ($("#" + this.id).hasClass("maxlength")) {
			if (!validateMaxLength(this.id)) {
				flag = false;
				return flag;
			}
		}
	});
	if (!flag)
		return flag;

	// 校验文本域
	$("textarea", $("#" + form)[0]).each(function() {
		if ($("#" + this.id).hasClass("required"))
			if (!validateRequired(this.id)) {
				flag = false;
				return flag;
			}
	});

	return flag;
}

/**
 * 校验必须项
 */
function validateRequired(id) {
	if ($("#" + id).val() == null || $("#" + id).val() == "") {
		alert("请输入" + $("#" + id).attr("label") + "的值");
		return false;
	}
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
		alert($("#" + id).attr("label") + "格式不正确");
		return false;
	}
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
				$("#" + id).val().substr(0, $("#" + id).attr("maxValue") - 1));
		return false;
	}
	return true;
}