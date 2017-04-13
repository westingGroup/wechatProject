<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
				<td>联系电话:</td>
				<td><input type="text" id="wasteDemanderLP" class="search" /></td>
			</tr>
			<tr>
				<td>产品类别:</td>
				<td><sf:select id="wasteDemanderCG" path="categoryType"
						cssClass="select search" label="服务类型">
						<sf:option value="" label="请选择类别" />
						<sf:options items="${categoryType}" itemLabel="info"
							itemValue="info" />
					</sf:select></td>
				<td>服务类型:</td>
				<td><sf:select id="wasteDemanderST" path="serviceType"
						cssClass="select search" label="服务类型">
						<sf:option value="" label="请选择服务类型" />
						<sf:options items="${serviceType}" itemLabel="info"
							itemValue="info" />
					</sf:select></td>
				<td colspan="2" style="text-align: center; padding-left: 20px;">
					<button type="button" id="wasteDemanderBtn"
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
				<th>联系人</th>
				<th>联系电话</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody id="wasteDemanderBody">
		</tbody>
	</table>
	<div class="pager" style="display: none !important;"
		id="wasteDemanderPager">
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
	<input type="hidden" id="wasteDemanderType" value="100" />
</div>