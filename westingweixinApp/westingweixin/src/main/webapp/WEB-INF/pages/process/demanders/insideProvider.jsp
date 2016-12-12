<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/demander/insideProvider.js"></script>
<div id="insideProvider" class="modal  fade" tabindex="-1"
	data-focus-on="input:first" aria-hidden="true"
	style="display: none; background-color: #fff; max-width: 80%; max-height: 400px; margin: auto;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true" onclick="clearInsideProvider()"></button>
		<h4 style="margin: 5px 0px;">内部员工</h4>
	</div>
	<div class="modal-body">
		<table class="searchTable">
			<tbody>
				<tr>
					<td>姓名:</td>
					<td><input type="text" id="userName" /></td>
					<td>电话:</td>
					<td><input type="text" id="phone" /></td>
					<td style="text-align: right;">
						<button type="button" id="insideProviderBtn" class="btn btn-main">查询</button>
					</td>
				</tr>
			</tbody>
		</table>
		<div>
			<br>
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th scope="col" style="text-align: center;"></th>
						<th scope="col" style="text-align: center;">姓名</th>
						<th scope="col" style="text-align: center;">电话</th>
					</tr>
				</thead>
				<tbody id="insideProviderBody">
				</tbody>
			</table>
			<!-- begin 上一页下一页操作 -->
			<!-- end 上一页下一页操作 -->
		</div>
	</div>

	<div class="modal-footer">
		<input type="hidden" id="insideProviderId" /> <input type="hidden"
			id="insideProviderName" />
		<div style="text-align: left;" id="insideProviderTipsInfo"></div>
		<button type="button" class="btn btn-main"
			id="insideProviderConfirmBtn">确定</button>
		<button type="button" class="btn btn-main" data-dismiss="modal"
			aria-hidden="true" id="insideProviderCancelBtn">关闭</button>
	</div>
</div>