/**
 * 向下滑动时
 */
function pageMyDemanders() {
	if (isCanDown == 1)// 如果可以向下翻页
		$.post(basePath + "/demander/mydemanders", {
			currentPage : parseInt(currPage) + 1,
			createBy : $("#createBy").val()
		}, function(data, status) {
			var records = data.records;
			currPage = data.currentPage;
			totalPage = data.totalPage;
			isCanDown = data.isCanDown;
			for (var i = 0; i < records.length; i++) {
				appendRecord(data.records[i]);
			}
		}, "json");
}

/**
 * 添加一条服务单
 * 
 * @param recordContent
 */
function appendRecord(recordContent) {
	var record = "<div class='container viewTaskCommonStyle' id='order"
			+ recordContent.id + "'>";
	record += "<hr class='viewHr' />";
	record += "<div class='row serialNumberView hidden" + recordContent.id
			+ "'>";
	record += "<div class='col-md-1 col-xs-3 label'>流水号：</div>";
	record += "<div class='col-md-10 col-xs-7 viewContent'>"
			+ recordContent.serviceOrderId + "</div>";
	record += "<div class='col-md-1 col-xs-2'>";
	record += "<img alt='折叠' class='hidden' src='" + basePath
			+ "/assets/img/zhedie.png' id='zhedie" + recordContent.id + "'>";
	record += "</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden" + recordContent.id + "' />";
	record += "<div class='row categoryView hidden" + recordContent.id + "'>";
	record += "<div class='col-md-1 col-xs-3 label'>类别：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent'>"
			+ recordContent.category + "</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden" + recordContent.id + "' />";
	record += "<div class='row serviceTypeView hidden" + recordContent.id
			+ "'>";
	record += "<div class='col-md-1 col-xs-3 label'>服务类型：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent'>"
			+ recordContent.serviceType + "</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden" + recordContent.id + "' />";
	record += "<div class='row statusView hidden" + recordContent.id + "'>";
	record += "<div class='col-md-1 col-xs-3 label'>状态：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent'>"
			+ recordContent.status + "</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden" + recordContent.id + "' />";
	record += "<div class='row submitTimeView'>";
	record += "<div class='col-md-1 col-xs-3 label'>提交时间：</div>";
	record += "<div class='col-md-10 col-xs-7 viewContent zhedie'>"
			+ recordContent.createDate + "</div>";
	record += "<div class='col-md-1 col-xs-2'>";
	record += "<img alt='展开' class='show' src='" + basePath
			+ "/assets/img/zhankai.png' id='zhankai" + recordContent.id + "'>";
	record += "</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden" + recordContent.id + "' />";
	record += "<div class='row serviceDemandView hidden" + recordContent.id
			+ "'>";
	record += "<div class='col-md-1 col-xs-3 label'>服务需求：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent'>"
			+ recordContent.content + "</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden" + recordContent.id + "'>";
	record += "<div class='row serviceEvaluate hidden" + recordContent.id
			+ "'>";
	record += "<div class='col-md-2 col-xs-5'>";
	record += "<div class='raty' id='raty" + recordContent.id
			+ "' style='margin-right: 0px;'></div>";
	record += "</div>";
	record += "<div class='col-md-10 col-xs-7' style='text-align: left;'>";
	record += "<button class='btn' id='but" + recordContent.id
			+ "'>服务评价</button>";
	record += "<input type='hidden' id='id" + recordContent.id + "' value="
			+ recordContent.id + " /> <input type='hidden' id='evaluate"
			+ recordContent.id + "' value='" + recordContent.evaluate + "' />";
	record += "</div>";
	record += "</div>";
	record += "</div>";

	$(".content").append(record);
	renderingList(recordContent.id, recordContent.evaluate, "demander", recordContent.status);
}
