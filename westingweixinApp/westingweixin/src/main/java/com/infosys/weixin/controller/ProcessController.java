package com.infosys.weixin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.basic.dto.ApplyDto;
import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.dto.ProcessCount;
import com.infosys.basic.dto.ServiceOrderDto;
import com.infosys.basic.dto.ServiceOrderModel;
import com.infosys.basic.entity.Apply;
import com.infosys.basic.entity.Demander;
import com.infosys.basic.entity.Provider;
import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.service.IApplyService;
import com.infosys.basic.service.IDemanderService;
import com.infosys.basic.service.IInsideProviderService;
import com.infosys.basic.service.IProviderService;
import com.infosys.basic.service.IServiceOrderService;
import com.infosys.basic.util.Constants;
import com.infosys.basic.util.DateUtil;
import com.infosys.basic.util.JsonUtil;
import com.infosys.weixin.model.TemplateData;
import com.infosys.weixin.model.WxTemplate;
import com.infosys.weixin.service.ITemplateService;
import com.infosys.weixin.web.servlet.WeixinContext;

@RequestMapping("/process")
@Controller
public class ProcessController {
    @Inject
    private IInsideProviderService insideProviderService;

    @Inject
    private IDemanderService demanderService;

    @Inject
    private IProviderService providerService;

    @Inject
    private IServiceOrderService serviceOrderService;

    @Inject
    private ITemplateService templateService;

    @Inject
    private IApplyService applyService;

