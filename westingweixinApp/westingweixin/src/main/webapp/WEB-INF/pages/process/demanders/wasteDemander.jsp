<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/demander/wasteDemander.js"></script>
<div>
	<table class="searchTable table">
		<tbody>
			<tr>
				<td>流水号:</td>
				<td><input type="text" id="wasteDemanderSOI" class="search" /></td>
				<td>联系人:</td>
				<td><input type="text" id="wasteDemanderLN" class="search" /></td>
				<td>联系方式:</td>
				<td><input type="text" id="wasteDemanderLP" class="search" /></td>
			</tr>
			<tr>
				<td>类别:</td>
				<td><input type="text" id="wasteDemanderCG" class="search" /></td>
				<td>类型:</td>
				<td><input type="text" id="wasteDemanderST" class="search" /></td>
				<td colspan="2" style="text-align: center;">
					<button type="button" id="wasteDemanderBtn" class="btn button btn-main">查询</button>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="resultTable table table-hover table-bordered">
		<thead>
			<tr>
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
		<tbody id="wasteDemanderBody">
		</tbody>
	</table>
	<div class="pager" id="wasteDemanderPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="wasteDemanderPagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40"> <a
					href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a><select class="select"
					id="wasteDemanderPageSize" data-action="select">
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
				</select>
			</div>
		</div>
		<div class="minMaxResult">
			view <span id="wasteDemanderFirstResult"></span>-<span
				id="wasteDemanderMaxResult"></span> of <span
				id="wasteDemanderTotalRecords"></span>
		</div>
	</div>
	<input type="hidden" id="wasteDemanderType" value="10" />
</div>