var newDemanderPagination = null;
var processDemanderPagination = null;
var finishedDemanderPagination = null;
var wasteDemanderPagination = null;
/**
 * 初始化需求信息
 */
function initDemander() {
	initNewDemander();
	initProcessDemander();
	initFinishedDemander();
	initWasteDemander();
	initInsideProvider();
	$(".search").val("");
}