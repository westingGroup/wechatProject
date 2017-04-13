var newDemanderPagination = null;
var processDemanderPagination = null;
var applyDemanderPagination = null;
var finishedDemanderPagination = null;
var wasteDemanderPagination = null;
/**
 * 初始化需求信息
 */
function initDemander() {
	initNewDemander();
	initProcessDemander();
	initApplyDemander();
	initFinishedDemander();
	initWasteDemander();
	initInsideProvider();
	$(".search").val("");
}