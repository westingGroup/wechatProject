package com.infosys.weixin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.infosys.basic.entity.Demander;
import com.infosys.basic.entity.Provider;
import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.entity.User;
import com.infosys.basic.service.IDemanderService;
import com.infosys.basic.service.IProviderService;
import com.infosys.basic.service.IServiceOrderService;
import com.infosys.basic.util.Constants;
import com.infosys.basic.util.DateUtil;
import com.infosys.basic.util.JsonUtil;
import com.infosys.basic.util.XiaoqUtil;
import com.infosys.weixin.model.TemplateData;
import com.infosys.weixin.model.WxTemplate;
import com.infosys.weixin.service.ITemplateService;
import com.infosys.weixin.web.servlet.WeixinContext;

//服务需求方
@RequestMapping("/demander")
@Controller
public class DemanderController {
    @Inject
    private IServiceOrderService serviceOrderService;

    @Inject
    private ITemplateService templateService;

    @Inject
    private IDemanderService demanderService;

    @Inject
    private IProviderService providerService;

    // 分页
    @RequestMapping(value = "/mydemanders", method = RequestMethod.POST)
    public @ResponseBody String mydemanders(String currentPage, String pageSize, String createBy, HttpSession session) {
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

        demanderSearchModal.setCreateBy(createBy);
        demanderSearchModal.setPager(demanderPage);
        PagerInfo<ServiceOrderDto> userResult = serviceOrderService.listServiceOrderByKeyword(demanderSearchModal);
        userResult.setTotalPage(userResult.getTotalPages());
        if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
            userResult.setIsCanUp(1);
        }

