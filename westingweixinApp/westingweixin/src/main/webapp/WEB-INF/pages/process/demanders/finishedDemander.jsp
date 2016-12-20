<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/demander/finishedDemander.js"></script>
<div>
	<table class="searchTable table">
		<tbody>
			<tr>
				<td>流水号:</td>
				<td><input type="text" id="finishedDemanderSOI" class="search" /></td>
				<td>联系人:</td>
				<td><input type="text" id="finishedDemanderLN" class="search" /></td>
				<td>联系方式:</td>
				<td><input type="text" id="finishedDemanderLP" class="search" /></td>
			</tr>
			<tr>
				<td>类别:</td>
				<td><input type="text" id="finishedDemanderCG" class="search" /></td>
				<td>类型:</td>
				<td><input type="text" id="finishedDemanderST" class="search" /></td>
				<td>评分:</td>
				<td><input type="text" id="finishedDemanderEV" class="search" /></td>
			</tr>
			<tr>
				<td colspan="4"></td>
				<td colspan="2" style="text-align: center;">
					<button type="button" id="finishedDemanderBtn" class="btn btn-main">查询</button>
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
				<th>处理人</th>
				<th>评分</th>
			</tr>
		</thead>
		<tbody id="finishedDemanderBody">
		</tbody>
	</table>
	<div class="pager" id="finishedDemanderPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="finishedDemanderPagination">
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
			view <span id="finishedDemanderFirstResult"></span>-<span
				id="finishedDemanderMaxResult"></span> of <span
				id="finishedDemanderTotalRecords"></span>
		</div>
	</div>
	<input type="hidden" id="finishedDemanderType" value="9" />
</div>