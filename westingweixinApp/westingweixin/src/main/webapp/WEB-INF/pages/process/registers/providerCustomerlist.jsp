<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/registers/providerCustomerlist.js"></script>
<div>
	<table class="searchTable table">
		<tbody>
			<tr>
				<td>姓名:</td>
				<td><input type="text" id="providerCustomerLN" class="search" /></td>
				<td>电话:</td>
				<td><input type="text" id="providerCustomerLP" class="search" /></td>
				<td style="text-align: right;">
					<button type="button" id="providerCustomerBtn" class="btn button btn-main">查询</button>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="resultTable table table-hover table-bordered">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>电话</th>
				<th>出生年月</th>
				<th>擅长业务</th>
				<th>职称/资质</th>
				<th>公司名称</th>
				<th>状态</th>
				<th>类型</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="providerCustomerListBody">
		</tbody>
	</table>
	<div class="pager" style="display:none !important;" id="providerCustomerPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="providerCustomerPagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40"> <a
					href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a><select class="select"
					id="providerCustomerPageSize" data-action="select">
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
				</select>
			</div>
		</div>
		<div class="minMaxResult">
			view <span id="providerCustomerFirstResult"></span>-<span
				id="providerCustomerMaxResult"></span> of <span
				id="providerCustomerTotalRecords"></span>
		</div>
	</div>
	<jsp:include page="providerCustomerUpdate.jsp"></jsp:include>
</div>