var newDemanderPagination = null;
var processDemanderPagination = null;
var finishedDemanderPagination = null;
var wasteDemanderPagination = null;
/**
 * 初始化需求信息
 */
function initDemander() {
	initNewDemanderList(1, 10);
	initProcessDemanderList(1, 10);
	initFinishedDemanderList(1, 10);
	initWasteDemanderList(1, 10);
}