<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/demander/applyDemander.js"></script>
<div>
	<table class="searchTable table">
		<tbody>
			<tr>
				<td>流水号:</td>
				<td><input type="text" id="applyDemanderSOI" class="search" /></td>
				<td>联系人:</td>
				<td><input type="text" id="applyDemanderLN" class="search" /></td>
				<td>联系电话:</td>
				<td><input type="text" id="applyDemanderLP" class="search" /></td>
			</tr>
			<tr>
				<td>产品类别:</td>
				<td><sf:select id="applyDemanderCG" path="categoryType"
						cssClass="select search" label="服务类型">
						<sf:option value="" label="请选择类别" />
						<sf:options items="${categoryType}" itemLabel="info"
							itemValue="info" />
					</sf:select></td>
				<td>服务类型:</td>
				<td><sf:select id="applyDemanderST" path="serviceType"
						cssClass="select search" label="服务类型">
						<sf:option value="" label="请选择服务类型" />
						<sf:options items="${serviceType}" itemLabel="info"
							itemValue="info" />
					</sf:select></td>
				<td colspan="2" style="text-align: center; padding-left: 20px;">
					<button type="button" id="applyDemanderBtn"
						class="btn button btn-main">查询</button>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="resultTable table table-hover table-bordered">
		<thead>
			<tr>
				<th><input type="checkbox" id="applyDemanderAll"></th>
				<th>流水号</th>
				<th>产品类别</th>
				<th>服务类型</th>
				<th>需求状态</th>
				<th>提交日期</th>
				<th>服务要求</th>
				<th>价格</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>处理人</th>
			</tr>
		</thead>
		<tbody id="applyDemanderBody">
		</tbody>
	</table>
	<div class="pager" style="display: none !important;"
		id="applyDemanderPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="applyDemanderPagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40"> <a
					href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a><select class="select"
					id="applyDemanderPageSize" data-action="select">
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
				</select>
			</div>
		</div>
		<div class="minMaxResult">
			view <span id="applyDemanderFirstResult"></span>-<span
				id="applyDemanderMaxResult"></span> of <span
				id="applyDemanderTotalRecords"></span>
		</div>
	</div>
	<div class="approval_info" style="display: none !important;"
		id="applyDemanderApproval">
		<input type="hidden" id="applyDemanderIds" /> <input type="hidden"
			id="applyDemanderType" value="20" />
		<table class="table">
			<tr>
				<td style="width: 60%;"><textarea rows="3" cols="40"
						class="required" label="备注" id="applyDemanderRemark"
						placeholder="备注" maxlength="200"></textarea></td>
				<td style="width: 10%;"><button class="btn button backBtn"
						id="applyDemanderReturnBtn">
						<span class="btnText">转新需求</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png"
							style="margin-left: 55px !important;">
					</button></td>
					<td style="width: 10%;"><button class="btn button backBtn"
						id="applyDemanderProcessBtn">
						<span class="btnText">转处理中</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png"
							style="margin-left: 55px !important;">
					</button></td>
				<td style="width: 10%;"><button class="btn button approvalBtn"
						id="applyDemanderApprovalBtn">
						<span class="btnText">转完成</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
				<td style="width: 10%;"><button class="btn button rejectBtn"
						id="applyDemanderRejectBtn">
						<span class="btnText">转废单</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
			</tr>
		</table>
	</div>
</div>