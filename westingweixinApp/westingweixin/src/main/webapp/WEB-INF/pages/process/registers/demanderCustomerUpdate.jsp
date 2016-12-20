<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/assets/js/process/registers/demanderCustomerUpdate.js"></script>
<div id="demanderCustomerUpdate" class="modal  fade" tabindex="-1"
	data-focus-on="input:first" aria-hidden="true"
	style="display: none; background-color: #fff; max-width: 80%; max-height: 450px; margin: auto; overflow: scroll;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true" onclick="clearDemanderCustomerUpdate()"></button>
		<h4 style="margin: 5px 0px;">服务需求方</h4>
	</div>
	<div class="modal-body subject">
		<div class="content">
			<input type="hidden" id="id" />
			<div class="container commonStyle contact">
				<hr class="commonHr" />
				<div class="row contactPerson">
					<div class="col-md-2 label">
						<img alt="姓名"
							src="<%=request.getContextPath()%>/assets/img/contactPerson.png"
							width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;姓名：
					</div>
					<div class="col-md-10">
						<input type="text" id="dcuLinkname"
							class="text required maxlength" label="姓名" maxlength="255" />
					</div>
				</div>
				<hr class="commonHr" />
				<div class="row contactPhone">
					<div class="col-md-2 label">
						<img alt="电话"
							src="<%=request.getContextPath()%>/assets/img/contactPhone.png"
							width="16px" height="16px">&nbsp;<font color="red">*</font>&nbsp;电话：
					</div>
					<div class="col-md-10">
						<input type="text" id="dcuLinkphone"
							class="text required phone maxlength" label="电话" maxlength="13" />
					</div>
				</div>
				<hr class="commonHr" />
				<div class="row contactBirthday">
					<div class="col-md-2 label">
						<img alt="出生年月"
							src="<%=request.getContextPath()%>/assets/img/calendar.png"
							width="16px" height="16px">&nbsp;&nbsp;出生年月：
					</div>

					<div class="col-md-10 input-append date form_datetime_day"
						style="padding: 0px;">
						<div class="row" style="padding-top: 0px;">
							<div class="col-md-10" style="padding: 0px;">
								<input type="text" class="text" id="dcuBirth"
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
					<div class="col-md-2 label">
						&nbsp;<font color="red" style="margin-left: 16px;">*</font>&nbsp;公司名称：
					</div>
					<div class="col-md-10">
						<input type="text" id="dcuCompany" class="text required maxlength"
							label="公司名称" maxlength="255" />
					</div>
				</div>
				<hr class="commonHr" />
				<div class="row companyName">
					<div class="col-md-2 label">
						&nbsp;<font color="red" style="margin-left: 16px;">*</font>&nbsp;行业和相关业务：
					</div>
					<div class="col-md-10">
						<textarea rows="2" id="dcuBusiness"
							class="textarea required maxlength" label="行业和相关业务"
							maxlength="255"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal-footer">
		<input type="hidden" id="dcuId" />
		<div style="text-align: left; color: red;" id="dcuTipsInfo"></div>
		<button type="button" class="btn btn-main" id="dcuConfirmBtn">确定</button>
		<button type="button" class="btn btn-main" data-dismiss="modal"
			aria-hidden="true" id="dcuCancelBtn">关闭</button>
	</div>
</div>