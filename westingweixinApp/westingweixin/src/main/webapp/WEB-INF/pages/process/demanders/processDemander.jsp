<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<table class="searchTable table table-hover table-bordered">
		<thead>
			<tr>
				<th><input type="checkbox"></th>
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
		<tbody>
			<tr>
				<td><input type="checkbox" /></td>
				<td></td>
				<td></td>
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
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<div class="pager">
		<div class="pageNum">
			<div class="gigantic pagination">
				<a href="#" class="first" data-action="first">&laquo;</a> <a
					href="#" class="previous" data-action="previous">&lsaquo;</a> <input
					type="text" readonly="readonly" data-max-page="40"> <a
					href="#" class="next" data-action="next">&rsaquo;</a> <a href="#"
					class="last" data-action="last">&raquo;</a>
			</div>
		</div>
		<div class="minMaxResult">view 1-10 of 23</div>
	</div>
	<div class="approval_info">
		<table class="table">
			<tr>
				<td style="width: 60%;"><textarea rows="3" cols="40"></textarea></td>
				<td style="width: 20%;"><button class="btn approvalBtn">
						<span class="btnText">转完成</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
				<td style="width: 20%;"><button class="btn rejectBtn">
						<span class="btnText">转废单</span> <img class="btnImg"
							src="<%=request.getContextPath()%>/assets/img/right_arrow.png">
					</button></td>
			</tr>
		</table>
	</div>
</div>