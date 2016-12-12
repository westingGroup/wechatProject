<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/registers/insideCustomerlist.js"></script>
<div>
	<table class="searchTable table">
		<tbody>
			<tr>
				<td>联系人:</td>
				<td><input type="text" id="insideCustomerLN" class="search" /></td>
				<td>联系方式:</td>
				<td><input type="text" id="insideCustomerLP" class="search" /></td>
				<td style="text-align: right;">
					<button type="button" id="insideCustomerBtn" class="btn btn-main">查询</button>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="resultTable table table-hover table-bordered">
		<thead>
			<tr>
				<th>序号</th>
				<th>联系人</th>
				<th>联系方式</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="insideCustomerListBody">
		</tbody>
	</table>
	<div class="pager" id="insideCustomerPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="insideCustomerPagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40"> <a
					href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a><select class="select"
					id="insideApplyPageSize" data-action="select">
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
				</select>
			</div>
		</div>
		<div class="minMaxResult">
			view <span id="insideCustomerFirstResult"></span>-<span
				id="insideCustomerMaxResult"></span> of <span
				id="insideCustomerTotalRecords"></span>
		</div>
	</div>
	<div class="approval_info" style="text-align: right;">
		<button class="btn ">新增</button>
	</div>
</div>
