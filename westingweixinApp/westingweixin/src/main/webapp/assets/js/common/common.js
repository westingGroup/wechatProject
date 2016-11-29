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