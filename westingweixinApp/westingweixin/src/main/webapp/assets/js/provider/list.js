//判断滑动是否取到了数据，如果已经取到了数据，则返回true;否则返回false
var flag = true;
/**
 * 向下滑动时，进行翻页
 */
function pageMyMobileApplys() {
	// 如果已经在查询，则返回
	if (!flag)
		return;
	if (isCanDown == 1) {// 如果可以向下翻页
		flag = false;
		$.post(basePath + "/provider/myMobileApplys", {
			currentPage : parseInt(currPage) + 1,
			dealBy : $("#providerId").val()
		}, function(data, status) {
			flag = true;
			var records = data.records;
			currPage = data.currentPage;
			totalPage = data.totalPage;
			isCanDown = data.isCanDown;
			for (var i = 0; i < records.length; i++) {
				appendRecord(data.records[i]);
			}
		}, "json");
	}
}

/**
 * 添加记录信息
 */
function appendRecord(recordContent) {
	var record = "<div class='container viewTaskCommonStyle' id='order"
			+ recordContent.id + "'>";
	record += "<hr class='viewHr' />";
	record += "<div class='row serialNumber hidden" + recordContent.id + "'>";
	record += "<div class='col-md-1 col-xs-3 label'>流水号：</div>";
	record += "<div class='col-md-10 col-xs-7 viewContent'>"
			+ recordContent.serviceOrderId + "</div>";
	record += "<div class='col-md-1 col-xs-2'>";
	record += "<img alt='折叠' class='hidden' src='" + basePath
			+ "/assets/img/zhedie.png' id='zhedie" + recordContent.id + "'>";
	record += "</div>";
	record += "</div>";
	record += "<div class='row categoryView hidden" + recordContent.id + "'>";
	record += "<div class='col-md-1 col-xs-3 label'>类别：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent'>"
			+ recordContent.category + "</div>";
	record += "</div>";
	record += "<div class='row serviceTypeView hidden" + recordContent.id
			+ "'>";
	record += "<div class='col-md-1 col-xs-3 label'>服务类型：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent'>"
			+ recordContent.serviceType + "</div>";
	record += "</div>";
	record += "<div class='row statusView hidden" + recordContent.id + "'>";
	record += "<div class='col-md-1 col-xs-3 label'>状态：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent' id='zt"
			+ recordContent.id + "'>" + recordContent.status + "</div>";
	record += "</div>";
	record += "<div class='row submitTimeView'>";
	record += "<div class='col-md-1 col-xs-3 label'>提交时间：</div>";
	record += "<div class='col-md-10 col-xs-7 viewContent zhedie'>"
			+ recordContent.createDate + "&nbsp;<span id='status"
			+ recordContent.id + "' status=" + recordContent.status + ">"
			+ recordContent.status + "</span></div>";
	record += "<div class='col-md-1 col-xs-2'>";
	record += "<img alt='展开' class='show' src='" + basePath
			+ "/assets/img/zhankai.png' id='zhankai" + recordContent.id + "'>";
	record += "</div>";
	record += "</div>";
	record += "<div class='row serviceDemandView hidden" + recordContent.id
			+ "'>";
	record += "<div class='col-md-1 col-xs-3 label'>服务需求：</div>";
	record += "<div id='content_" + recordContent.id
			+ "' class='col-md-11 col-xs-9 viewContent'>"
			+ recordContent.content + "</div>";
	record += "</div>";

	record += "<div class='row serviceDemandView hidden" + recordContent.id
			+ "'>";
	record += "<div class='col-md-1 col-xs-3 label'>价格：</div>";
	record += "<div id='content_" + recordContent.id + "'";
	record += "class='col-md-11 col-xs-9 viewContent'>" + recordContent.price
			+ "</div>";
	record += "</div>";

	record += "<div class='row serviceDemandView viewContentAll hidden"
			+ recordContent.id + "' id='contentAllDiv_" + recordContent.id
			+ "'>";
	record += "<div id='contentAll_" + recordContent.id + "' class='hidden'>"
			+ recordContent.content + "</div>";
	record += "</div>";
	record += "<div class='row serviceEvaluate hidden" + recordContent.id
			+ "'>";
	record += "<div class='col-md-1 col-xs-3 label'>客户反馈：</div>";
	record += "<div class='col-md-10 col-xs-4' style='padding-left:0px;'>";
	record += "<div class='raty viewContent' id='raty" + recordContent.id
			+ "' style='margin-right: 0px;margin-top: -5px;'></div>";
	record += "<div class='col-md-1 col-xs-5'><input type='hidden' id='id"
			+ recordContent.id + "' value='+"
	recordContent.id
	"+' /> <button class='btn button btnApply' id='applybut" + recordContent.id
			+ "'>完成申请</button></div>";
	record += "</div>";
	record += "</div>";

	record += "<div class='row serviceEvaluate hidden" + recordContent.id
			+ "' id='evaluateContentDiv" + recordContent.id + "'>";
	record += "<textarea id='evaluateContent" + recordContent.id
			+ "' class='textarea required maxlength' rows='2'";
	record += "	placeholder='请输入评价内容' label='评价内容' maxlength='100'>"
			+ recordContent.evaluateContent + "</textarea>";
	record += "</div>";

	record += "</div>";

	$(".content").append(record);
	renderingList(recordContent.id, recordContent.evaluate, "provider",
			recordContent.status, recordContent.evaluateContent,
			recordContent.dealBy);
}