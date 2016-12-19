package com.infosys.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import com.infosys.basic.util.JsonUtil;

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
    @RequestMapping(value = "/dealRegister", method = RequestMethod.POST)
    public @ResponseBody String dealRegister(String type, String demanderIds, String remark, int dealType,
            HttpSession session) {
        String rtnStr = "操作失败";
        if (StringUtils.isNotBlank(demanderIds)) {
            String[] deIds = demanderIds.split(",");
            if (deIds.length > 0) {
                Demander demander;
                Provider provider;
                for (int i = 0; i < deIds.length; i++) {
                    String id = deIds[i];
                    if (type.equals("demander")) {
                        demander = demanderService.load(Integer.parseInt(id));
                        demander.setRemark(remark);
                        if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_PASS) {
                            demander.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_PASS);
                        } else if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_REJECT) {
                            demander.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_REJECT);
                        }
                        demanderService.update(demander);
                    } else if (type.equals("provider")) {
                        provider = providerService.load(Integer.parseInt(id));
                        provider.setRemark(remark);
                        if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_PASS) {
                            provider.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_PASS);
                        } else if (dealType == com.infosys.basic.util.Constants.T_USER_STATUS_REJECT) {
                            provider.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_REJECT);
                        }
                        providerService.update(provider);
                    }
                }
                demander = null;
                provider = null;
                rtnStr = "操作成功";
            }
        } else {
            rtnStr = "请选择";
        }
        return rtnStr;
    }

    // 首页
    @RequestMapping(value = "/demanders")
    public ModelAndView approvalDemander(String type) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("type", type);
        modelAndView.setViewName("process/demanders/approvalDemander");
        return modelAndView;
    }

    // type 1新需求 2处理中 9完成 10废单
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
        return jsonUtil.obj2json(applys);
    }

    // InsideOrOutSide inside or outside
    @RequestMapping(value = "/dealDemander", method = RequestMethod.POST)
    public @ResponseBody String dealDemander(String type, String demanderIds, String remark, int dealType,
            String InsideOrOutSide, int dealBy, String dealName, HttpSession session) {
        String rtnStr = "操作失败";
        if (StringUtils.isNotBlank(demanderIds)) {
            String[] deIds = demanderIds.split(",");
            if (deIds.length > 0) {
                ServiceOrder serviceOrder;
                for (int i = 0; i < deIds.length; i++) {
                    String id = deIds[i];
                    serviceOrder = serviceOrderService.load(Integer.parseInt(id));
                    if (type.equals("1")) {
                        serviceOrder.setRemark1(StringUtils.isEmpty(remark) ? "" : remark.trim());
                        if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING) {
                            if (InsideOrOutSide.equals("inside")) {
                                serviceOrder.setDealBy(dealBy);
                                serviceOrder.setType(1);
                            } else {
                                serviceOrder.setDealBy(dealBy);
                                serviceOrder.setType(0);
                            }
                            serviceOrder.setDealDate(new Date());
                            serviceOrder.setDealname(dealName.trim());
                            serviceOrder
                                    .setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING);
                        } else if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL) {
                            serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL);
                        }
                    } else if (type.equals("2")) {
                        serviceOrder.setRemark2(StringUtils.isEmpty(remark) ? "" : remark.trim());
                        if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_DONE) {
                            serviceOrder
                                    .setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_DONE);
                        } else if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL) {
                            serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL);
                        } else if (dealType == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_NEW) {
                            List<Apply> apList = applyService.listBySId(id);
                            if (apList != null && apList.size() > 0) {
                                serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_APPLY);
                            } else {
                                serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_NEW);
                            }
                        }
                    }
                    serviceOrderService.update(serviceOrder);
                }
                serviceOrder = null;
                rtnStr = "操作成功";
            }
        } else {
            rtnStr = "请选择";
        }
        return rtnStr;
    }

}
