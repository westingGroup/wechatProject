<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/registers/demanderRegisterApplylist.js"></script>
<div>
	<table class="searchTable table">
		<tbody>
			<tr>
				<td>联系人:</td>
				<td><input type="text" id="demanderApplyLN" class="search" /></td>
				<td>联系方式:</td>
				<td><input type="text" id="demanderApplyLP" class="search" /></td>
				<td style="text-align: right;">
					<button type="button" id="demanderApplyBtn" class="btn btn-main">查询</button>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="resultTable table table-hover table-bordered">
		<thead>
			<tr>
				<th><input type="checkbox" id="demanderApplyAll"></th>
				<th>序号</th>
				<th>联系人</th>
				<th>联系方式</th>
				<th>相关业务</th>
				<th>公司名称</th>
			</tr>
		</thead>
		<tbody id="demanderRegisterApplyListBody">
		</tbody>
	</table>
	<div class="pager" id="demanderApplyPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="demanderApplyPagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40"> <a
					href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a> <select class="select"
					id="demanderApplyPageSize" data-action="select">
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
				</select>
			</div>
		</div>
		<div class="minMaxResult">
			view <span id="demanderApplyFirstResult"></span>-<span
				id="demanderApplyMaxResult"></span> of <span
				id="demanderApplyTotalRecords"></span>
		</div>
	</div>
	<div class="approval_info" id="demanderApplyApproval">
		<input type="hidden" id="demanderApplyIds" />
		<table class="table">
			<tr>
				<td style="width: 60%;"><textarea rows="3" cols="40"
						id="demanderRemark"></textarea></td>
				<td style="width: 20%;"><button class="btn approvalBtn"
						id="demanderApplyApprovalBtn">
						<span class="btnText">批准</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
				<td style="width: 20%;"><button class="btn rejectBtn"
						id="demanderApplyRejectBtn">
						<span class="btnText">拒绝</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
			</tr>
		</table>
	</div>
</div>