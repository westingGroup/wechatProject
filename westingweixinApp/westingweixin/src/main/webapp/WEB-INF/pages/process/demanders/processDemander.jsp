<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/demander/processDemander.js"></script>
<div>
	<table class="searchTable table">
		<tbody>
			<tr>
				<td>流水号:</td>
				<td><input type="text" id="processDemanderSOI" class="search" /></td>
				<td>联系人:</td>
				<td><input type="text" id="processDemanderLN" class="search" /></td>
				<td>联系方式:</td>
				<td><input type="text" id="processDemanderLP" class="search" /></td>
			</tr>
			<tr>
				<td>类别:</td>
				<td><input type="text" id="processDemanderCG" class="search" /></td>
				<td>类型:</td>
				<td><input type="text" id="processDemanderST" class="search" /></td>
				<td colspan="2" style="text-align: center;">
					<button type="button" id="processDemanderBtn" class="btn button btn-main">查询</button>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="resultTable table table-hover table-bordered">
		<thead>
			<tr>
				<th><input type="checkbox" id="processDemanderAll"></th>
				<th>流水号</th>
				<th>产品类别</th>
				<th>服务类型</th>
				<th>需求状态</th>
				<th>提交日期</th>
				<th>内容</th>
				<th>联系人</th>
				<th>联系方式</th>
				<th>处理人</th>
			</tr>
		</thead>
		<tbody id="processDemanderBody">
		</tbody>
	</table>
	<div class="pager" id="processDemanderPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="processDemanderPagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40"> <a
					href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a><select class="select"
					id="processDemanderPageSize" data-action="select">
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
				</select>
			</div>
		</div>
		<div class="minMaxResult">
			view <span id="processDemanderFirstResult"></span>-<span
				id="processDemanderMaxResult"></span> of <span
				id="processDemanderTotalRecords"></span>
		</div>
	</div>
	<div class="approval_info" id="processDemanderApproval">
		<input type="hidden" id="processDemanderIds" /> <input type="hidden"
			id="processDemanderType" value="2" />
		<table class="table">
			<tr>
				<td style="width: 60%;"><textarea rows="3" cols="40"
						class="required" label="备注" id="processDemanderRemark"
						placeholder="备注"></textarea></td>
				<td style="width: 10%;"><button class="btn approvalBtn"
						id="processDemanderReturnBtn">
						<span class="btnText">转待分配</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
				<td style="width: 10%;"><button class="btn button approvalBtn"
						id="processDemanderApprovalBtn">
						<span class="btnText">转完成</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
				<td style="width: 10%;"><button class="btn button rejectBtn"
						id="processDemanderRejectBtn">
						<span class="btnText">转废单</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
			</tr>
		</table>
	</div>
</div>