        if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
            userResult.setIsCanDown(1);
        }
        return jsonUtil.obj2json(userResult);
    }

    // 我的服务
    @RequestMapping("/list")
    public String list(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
        Demander demander = demanderService.loadByOpenid(u.getOpenid());
        if (demander == null
                || (demander != null && demander.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_REJECT)) {
            model.addAttribute("demander", new Demander());
            // 进入服务需求方-注册页面
            model.addAttribute("fromPath", "2");
            model.addAttribute("openid", u.getOpenid());
            return "demander/register";
        }
        // 没有审批 进入审批页面
        if (demander.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL
                || demander.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
            model.addAttribute("demander", demander);
            return "demander/verify";
        }
        // 进入服务需求方-我的服务
        ServiceOrderModel demanderSearchModal = new ServiceOrderModel();
        PagerInfo<ServiceOrderDto> demanderPage = new PagerInfo<ServiceOrderDto>();
        demanderPage.setCurrentPage(1L);
        demanderPage.setPageSize(10L);
        demanderSearchModal.setCreateBy(String.valueOf(demander.getId()));
        demanderSearchModal.setPager(demanderPage);
        PagerInfo<ServiceOrderDto> userResult = serviceOrderService.listServiceOrderByKeyword(demanderSearchModal);
        userResult.setTotalPage(userResult.getTotalPages());
        if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
            userResult.setIsCanUp(1);
        }
        if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
            userResult.setIsCanDown(1);
        }
        model.addAttribute("orders", userResult);
        model.addAttribute("demanderId", demander.getId());
        model.addAttribute("demander", demander);
        return "demander/list";
    }

    // 服务申请
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
        Demander demander = demanderService.loadByOpenid(u.getOpenid());
        if (demander == null
                || (demander != null && demander.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_REJECT)) {
            model.addAttribute("demander", new Demander());
            // 进入服务需求方-注册页面
            model.addAttribute("fromPath", "1");
            model.addAttribute("openid", u.getOpenid());
            return "demander/register";
        }
        // 没有审批 进入审批页面
        if (demander.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL
                || demander.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
            model.addAttribute("demander", demander);
            return "demander/verify";
        }
        model.addAttribute("serviceType", ServiceOrder.ServiceType.values());
        model.addAttribute("categoryType", ServiceOrder.CategoryType.values());
        model.addAttribute("order", new ServiceOrder());
        model.addAttribute("demander", demander);
        return "demander/add";
    }

    // 我的申请
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(ServiceOrder order, HttpSession session) {
        order.setCreateDate(new Date());
        order.setStatus(Constants.T_SERVICE_ORDER_STATUS_NEW);// 新需求
        order.setServiceOrderId(String.valueOf(System.currentTimeMillis()));
        order.setCategory(order.getCategory());
        order.setServiceType(order.getServiceType());
        if (StringUtils.isNotBlank(order.getContent())) {
            order.setContent(XiaoqUtil.filterEmoji(order.getContent()));
        }
        serviceOrderService.add(order);
        return "redirect:/demander/list";
    }

    // 进入服务需求方-注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "fromPath", required = false) String fromPath,
            @RequestParam(value = "birth", required = false) String birth,
            @RequestParam(value = "openid", required = false) String openid, Demander demander, HttpSession session,
            Model model) {
        Demander dem = demanderService.loadByOpenid(openid);
        if (dem != null) {
            dem.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
            dem.setBirthDate(DateUtil.parseDate(birth, "yyyy-MM-dd"));
            dem.setLinkname(demander.getLinkname());
            dem.setLinkphone(demander.getLinkphone());
            dem.setBusiness(demander.getBusiness());
            dem.setQualification(demander.getQualification());
            dem.setCompany(demander.getCompany());
            dem.setCreateDate(new Date());
            demanderService.update(dem);
            model.addAttribute("demander", dem);
        } else {
            demander.setOpenid(openid);
            demander.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
            demander.setBirthDate(DateUtil.parseDate(birth, "yyyy-MM-dd"));
            demander.setCreateDate(new Date());
            demanderService.add(demander);
            model.addAttribute("demander", demander);
        }
        if (fromPath.equals("1")) {
            return "demander/verify";
        } else if (fromPath.equals("2")) {
            return "demander/verify";
        }
        return "demander/verify";
    }

    @RequestMapping(value = "/evaluate/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String update(@PathVariable int id, String evaluate, String evaluateContent) {
        ServiceOrder tu = serviceOrderService.load(id);
        if (StringUtils.isNotBlank(tu.getEvaluate())) {
            return JsonUtil.getInstance().obj2json("您已经评价过");
        }
        tu.setEvaluate(evaluate);
        tu.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_DEALING_EVALUATE);
        tu.setEvaluateContent(StringUtils.trim(evaluateContent));
        serviceOrderService.update(tu);
        return JsonUtil.getInstance().obj2json("评价成功");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public @ResponseBody String update(@PathVariable int id, Model model) {
        Demander u = demanderService.get(id);
        return JsonUtil.getInstance().obj2json(u);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public @ResponseBody String update(@PathVariable int id, Demander demander,
            @RequestParam(value = "birth", required = false) String birth) {
        Demander tu = demanderService.load(id);
        tu.setBirthDate(DateUtil.parseDate(birth, "yyyy-MM-dd"));
        tu.setBusiness(demander.getBusiness());
        tu.setCompany(demander.getCompany());
        tu.setLinkname(demander.getLinkname());
        tu.setLinkphone(demander.getLinkphone());
        demanderService.update(tu);
        return "更新成功";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/text; charset=utf-8")
    public @ResponseBody String delete(@PathVariable int id) {
        Demander tu = demanderService.load(id);
        tu.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_DELETE);
        demanderService.update(tu);
        return "删除成功";
    }

    @RequestMapping(value = "/enable/{id}", method = RequestMethod.POST)
    public @ResponseBody String enable(@PathVariable int id) {
        Demander tu = demanderService.load(id);
        tu.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_PASS);
        demanderService.update(tu);
        return JsonUtil.getInstance().obj2json("启用成功");
    }

    @RequestMapping(value = "/apply/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String apply(@PathVariable int id, int providerId) {
        ServiceOrder tu = serviceOrderService.load(id);
        tu.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_APPLYED);
        tu.setCompleteDate(new Date());
        serviceOrderService.update(tu);
        // 提供商 申请完成 发模板消息给 需求方
        final Provider provider = providerService.get(providerId);
        final Demander demander = demanderService.get(tu.getCreateBy());
        final ServiceOrder so = tu;
        // 提供商 申请完成 发模板消息给 需求方 需求方可以去完成确认 确认了后就可以去评价
        new Thread() {
            public void run() {
                String bindUrl = WeixinContext.getInstance().getBaseUrl();
                sendTemplateMsg(WeixinContext.getInstance().getTemplateDemander(), bindUrl + "/demander/list",
                        demander.getOpenid(), "需求单" + so.getServiceOrderId() + "已完成申请", so.getContent(),
                        provider.getLinkname() + "（手机" + provider.getLinkphone() + "）",
                        DateUtil.format(new Date(), "yyyy-MM-dd HH:mm"), "点击该信息为本次为您服务的工程师打分");
            }
        }.start();
        return JsonUtil.getInstance().obj2json("申请成功");
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

    @RequestMapping(value = "/cancelServiceOrder/{serviceOrderId}", method = RequestMethod.POST)
    public @ResponseBody String cancelServiceOrder(@PathVariable String serviceOrderId, String remark, int dealBy,
            String dealName, HttpSession session) {
        JsonUtil jsonUtil = JsonUtil.getInstance();
        String rtnStr = "撤销失败";
        if (StringUtils.isNotBlank(serviceOrderId)) {
            ServiceOrder serviceOrder = serviceOrderService.get(Integer.parseInt(serviceOrderId));
            if (serviceOrder.getStatus() == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_NEW) {
                serviceOrder.setRemark1(StringUtils.isEmpty(remark) ? "主动撤销" : remark.trim());
                serviceOrder.setDealBy(dealBy);
                serviceOrder.setType(9);
                serviceOrder.setDealDate(new Date());
                serviceOrder.setDealname(dealName.trim());
                serviceOrder.setStatus(com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL);
                serviceOrderService.update(serviceOrder);
                serviceOrder = null;
                rtnStr = "撤销成功";
            } else if (serviceOrder.getStatus() == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_APPLY) {
                rtnStr = "需求单已经被申领，不能进行撤销操作";
            } else if (serviceOrder.getStatus() == com.infosys.basic.util.Constants.T_SERVICE_ORDER_STATUS_CANCEL) {
                rtnStr = "需求单已经被撤销，不能重复撤销";
            }
        } else {
            rtnStr = "请选择";
        }
        return jsonUtil.obj2json(rtnStr);
    }

}
