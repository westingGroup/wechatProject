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
				<th>操作</th>
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
				<td><img alt="修改"
					src="<%=request.getContextPath()%>/assets/img/edit.png"
					width="16px" height="16px">&nbsp;&nbsp;<img alt="删除"
					src="<%=request.getContextPath()%>/assets/img/删除.png" width="16px"
					height="16px" /></td>
			</tr>
			<tr>
				<td><input type="checkbox" /></td>
				<td></td>
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
				<td></td>
			</tr>
			<tr>
				<td><input type="checkbox" /></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<div class="approval_info" style="text-align: right;">
		<button class="btn approvalBtn">新增</button>
	</div>
</div>