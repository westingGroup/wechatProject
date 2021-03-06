<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/registers/providerRegisterApplylist.js"></script>
<div>
	<table class="searchTable table">
		<tbody>
			<tr>
				<td>姓名:</td>
				<td><input type="text" id="providerApplyLN" class="search" /></td>
				<td>电话:</td>
				<td><input type="text" id="providerApplyLP" class="search" /></td>
				<td style="text-align: right;">
					<button type="button" id="providerApplyBtn"
						class="btn button btn-main">查询</button>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="resultTable table table-hover table-bordered">
		<thead>
			<tr>
				<th><input type="checkbox" id="providerApplyAll"></th>
				<th>序号</th>
				<th>姓名</th>
				<th>电话</th>
				<th>出生年月</th>
				<th>擅长业务</th>
				<th>职称/资质</th>
				<th>公司名称</th>
			</tr>
		</thead>
		<tbody id="providerRegisterApplyListBody">
		</tbody>
	</table>
	<div class="pager" style="display: none !important;"
		id="providerApplyPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="providerApplyPagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40"> <a
					href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a><select class="select"
					id="providerApplyPageSize" data-action="select">
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
				</select>
			</div>
		</div>
		<div class="minMaxResult">
			view <span id="providerApplyFirstResult"></span>-<span
				id="providerApplyMaxResult"></span> of <span
				id="providerApplyTotalRecords"></span>
		</div>
	</div>
	<div class="approval_info" style="display: none !important;"
		id="providerApplyApproval">
		<input type="hidden" id="providerApplyIds" />
		<table class="table">
			<tr>
				<td style="width: 30%;"><textarea rows="3" cols="40"
						id="providerRemark" placeholder="备注"></textarea></td>
				<td style="width: 30%;"><select class="select required"
					id="providerType1" label="工程师"><option value="0"
							selected>外部</option>
						<option value="1">内部</option></td>
				<td style="width: 20%;"><button class="btn button approvalBtn"
						id="providerApplyApprovalBtn">
						<span class="btnText">批准</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
				<td style="width: 20%;"><button class="btn button rejectBtn"
						id="providerApplyRejectBtn">
						<span class="btnText">拒绝</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
			</tr>
		</table>
	</div>
</div>