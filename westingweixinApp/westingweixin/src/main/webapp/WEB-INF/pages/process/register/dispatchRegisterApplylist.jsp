<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<table class="searchTable table table-hover table-bordered">
		<thead>
			<tr>
				<th><input type="checkbox"></th>
				<th>序号</th>
				<th>联系人</th>
				<th>联系方式</th>
				<th>相关业务</th>
				<th>公司名称</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="checkbox" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input type="checkbox" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input type="checkbox" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><input type="checkbox" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<div class="approval_info">
		<table class="table">
			<tr>
				<td><textarea rows="3" cols="40"></textarea></td>
				<td><button class="btn approvalBtn">
						<span class="btnText">批准</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
				<td><button class="btn rejectBtn">
						<span class="btnText">拒绝</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
			</tr>
		</table>
	</div>
</div>