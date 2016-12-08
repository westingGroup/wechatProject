/**
 * 向下滑动时，进行翻页
 */
function pageMyMobileApplys(){
	if (isCanDown == 1)// 如果可以向下翻页
		$.post(basePath + "/provider/myMobileApplys", {
			currentPage : parseInt(currPage) + 1,
			dealBy:$("#providerId").val()
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
 * 添加记录信息
 */
function appendRecord(recordContent){
	var record = "<div class='container viewTaskCommonStyle' id='order"+recordContent.id+"'>";
	record += "<hr class='viewHr' />";
	record += "<div class='row serialNumber hidden"+recordContent.id+"'>";
	record += "<div class='col-md-1 col-xs-3 label'>流水号：</div>";
	record += "<div class='col-md-10 col-xs-7 viewContent'>"+recordContent.serviceOrderId+"</div>";
	record += "<div class='col-md-1 col-xs-2'>";
	record += "<img alt='折叠' class='hidden' src='"+basePath+"/assets/img/zhedie.png' id='zhedie"+recordContent.id+"'>";
	record += "</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden"+recordContent.id+"' />";
	record += "<div class='row categoryView hidden"+recordContent.id+"'>";
	record += "<div class='col-md-1 col-xs-3 label'>类别：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent'>"+recordContent.category+"</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden"+recordContent.id+"' />";
	record += "<div class='row serviceTypeView hidden"+recordContent.id+"'>";
	record += "<div class='col-md-1 col-xs-3 label'>服务类型：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent'>"+recordContent.serviceType+"</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden"+recordContent.id+"' />";
	record += "<div class='row statusView hidden"+recordContent.id+"'>";
	record += "<div class='col-md-1 col-xs-3 label'>状态：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent'>"+recordContent.status+"</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden"+recordContent.id+"' />";
	record += "<div class='row submitTimeView'>";
	record += "<div class='col-md-1 col-xs-3 label'>提交时间：</div>";
	record += "<div class='col-md-10 col-xs-7 viewContent zhedie'>"+recordContent.createDate+"</div>";
	record += "<div class='col-md-1 col-xs-2'>";
	record += "<img alt='展开' class='show' src='"+basePath+"/assets/img/zhankai.png' id='zhankai"+recordContent.id+"'>";
	record += "</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden"+recordContent.id+"' />";
	record += "<div class='row serviceDemandView hidden"+recordContent.id+"'>";
	record += "<div class='col-md-1 col-xs-3 label'>服务需求：</div>";
	record += "<div class='col-md-11 col-xs-9 viewContent'>"+recordContent.content+"</div>";
	record += "</div>";
	record += "<hr class='commonHr hidden"+recordContent.id+"'>";
	record += "<div class='row serviceEvaluate hidden"+recordContent.id+"'>";
	record += "<div class='col-md-1 col-xs-3 label'>客户反馈：</div>";
	record += "<div class='col-md-11 col-xs-9'>";
	record += "<div class='raty' id='raty"+recordContent.id+"' style='margin-right: 0px;'></div>";
	record += "</div>";
	record += "</div>";
	record += "</div>";
	
	$(".content").append(record);
	renderingList(recordContent.id, recordContent.evaluate, "provider");
}