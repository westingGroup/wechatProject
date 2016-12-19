<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/demander/newDemander.js"></script>
<div>
	<table class="searchTable table">
		<tbody>
			<tr>
				<td>流水号:</td>
				<td><input type="text" id="newDemanderSOI" class="search"/></td>
				<td>联系人:</td>
				<td><input type="text" id="newDemanderLN" class="search"/></td>
				<td>联系方式:</td>
				<td><input type="text" id="newDemanderLP" class="search"/></td>
				<td>类别:</td>
				<td><input type="text" id="newDemanderCG" class="search" /></td>
				<td>类型:</td>
				<td><input type="text" id="newDemanderST" class="search" /></td>
				<td style="text-align: right;">
					<button type="button" id="newDemanderBtn" class="btn btn-main">查询</button>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="resultTable table table-hover table-bordered">
		<thead>
			<tr>
				<th></th>
				<th>流水号</th>
				<th>产品类别</th>
				<th>服务类型</th>
				<th>需求状态</th>
				<th>提交日期</th>
				<th>内容</th>
				<th>联系人</th>
				<th>联系方式</th>
			</tr>
		</thead>
		<tbody id="newDemanderBody">
		</tbody>
	</table>
	<div class="pager" id="newDemanderPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="newDemanderPagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40"> <a
					href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a><select class="select"
					id="newDemanderPageSize" data-action="select">
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
				</select>
			</div>
		</div>
		<div class="minMaxResult">
			view <span id="newDemanderFirstResult"></span>-<span
				id="newDemanderMaxResult"></span> of <span
				id="newDemanderTotalRecords"></span>
		</div>
	</div>
	<div class="approval_info" id="newDemanderApproval">
		<input type="hidden" id="selectNewDemanderId" /><input type="hidden"
			id="newDemanderType" value="1" />
		<table class="table">
			<tr>
				<td style="width: 30%;"><textarea rows="3" cols="40"
						class="required" id="newDemanderRemark" label="备注"></textarea></td>
				<td style="width: 30%;"><select class="select required"
					id="newDemanderEngineer" label="工程师"><option value="">工程师</select><img
					class="btnImg" style="margin-left: 5px; cursor: pointer;"
					id="insideEngineerImg"
					src="<%=request.getContextPath()%>/assets/img/magnifier.png"></td>
				<td style="width: 20%;"><button class="btn approvalBtn"
						id="newDemanderApprovalBtn">
						<span class="btnText">转处理中</span> <img class="btnImg"
							style="margin-left: 55px !important;"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
				<td style="width: 20%;"><button class="btn rejectBtn"
						id="newDemanderRejectBtn">
						<span class="btnText">转废单</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
			</tr>
		</table>
	</div>
	<jsp:include page="insideProvider.jsp"></jsp:include>
</div>