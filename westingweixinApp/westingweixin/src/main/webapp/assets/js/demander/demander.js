/**
 * 向下滑动时
 */
function pageMyDemanders() {
	if (isCanDown == 1)//如果可以向下翻页
		$.post(basePath + "/demander/mydemanders", {
			currentPage : parseInt(currPage)+1
		}, function(data, status) {
			var record;
			for(var i = 0; i < data.records.length; i++){
//				record = "<div class='container viewTaskCommonStyle' id="order${status.index}">";
			}
		}, "json");
}