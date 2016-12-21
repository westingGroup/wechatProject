<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/registers/insideCustomerUpdate.js"></script>
<div id="insideCustomerUpdate" class="modal  fade" tabindex="-1"
	data-focus-on="input:first" aria-hidden="true"
	style="display: none; background-color: #fff; max-width: 80%; max-height: 290px; margin: auto; overflow: scroll;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true" onclick="clearDemanderCustomerUpdate()"></button>
		<h4 style="margin: 5px 0px;">内部提供商</h4>
	</div>
	<div class="modal-body subject">
		<div class="content" style="">
			<input type="hidden" id="id" />
			<div class="container commonStyle contact">
				<hr class="commonHr" />
				<div class="row contactPerson">
					<div class="col-md-1 label">
						<img alt="姓名"
							src="<%=request.getContextPath()%>/assets/img/contactPerson.png"
							width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;姓名：
					</div>
					<div class="col-md-11">
						<input type="text" id="icuLinkname"
							class="text required maxlength" label="姓名" maxlength="255" />
					</div>
				</div>
				<hr class="commonHr" />
				<div class="row contactPhone">
					<div class="col-md-1 label">
						<img alt="电话"
							src="<%=request.getContextPath()%>/assets/img/contactPhone.png"
							width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;电话：
					</div>
					<div class="col-md-11">
						<input type="text" id="icuLinkphone"
							class="text required phone maxlength" label="电话" maxlength="13" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<input type="hidden" id="icuId" />
		<div style="text-align: left; color: red;" id="icuTipsInfo"></div>
		<button type="button" class="btn button btn-main" id="icuConfirmBtn">确定</button>
		<button type="button" class="btn button btn-main" data-dismiss="modal"
			aria-hidden="true" id="icuCancelBtn">关闭</button>
	</div>
</div>