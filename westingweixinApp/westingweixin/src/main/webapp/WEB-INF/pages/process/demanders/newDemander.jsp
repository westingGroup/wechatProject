<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/demander/newDemander.js"></script>
<div>
	<table class="searchTable table">
		<tbody>
			<tr>
				<td>流水号:</td>
				<td><input type="text" id="newDemanderSOI" class="search" /></td>
				<td>联系人:</td>
				<td><input type="text" id="newDemanderLN" class="search" /></td>
				<td>联系电话:</td>
				<td><input type="text" id="newDemanderLP" class="search" /></td>
			</tr>
			<tr>
				<td>产品类别:</td>
				<td><sf:select id="newDemanderCG" path="categoryType"
						cssClass="select search" label="服务类型">
						<sf:option value="" label="请选择类别" />
						<sf:options items="${categoryType}" itemLabel="info"
							itemValue="info" />
					</sf:select></td>
				<td>服务类型:</td>
				<td><sf:select id="newDemanderST" path="serviceType"
						cssClass="select search" label="服务类型">
						<sf:option value="" label="请选择服务类型" />
						<sf:options items="${serviceType}" itemLabel="info"
							itemValue="info" />
					</sf:select></td>
				<td colspan="2" style="text-align: center; padding-left: 20px;">
					<button type="button" id="newDemanderBtn"
						class="btn button btn-main">查询</button>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="resultTable table table-hover table-bordered">
		<thead>
			<tr>
				<th></th>
				<th>流水号</th>
				<th>产品类别</th>
				<th>服务类型</th>
				<th>需求状态</th>
				<th>提交日期</th>
				<th>服务要求</th>
				<th>联系人</th>
				<th>联系电话</th>
			</tr>
		</thead>
		<tbody id="newDemanderBody">
		</tbody>
	</table>
	<div class="pager" style="display: none !important;"
		id="newDemanderPager">
		<div class="pageNum">
			<div class="gigantic pagination" id="newDemanderPagination">
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
			view <span id="newDemanderFirstResult"></span>-<span
				id="newDemanderMaxResult"></span> of <span
				id="newDemanderTotalRecords"></span>
		</div>
	</div>
	<div class="approval_info" style="display: none !important;"
		id="newDemanderApproval">
		<input type="hidden" id="selectNewDemanderId" /><input type="hidden"
			id="newDemanderType" value="11" />
		<table class="table">
			<tr>
				<td style="width: 30%;"><textarea rows="3" cols="40"
						class="required" id="newDemanderRemark" label="备注"
						placeholder="备注" maxlength="200"></textarea></td>
				<td style="width: 40%;"><select class="select required" style="width:90%"
					id="newDemanderEngineer" label="请选择工程师"><option value="">请选择工程师</option></select><img
					class="btnImg" style="margin-left: 5px; cursor: pointer;"
					id="insideEngineerImg"
					src="<%=request.getContextPath()%>/assets/img/magnifier.png"></td>
				<td style="width: 15%;"><button class="btn button approvalBtn"
						id="newDemanderApprovalBtn">
						<span class="btnText">转处理中</span> <img class="btnImg"
							style="margin-left: 55px !important;"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
				<td style="width: 15%;"><button class="btn button rejectBtn"
						id="newDemanderRejectBtn">
						<span class="btnText">转废单</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
			</tr>
		</table>
	</div>
	<jsp:include page="insideProvider.jsp"></jsp:include>
</div>