<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
				<td>联系电话:</td>
				<td><input type="text" id="finishedDemanderLP" class="search" /></td>
			</tr>
			<tr>
				<td>产品类别:</td>
				<td><sf:select id="finishedDemanderCG" path="categoryType"
						cssClass="select search" label="服务类型">
						<sf:option value="" label="请选择类别" />
						<sf:options items="${categoryType}" itemLabel="info"
							itemValue="info" />
					</sf:select></td>
				<td>服务类型:</td>
				<td><sf:select id="finishedDemanderST" path="serviceType"
						cssClass="select search" label="服务类型">
						<sf:option value="" label="请选择服务类型" />
						<sf:options items="${serviceType}" itemLabel="info"
							itemValue="info" />
					</sf:select></td>
				<td>评分:</td>
				<td><select class="select search" id="finishedDemanderEV"
					label="评分"><option value="">请选择评分</option>
						<option value="1">1颗星</option>
						<option value="2">2颗星</option>
						<option value="3">3颗星</option>
						<option value="4">4颗星</option>
						<option value="5">5颗星</option></select></td>
			</tr>
			<tr>
				<td colspan="4"></td>
				<td colspan="2" style="text-align: center; padding-left: 20px;">
					<button type="button" id="finishedDemanderBtn"
						class="btn button btn-main">查询</button>
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
				<th>服务要求</th>
				<th>价钱</th>
				<th>完成日期</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>处理人</th>
				<th>评分</th>
			</tr>
		</thead>
		<tbody id="finishedDemanderBody">
		</tbody>
	</table>
	<div class="pager" style="display: none !important;"
		id="finishedDemanderPager">
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
	<input type="hidden" id="finishedDemanderType" value="90" />
</div>