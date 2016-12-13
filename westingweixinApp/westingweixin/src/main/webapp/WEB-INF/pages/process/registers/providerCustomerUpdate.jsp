<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/registers/providerCustomerUpdate.js"></script>
<div id="providerCustomerUpdate" class="modal  fade" tabindex="-1"
	data-focus-on="input:first" aria-hidden="true"
	style="display: none; background-color: #fff; max-width: 80%; max-height: 400px; margin: auto; overflow: scroll;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true" onclick="clearProviderCustomerUpdate()"></button>
		<h4 style="margin: 5px 0px;">服务提供商</h4>
	</div>
	<div class="modal-body subject">
		<div class="content">
			<div class="container commonStyle contact">
				<div class="row contactPerson">
					<div class="col-md-1 label">
						<img alt="姓名"
							src="<%=request.getContextPath()%>/assets/img/contactPerson.png"
							width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;姓名：
					</div>
					<div class="col-md-11">
						<input id="pcuLinkName" class="text required maxlength" label="姓名"
							maxlength="255" />
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
						<input id="pcuLinkPhone" class="text required phone maxlength"
							label="电话" maxlength="13" />
					</div>
				</div>
				<hr class="commonHr" />
				<div class="row contactBirthday">
					<div class="col-md-1 label">
						<img alt="出生年月"
							src="<%=request.getContextPath()%>/assets/img/calendar.png"
							width="16px" height="16px">&nbsp;&nbsp;出生年月：
					</div>
					<div class="col-md-11 input-append date form_datetime_day"
						style="padding: 0px;">
						<div class="row">
							<div class="col-md-10" style="padding: 0px;">
								<input type="text" class="text" name="pcuBirth"
									readonly="readonly" />
							</div>
							<div class="col-md-2" style="padding: 0px; text-align: right;">
								<span class="add-on"><i class="icon-remove"></i></span> <span
									class="add-on"><i class="icon-calendar"></i></span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="container commonStyle company">
				<div class="row companyBusiness">
					<div class="col-md-1 label">
						&nbsp;<font color="red" style="margin-left: 16px;">*</font>&nbsp;擅长业务：
					</div>
					<div class="col-md-11">
						<input id="pcuBusiness" class="text required maxlength"
							label="擅长业务" maxlength="255" />
					</div>
				</div>
				<hr class="commonHr" />
				<div class="row companyQualifition">
					<div class="col-md-1 label">
						<span style="margin-left: 26px;"></span>职称/资质：
					</div>
					<div class="col-md-11">
						<input id="pcuQualification" class="text maxlength" label="职称/资质"
							maxlength="255" />
					</div>
				</div>
				<hr class="commonHr" />
				<div class="row companyName">
					<div class="col-md-1 label">
						<span style="margin-left: 26px;">公司名称：</span>
					</div>
					<div class="col-md-11">
						<input id="pcuCompany" class="text maxlength" label="公司名称"
							maxlength="255" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal-footer">
		<input type="hidden" id="pcuId" />
		<div style="text-align: left; color: red;" id="pcuTipsInfo"></div>
		<button type="button" class="btn btn-main" id="pcuConfirmBtn">确定</button>
		<button type="button" class="btn btn-main" data-dismiss="modal"
			aria-hidden="true" id="pcuCancelBtn">关闭</button>
	</div>
</div>
