package com.infosys.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.dto.ServiceOrderDto;
import com.infosys.basic.dto.ServiceOrderModel;
import com.infosys.basic.entity.Apply;
import com.infosys.basic.entity.Provider;
import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.entity.User;
import com.infosys.basic.service.IApplyService;
import com.infosys.basic.service.IProviderService;
import com.infosys.basic.service.IServiceOrderService;
import com.infosys.basic.util.Constants;
import com.infosys.basic.util.DateUtil;
import com.infosys.basic.util.JsonUtil;

//服务提供方
@RequestMapping("/provider")
@Controller
public class ProviderController {
    @Inject
    private IServiceOrderService serviceOrderService;

    @Inject
    private IProviderService providerService;

    @Inject
    private IApplyService applyService;

    // 我的服务列表
    @RequestMapping("/list")
    public String list(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
        Provider provider = providerService.loadByOpenid(u.getOpenid());
        if (provider == null
                || (provider != null && provider.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_REJECT)) {
            model.addAttribute("provider", new Provider());
            // 进入服务供应商-注册页面
            model.addAttribute("fromPath", "2");
            model.addAttribute("openid", u.getOpenid());
            return "provider/register";
        }
        // 没有审批 进入审批页面
        if (provider.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL
                || provider.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
            model.addAttribute("provider", provider);
            return "provider/verify";
        }
        // 进入服务供应商-我的服务 (未申领；别人已申领待分配)
        // 我已经完成的 分配给我的我还没完成 我申领的还没有分配
        ServiceOrderModel serviceOrderModel = new ServiceOrderModel();
        PagerInfo<ServiceOrderDto> demanderPage = new PagerInfo<ServiceOrderDto>();
        demanderPage.setCurrentPage(1L);
        demanderPage.setPageSize(10L);
        serviceOrderModel.setDealBy(String.valueOf(provider.getId()));
        serviceOrderModel.setPager(demanderPage);
        PagerInfo<ServiceOrderDto> userResult = serviceOrderService
                .listServiceOrderByKeywordForMyMobileApplys(serviceOrderModel);
        userResult.setTotalPage(userResult.getTotalPages());
        if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
            userResult.setIsCanUp(1);
        }
        if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
            userResult.setIsCanDown(1);
        }
        model.addAttribute("orders", userResult);
        model.addAttribute("providerId", provider.getId());
        return "provider/list";
    }

    // 分页
    // 手机端 认领服务列表
    // dealBy 对应 model.addAttribute("providerId", provider.getId());
    @RequestMapping(value = "/myMobileApplys", method = RequestMethod.POST)
    public @ResponseBody String myMobileApplys(String currentPage, String pageSize, String dealBy, HttpSession session) {
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

        demanderSearchModal.setDealBy(dealBy);
        demanderSearchModal.setPager(demanderPage);
        PagerInfo<ServiceOrderDto> userResult = serviceOrderService
                .listServiceOrderByKeywordForMyMobileApplys(demanderSearchModal);
        userResult.setTotalPage(userResult.getTotalPages());
        if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
            userResult.setIsCanUp(1);
        }

        if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
            userResult.setIsCanDown(1);
        }
        return jsonUtil.obj2json(userResult);
    }

    // 服务认领
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
        Provider provider = providerService.loadByOpenid(u.getOpenid());
        if (provider == null
                || (provider != null && provider.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_REJECT)) {
            model.addAttribute("provider", new Provider());
            // 进入服务供应商-注册页面
            model.addAttribute("fromPath", "1");
            model.addAttribute("openid", u.getOpenid());
            return "provider/register";
        }

        // 没有审批 进入审批页面
        if (provider.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL
                || provider.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
            model.addAttribute("provider", provider);
            return "provider/verify";
        }
        // 进入服务供应商-认领页面
        // 新需求0 别人申领的我没申领
        ServiceOrderModel demanderSearchModal = new ServiceOrderModel();
        PagerInfo<ServiceOrderDto> demanderPage = new PagerInfo<ServiceOrderDto>();
        demanderPage.setCurrentPage(1L);
        demanderPage.setPageSize(10L);
        demanderSearchModal.setApplyBy(String.valueOf(provider.getId()));
        demanderSearchModal.setOpenid(u.getOpenid());
        demanderSearchModal.setPager(demanderPage);
        PagerInfo<ServiceOrderDto> userResult = serviceOrderService
                .listServiceOrderByKeywordForMobileApply(demanderSearchModal);
        userResult.setTotalPage(userResult.getTotalPages());
        if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
            userResult.setIsCanUp(1);
        }
        if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
            userResult.setIsCanDown(1);
        }
        model.addAttribute("orders", userResult);
        model.addAttribute("apply", new Apply());
        model.addAttribute("provider", provider);
        return "provider/add";
    }

    // 分页
    // 手机端 认领服务列表
    // applyBy 对应 model.addAttribute("provider", provider); 中设置的 provider的id
    @RequestMapping(value = "/mobileApply", method = RequestMethod.POST)
    public @ResponseBody String mobileApply(String currentPage, String pageSize, String applyBy, String openID,
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

        demanderSearchModal.setApplyBy(applyBy);
        demanderSearchModal.setOpenid(openID);
        demanderSearchModal.setPager(demanderPage);
        PagerInfo<ServiceOrderDto> userResult = serviceOrderService
                .listServiceOrderByKeywordForMobileApply(demanderSearchModal);
        userResult.setTotalPage(userResult.getTotalPages());
        if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
            userResult.setIsCanUp(1);
        }

        if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
            userResult.setIsCanDown(1);
        }
        return jsonUtil.obj2json(userResult);
    }

    // id 对于t_service_order的id
    // 认领
    // 添加 providerType 0外部的 1内部的
    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
    @ResponseBody
    public String add(@PathVariable int id, Apply apply,
            @RequestParam(value = "completeDateStr", required = false) String completeDateStr,
            @RequestParam(value = "providerId", required = false) String providerId,
            @RequestParam(value = "providerName", required = false) String providerName,
            @RequestParam(value = "providerType", required = true) String providerType) {
        ServiceOrder order = serviceOrderService.get(id);
        if (order.getStatus() == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_ALLOCATED_DEALING) {
            return "需求单已经是处理中，不能在认领了";
        } else if (order.getStatus() == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_APPLY) {
            List<Apply> apList = applyService.listBySIdApplyBy(String.valueOf(order.getId()), providerId);
            if (apList != null && apList.size() > 0) {
                return "您已经认领过";
            }
        }
        apply.setApplyBy(Integer.parseInt(providerId));// 外部人员
        apply.setApplyname(providerName);
        apply.setApplyDate(new Date());
        apply.setCompleteDate(DateUtil.parseDate(completeDateStr, "yyyy-MM-dd"));
        apply.setsId(id);
        apply.setType(Integer.parseInt(providerType));
        applyService.add(apply);

        order.setLastApplyBy(Integer.parseInt(providerId));
        order.setLastApplyDate(new Date());
        order.setLastApplyName(providerName);
        order.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_APPLY);
        serviceOrderService.update(order);
        return "您已经认领成功";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "fromPath", required = false) String fromPath,
            @RequestParam(value = "birth", required = false) String birth,
            @RequestParam(value = "openid", required = false) String openid, Provider provider, HttpSession session,
            Model model) {
        Provider pro = providerService.loadByOpenid(openid);
        if (pro != null) {
            pro.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
            pro.setBirthDate(DateUtil.parseDate(birth, "yyyy-MM-dd"));
            pro.setLinkname(provider.getLinkname());
            pro.setLinkphone(provider.getLinkphone());
            pro.setBusiness(provider.getBusiness());
            pro.setQualification(provider.getQualification());
            pro.setCompany(provider.getCompany());
            pro.setCreateDate(new Date());
            providerService.update(pro);
            model.addAttribute("provider", pro);
        } else {
            provider.setOpenid(openid);
            provider.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
            provider.setBirthDate(DateUtil.parseDate(birth, "yyyy-MM-dd"));
            provider.setCreateDate(new Date());
            providerService.add(provider);
            model.addAttribute("provider", provider);
        }
        if (fromPath.equals("1")) {
            return "provider/verify";
        } else if (fromPath.equals("2")) {
            return "provider/verify";
        }
        return "provider/verify";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public @ResponseBody String update(@PathVariable int id, Model model) {
        Provider u = providerService.get(id);
        return JsonUtil.getInstance().obj2json(u);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public @ResponseBody String update(@PathVariable int id, Provider provider,
            @RequestParam(value = "birth", required = false) String birth,
            @RequestParam(value = "providerType", required = true) String providerType) {
        Provider tu = providerService.load(id);
        tu.setBirthDate(DateUtil.parseDate(birth, "yyyy-MM-dd"));
        tu.setBusiness(provider.getBusiness());
        tu.setCompany(provider.getCompany());
        tu.setLinkname(provider.getLinkname());
        tu.setLinkphone(provider.getLinkphone());
        tu.setQualification(provider.getQualification());
        tu.setType(Integer.parseInt(providerType));
        providerService.update(tu);
        return "更新成功";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/text; charset=utf-8")
    public @ResponseBody String delete(@PathVariable int id) {
        Provider tu = providerService.load(id);
        tu.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_DELETE);
        providerService.update(tu);
        return "删除成功";
    }

    @RequestMapping(value = "/enable/{id}", method = RequestMethod.POST)
    public @ResponseBody String enable(@PathVariable int id) {
        Provider tu = providerService.load(id);
        tu.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_PASS);
        providerService.update(tu);
        return JsonUtil.getInstance().obj2json("启用成功");
    }

}