    /**
     * 首页页面
     * 
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        ProcessCount processCount = new ProcessCount();
        DemanderModel demanderModel = new DemanderModel();
        demanderModel.setStatus(String.valueOf(Constants.T_USER_STATUS_NORMAL));
        // 服务需求方新注册人员
        processCount.setDemanderRegisterCount(demanderService.getDemanderTotalByConditions(demanderModel));
        // 服务提供商新注册人员
        processCount.setProviderRegisterCount(providerService.getDemanderTotalByConditions(demanderModel));
        // 新需求
        ServiceOrderModel serviceOrderModel = new ServiceOrderModel();
        serviceOrderModel.setStatus(String.valueOf(Constants.T_SERVICE_ORDER_STATUS_APPLY));
        processCount.setNewDemanderCount(serviceOrderService.getOrdersTotalByConditionsForProcess(serviceOrderModel));
        // 处理中需求
        serviceOrderModel.setStatus(String.valueOf(Constants.T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING));
        processCount.setProcessDemanderCount(serviceOrderService
                .getOrdersTotalByConditionsForProcess(serviceOrderModel));
        modelAndView.addObject("processCount", processCount);
        modelAndView.setViewName("process/index");
        return modelAndView;
    }

    @RequestMapping(value = "/processCount", method = RequestMethod.POST)
    public @ResponseBody String processCount() {
        JsonUtil jsonUtil = JsonUtil.getInstance();
        ProcessCount processCount = new ProcessCount();
        DemanderModel demanderModel = new DemanderModel();
        demanderModel.setStatus(String.valueOf(Constants.T_USER_STATUS_NORMAL));
        // 服务需求方新注册人员
        processCount.setDemanderRegisterCount(demanderService.getDemanderTotalByConditions(demanderModel));
        // 服务提供商新注册人员
        processCount.setProviderRegisterCount(providerService.getDemanderTotalByConditions(demanderModel));
        // 新需求
        ServiceOrderModel serviceOrderModel = new ServiceOrderModel();
        serviceOrderModel.setStatus(String.valueOf(Constants.T_SERVICE_ORDER_STATUS_APPLY));
        processCount.setNewDemanderCount(serviceOrderService.getOrdersTotalByConditionsForProcess(serviceOrderModel));
        // 处理中需求
        serviceOrderModel.setStatus(String.valueOf(Constants.T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING));
        processCount.setProcessDemanderCount(serviceOrderService
                .getOrdersTotalByConditionsForProcess(serviceOrderModel));
        return jsonUtil.obj2json(processCount);
    }

    // 首页
    @RequestMapping(value = "/registers")
    public ModelAndView approvalRegister(String type) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("type", type);
        modelAndView.setViewName("process/registers/approvalRegister");
        return modelAndView;
    }

    // 分页
    // type demander 和 provider 和inside
    // status 1 和11
    @RequestMapping(value = "/listByPage", method = RequestMethod.POST)
    public @ResponseBody String listByPage(String currentPage, String pageSize, String linkname, String linkphone,
            String type, String status, HttpSession session) {
        JsonUtil jsonUtil = JsonUtil.getInstance();
        DemanderModel demanderSearchModal = new DemanderModel();
        PagerInfo<DemanderDto> demanderPage = new PagerInfo<DemanderDto>();
        if (StringUtils.isBlank(currentPage)) {
            demanderPage.setCurrentPage(1L);
        } else {
            demanderPage.setCurrentPage(Long.valueOf(currentPage));
        }

        if (StringUtils.isBlank(pageSize)) {
            demanderPage.setPageSize(10L);
        } else {
            demanderPage.setPageSize(Long.valueOf(pageSize));
        }

        demanderSearchModal.setLinkname(StringUtils.isBlank(linkname) ? "" : linkname.trim());
        demanderSearchModal.setLinkphone(StringUtils.isBlank(linkphone) ? "" : linkphone.trim());
        demanderSearchModal
                .setStatus(StringUtils.isBlank(status) ? com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL_STR
                        : status.trim());
        demanderSearchModal.setPager(demanderPage);
        PagerInfo<DemanderDto> userResult = null;
        if (type.equals("demander")) {
            userResult = demanderService.listDemanderByKeyword(demanderSearchModal);
        } else if (type.equals("provider")) {
            userResult = providerService.listProviderByKeyword(demanderSearchModal);
        } else if (type.equals("inside")) {
            demanderSearchModal.setUsername(StringUtils.isBlank(linkname) ? "" : linkname.trim());
            demanderSearchModal.setPhone(StringUtils.isBlank(linkphone) ? "" : linkphone.trim());
            userResult = insideProviderService.listInsideProviderByKeyword(demanderSearchModal);
        }
        userResult.setTotalPage(userResult.getTotalPages());
        if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
            userResult.setIsCanUp(1);
        }

        if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
            userResult.setIsCanDown(1);
        }
        return jsonUtil.obj2json(userResult);
    }

    // pc处理注册的需求方和提供商
    // dealType 对应 11 批准10拒绝
    // type demander和provider
    // providerType 外部 内部下拉框
    @RequestMapping(value = "/dealRegister", method = RequestMethod.POST)
    public @ResponseBody String dealRegister(String type, String demanderIds, String remark, int dealType,
            String providerType, HttpSession session) {
        String rtnStr = "操作失败";
        if (StringUtils.isNotBlank(demanderIds)) {
            String[] deIds = demanderIds.split(",");
            if (deIds.length > 0) {
                Demander demander;
                Provider provider;
                for (int i = 0; i < deIds.length; i++) {
                    String id = deIds[i];
                    String url = null;
                    String first = null;
                    String to_openid = null;
                    String keyword1 = null;
                    String keyword2 = null;
                    String keyword3 = null;
                    if (type.equals("demander")) {
                        url = "/demander/add";
                        keyword3 = "服务需求方";
                        demander = demanderService.load(Integer.parseInt(id));
                        keyword2 = DateUtil.format(demander.getCreateDate(), "yyyy-MM-dd HH:mm");
                        keyword1 = demander.getLinkname();
                        to_openid = demander.getOpenid();
                        demander.setRemark(remark);
                        if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_PASS) {
                            first = "恭喜，您的服务需求方注册信息申请已经通过审核";
                            demander.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_PASS);
                        } else if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_REJECT) {
                            first = "抱歉，您的服务需求方注册信息申请没有通过审核";
                            demander.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_REJECT);
                        }
                        demanderService.update(demander);
                    } else if (type.equals("provider")) {
                        url = "/provider/add";
                        keyword3 = "服务提供商";
                        provider = providerService.load(Integer.parseInt(id));
                        keyword2 = DateUtil.format(provider.getCreateDate(), "yyyy-MM-dd HH:mm");
                        keyword1 = provider.getLinkname();
                        to_openid = provider.getOpenid();
                        provider.setRemark(remark);
                        if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_PASS) {
                            first = "恭喜，您的服务提供商注册信息申请已经通过审核";
                            provider.setType(Integer.parseInt(providerType));
                            provider.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_PASS);
                        } else if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_REJECT) {
                            first = "抱歉，您的服务提供商注册信息申请没有通过审核";
                            provider.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_REJECT);
                        }
                        providerService.update(provider);
                    }
                    final String remarkStr = StringUtils.isEmpty(remark) ? "审核通过" : remark.trim();
                    final String firstStr = first;
                    final String to_openidStr = to_openid;
                    final String keyword1Str = keyword1;
                    final String keyword2Str = keyword2;
                    final String keyword3Str = keyword3;
                    final String urlStr = url;
                    new Thread() {
                        public void run() {
                            // 所有转废单都需要发送模板消息
                            String bindUrl = WeixinContext.getInstance().getBaseUrl();
                            sendTemplateMsg(WeixinContext.getInstance().getTemplateRegisterAudit(), bindUrl + urlStr,
                                    to_openidStr, firstStr, keyword1Str, keyword2Str, keyword3Str, remarkStr);
                        }
                    }.start();
                }
                demander = null;
                provider = null;
                rtnStr = "操作成功";
            }
        } else {
            rtnStr = "请选择";
        }
        return JsonUtil.getInstance().obj2json(rtnStr);
    }

    // 首页
    @RequestMapping(value = "/demanders")
    public ModelAndView approvalDemander(String type) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("type", type);
        modelAndView.setViewName("process/demanders/approvalDemander");
        modelAndView.addObject("serviceType", ServiceOrder.ServiceType.values());
        modelAndView.addObject("categoryType", ServiceOrder.CategoryType.values());
        return modelAndView;
    }

    // type 11新需求 12处理中 20 已申请 90完成 100废单 110 已评价
    @RequestMapping(value = "/listOrdersByPage", method = RequestMethod.POST)
    public @ResponseBody String listByPageForDemander(String currentPage, String pageSize, String linkname,
            String linkphone, String serviceOrderId, String type, String category, String serviceType, String evaluate,
            HttpSession session) {
        JsonUtil jsonUtil = JsonUtil.getInstance();
        ServiceOrderModel demanderSearchModal = new ServiceOrderModel();
        PagerInfo<ServiceOrderDto> demanderPage = new PagerInfo<ServiceOrderDto>();
        if (StringUtils.isBlank(currentPage)) {
            demanderPage.setCurrentPage(1L);
        } else {
            demanderPage.setCurrentPage(Long.valueOf(currentPage));
        }

        if (StringUtils.isBlank(pageSize)) {
            demanderPage.setPageSize(10L);
        } else {
            demanderPage.setPageSize(Long.valueOf(pageSize));
        }

        demanderSearchModal.setLinkname(StringUtils.isBlank(linkname) ? "" : linkname.trim());
        demanderSearchModal.setLinkphone(StringUtils.isBlank(linkphone) ? "" : linkphone.trim());
        demanderSearchModal.setServiceOrderId(StringUtils.isBlank(serviceOrderId) ? "" : serviceOrderId.trim());
        demanderSearchModal.setStatus(type.trim());
        demanderSearchModal.setCategory(StringUtils.isBlank(category) ? "" : category.trim());
        demanderSearchModal.setEvaluate(StringUtils.isBlank(evaluate) ? "" : evaluate.trim());
        demanderSearchModal.setServiceType(StringUtils.isBlank(serviceType) ? "" : serviceType.trim());
        demanderSearchModal.setPager(demanderPage);
        PagerInfo<ServiceOrderDto> userResult = serviceOrderService.listProcessServiceOrders(demanderSearchModal);
        userResult.setTotalPage(userResult.getTotalPages());
        if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
            userResult.setIsCanUp(1);
        }

        if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
            userResult.setIsCanDown(1);
        }
        return jsonUtil.obj2json(userResult);
    }

    @RequestMapping(value = "/listInsideProviderByPage", method = RequestMethod.POST)
    public @ResponseBody String listInsideProviderByPage(String currentPage, String pageSize, String linkname,
            String linkphone, HttpSession session) {
        JsonUtil jsonUtil = JsonUtil.getInstance();
        DemanderModel demanderSearchModal = new DemanderModel();
        PagerInfo<DemanderDto> demanderPage = new PagerInfo<DemanderDto>();
        if (StringUtils.isBlank(currentPage)) {
            demanderPage.setCurrentPage(1L);
        } else {
            demanderPage.setCurrentPage(Long.valueOf(currentPage));
        }

        if (StringUtils.isBlank(pageSize)) {
            demanderPage.setPageSize(10L);
        } else {
            demanderPage.setPageSize(Long.valueOf(pageSize));
        }

        demanderSearchModal.setUsername(StringUtils.isBlank(linkname) ? "" : linkname.trim());
        demanderSearchModal.setPhone(StringUtils.isBlank(linkphone) ? "" : linkphone.trim());
        demanderSearchModal.setType(com.infosys.basic.util.Constants.T_SERVICE_ORDER_TYPE_INSIDE);
        demanderSearchModal.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_PASS_STR);
        demanderSearchModal.setPager(demanderPage);
        PagerInfo<DemanderDto> userResult = providerService.listProviderByType(demanderSearchModal);
        userResult.setTotalPage(userResult.getTotalPages());
        if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
            userResult.setIsCanUp(1);
        }

        if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
            userResult.setIsCanDown(1);
        }
        return jsonUtil.obj2json(userResult);
    }

    @RequestMapping(value = "/outsideProviderList", method = RequestMethod.POST)
    public @ResponseBody String insideProviderList(String sId, HttpSession session) {
        JsonUtil jsonUtil = JsonUtil.getInstance();
        List<Apply> applys = applyService.listBySId(sId);
        List<ApplyDto> applyDtos = new ArrayList<ApplyDto>();
        ApplyDto dto;
        if (applys != null && applys.size() > 0) {
            for (Apply apply : applys) {
                Provider provider = providerService.get(apply.getApplyBy());
                dto = new ApplyDto();
                dto.setApplyBy(apply.getApplyBy());
                dto.setApplyname(apply.getApplyname());
                dto.setPrice(apply.getPrice());
                dto.setCompleteDate(apply.getCompleteDate());
                if (provider.getBusiness().length() > 10) {
                    dto.setBusiness(provider.getBusiness().substring(0, 10));
                }else{
                    dto.setBusiness(provider.getBusiness());
                }
              
                dto.setLinkphone(provider.getLinkphone());
                applyDtos.add(dto);
                dto = null;
            }
        }

        return jsonUtil.obj2json(applyDtos);
    }

    // InsideOrOutSide inside or outside
    // type 11新需求 和12 处理中 20 已申请
    // dealType对应 12 转处理中 100 转废单 0 转新需求 90完成
    // InsideOrOutSide 是 inside 是内部员工 type是1 否则是type是0 外部 ；1 已申领待分配
    // from pc wx
    @RequestMapping(value = "/dealDemander", method = RequestMethod.POST)
    public @ResponseBody String dealDemander(String from, String type, String demanderIds, String remark, int dealType,
            String InsideOrOutSide, int dealBy, String dealName, HttpSession session) {
        JsonUtil jsonUtil = JsonUtil.getInstance();
        String rtnStr = "操作失败";
        if (StringUtils.isNotBlank(demanderIds)) {
            String[] deIds = demanderIds.split(",");
            if (deIds.length > 0) {
                ServiceOrder serviceOrder;
                for (int i = 0; i < deIds.length; i++) {
                    String id = deIds[i];
                    serviceOrder = serviceOrderService.load(Integer.parseInt(id));
                    if (type.equals(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_APPLY_STR)) {
                        serviceOrder.setRemark1(StringUtils.isEmpty(remark) ? "" : remark.trim());
                        if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING) {
                            if (InsideOrOutSide.equals("inside")) {
                                serviceOrder.setDealBy(dealBy);
                                serviceOrder.setType(1);

                                List<Apply> apList = applyService.listBySIdApplyBy(
                                        String.valueOf(serviceOrder.getId()), String.valueOf(dealBy));
                                if (apList == null || apList.size() < 1) {
                                    // 分配给内部员工添加 一条申请
                                    Apply apply = new Apply();
                                    apply.setApplyBy(dealBy);// 外部人员
                                    apply.setApplyname(dealName);
                                    apply.setApplyDate(new Date());
                                    apply.setsId(Integer.parseInt(id));
                                    apply.setType(1);
                                    applyService.add(apply);
                                }

                            } else {
                                serviceOrder.setDealBy(dealBy);
                                serviceOrder.setType(0);
                            }
                            serviceOrder.setDealDate(new Date());
                            if (StringUtils.isNotBlank(dealName)) {
                                String[] dealnameStr = dealName.split(" ");
                                serviceOrder.setDealname(dealnameStr[0]);
                            }
                            serviceOrder
                                    .setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING);

                            final Provider provider = providerService.get(dealBy);
                            final Demander demander = demanderService.get(serviceOrder.getCreateBy());
                            List<Apply> apList = applyService.listBySIdApplyBy(String.valueOf(serviceOrder.getId()),
                                    String.valueOf(dealBy));
                            Apply apply = null;
                            if (apList != null && apList.size() > 0) {
                                apply = apList.get(0);
                            }
                            final Apply ay = apply;
                            final ServiceOrder so = serviceOrder;
                            new Thread() {
                                public void run() {
                                    // 新需求转处理 给提供商发模板消息 进行完成申请
                                    String bindUrl = WeixinContext.getInstance().getBaseUrl();
                                    sendTemplateMsg(WeixinContext.getInstance().getTemplateProvider(), bindUrl
                                            + "/demander/list", demander.getOpenid(), "尊敬的" + demander.getLinkname()
                                            + "，您的需求单已经被" + provider.getLinkname() + "（手机" + provider.getLinkphone()
                                            + "）" + "受理", so.getServiceOrderId(), so.getServiceType(), DateUtil.format(
                                            ay == null ? new Date() : ay.getApplyDate(), "yyyy-MM-dd HH:mm"),
                                            "如果计划有变，请联系服务专员协调");
                                    sendTemplateMsg(
                                            WeixinContext.getInstance().getTemplateProvider(),
                                            bindUrl + "/provider/list",
                                            provider.getOpenid(),
                                            "尊敬的" + provider.getLinkname() + "，需求单" + so.getServiceOrderId()
                                                    + "已经分配给您处理。需求人:" + demander.getLinkname() + "（手机"
                                                    + demander.getLinkphone() + "）", so.getServiceOrderId(),
                                            so.getServiceType(), DateUtil.format(new Date(), "yyyy-MM-dd HH:mm"),
                                            "如果计划有变，请联系服务专员协调");
                                }
                            }.start();
                        } else if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL) {
                            serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL);
                        }
                    } else if (type
                            .equals(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING_STR)) {
                        serviceOrder.setRemark1(StringUtils.isEmpty(remark) ? "" : remark.trim());
                        if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_DONE) {
                            serviceOrder
                                    .setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_DONE);
                        } else if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL) {
                            serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL);
                        } else if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_NEW) {
                            // 修改 2017年2月13日 如果申领了 还是转新需求，而且干掉申领
                            // List<Apply> apList = applyService.listBySId(id);
                            /*
                             * if (apList != null && apList.size() > 0) {
                             * serviceOrder
                             * .setStatus(com.infosys.basic.util.Constants
                             * .T_SERVICE_ORDER_STATUS_APPLY); } else {
                             */
                            serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_NEW);
                            applyService.deleteBySId(id);
                            // }
                        }
                    } else if (type.equals(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_APPLYED_STR)) {
                        serviceOrder.setRemark1(StringUtils.isEmpty(remark) ? "" : remark.trim());
                        if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_DONE) {
                            serviceOrder
                                    .setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_DONE);
                            serviceOrder.setCompleteDate(new Date());
                        } else if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL) {
                            serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL);
                        } else if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_NEW) {
                            List<Apply> apList = applyService.listBySId(id);
                            if (apList != null && apList.size() > 0) {
                                serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_APPLY);
                            } else {
                                serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_NEW);
                            }
                        } else if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING) {
                            serviceOrder
                                    .setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING);
                        }
                    }
                    serviceOrderService.update(serviceOrder);

                    if (from.equals("pc") && dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL) {
                        final Demander demander = demanderService.get(serviceOrder.getCreateBy());
                        final String serviceOrderId = serviceOrder.getServiceOrderId();
                        final String serviceType = serviceOrder.getServiceType();
                        final String remarkStr = StringUtils.isEmpty(remark) ? "" : remark.trim();
                        new Thread() {
                            public void run() {
                                // 所有转废单都需要发送模板消息
                                String bindUrl = WeixinContext.getInstance().getBaseUrl();
                                sendTemplateMsg(WeixinContext.getInstance().getTemplateProvider(), bindUrl
                                        + "/demander/add", demander.getOpenid(), "抱歉，您的服务需求因为不符合要求，已经作废，请重新提交",
                                        serviceOrderId, serviceType, DateUtil.format(new Date(), "yyyy-MM-dd HH:mm"),
                                        remarkStr);
                            }
                        }.start();
                    }
                }
                serviceOrder = null;
                rtnStr = "操作成功";
            }
        } else {
            rtnStr = "请选择";
        }
        return jsonUtil.obj2json(rtnStr);
    }

    private void sendTemplateMsg(String templateId, String bindUrl, String openid, String msg, String serviceOrderId,
            String serviceType, String applyDate, String remarkValue) {
        WxTemplate temp = new WxTemplate();
        temp.setUrl(bindUrl);
        temp.setTouser(openid);
        temp.setTemplate_id(templateId);
        temp.setTopcolor("#FF0000");
        Map<String, TemplateData> m = new HashMap<String, TemplateData>();
        TemplateData first = new TemplateData();
        first.setColor("#173177");
        first.setValue(msg);
        m.put("first", first);

        TemplateData keyword1 = new TemplateData();
        keyword1.setColor("#173177");
        keyword1.setValue(serviceOrderId);
        m.put("keyword1", keyword1);

        TemplateData keyword2 = new TemplateData();
        keyword2.setColor("#173177");
        keyword2.setValue(serviceType);
        m.put("keyword2", keyword2);

        TemplateData keyword3 = new TemplateData();
        keyword3.setColor("#173177");
        keyword3.setValue(applyDate);
        m.put("keyword3", keyword3);
        temp.setData(m);

        TemplateData remark = new TemplateData();
        remark.setColor("#173177");
        remark.setValue(remarkValue);
        m.put("remark", remark);
        temp.setData(m);

        templateService.sendTemplateMessage(temp);
    }

    @RequestMapping(value = "/priceUpdate/{id}", method = RequestMethod.GET, produces = "application/text; charset=utf-8")
    public @ResponseBody String priceUpdate(@PathVariable int id, int price) {
        Apply tu = applyService.get(id);
        tu.setPrice(price);
        applyService.update(tu);
        return "修改成功";
    }
}
