/**
 * 初始化内部员工列表
 */
function initInsideProvider(){
	$.post(basePath + "/process/insideProviderList", {
		currentPage : parseInt(currPage) + 1,
	}, function(data, status) {
		alert(data);
	}, "json");
}