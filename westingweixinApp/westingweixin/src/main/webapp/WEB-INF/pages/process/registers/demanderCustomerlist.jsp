<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/registers/demanderCustomerlist.js"></script>
<div>
	<table class="searchTable table table-hover table-bordered">
		<thead>
			<tr>
				<th>序号</th>
				<th>联系人</th>
				<th>联系方式</th>
				<th>相关业务</th>
				<th>公司名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="demanderCustomerListBody">
		</tbody>
	</table>
	<div class="pager" id="demanderCustomerPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="demanderCustomerPagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40"> <a
					href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a><select class="select"
					id="demanderCustomerPageSize" data-action="select">
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
				</select>
			</div>
		</div>
		<div class="minMaxResult">
			view <span id="demanderCustomerFirstResult"></span>-<span
				id="demanderCustomerMaxResult"></span> of <span
				id="demanderCustomerTotalRecords"></span>
		</div>
	</div>
</div>