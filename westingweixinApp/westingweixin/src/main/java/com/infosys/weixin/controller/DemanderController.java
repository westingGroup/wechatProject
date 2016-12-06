package com.infosys.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infosys.basic.entity.Demander;
import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.entity.User;
import com.infosys.basic.service.IDemanderService;
import com.infosys.basic.service.IServiceOrderService;
import com.infosys.basic.util.Constants;

//服务需求方
@RequestMapping("/demander")
@Controller
public class DemanderController {
    @Inject
    private IServiceOrderService serviceOrderService;

    @Inject
    private IDemanderService demanderService;

    // 我的服务
    @RequestMapping("/list")
    public String list(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
        Demander demander = demanderService.loadByOpenid(u.getOpenid());
        if (demander == null) {
            model.addAttribute("demander", new Demander());
            // 进入服务需求方-注册页面
            model.addAttribute("fromPath", "2");
            model.addAttribute("openid", u.getOpenid());
            return "demander/register";
        }
        // 进入服务需求方-我的服务
        ServiceOrder order = new ServiceOrder();
        order.setCreateBy(u.getId());
        List<ServiceOrder> orders = serviceOrderService.listDemander(order);
        model.addAttribute("orders", orders);
        return "demander/list";
    }

    // 服务申请
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
        Demander demander = demanderService.loadByOpenid(u.getOpenid());
        if (demander == null) {
            model.addAttribute("demander", new Demander());
            // 进入服务需求方-注册页面
            model.addAttribute("fromPath", "1");
            model.addAttribute("openid", u.getOpenid());
            return "demander/register";
        }
        // 进入服务需求方-服务申请
        model.addAttribute("serviceType", ServiceOrder.ServiceType.values());
        model.addAttribute("categoryType", ServiceOrder.CategoryType.values());
        model.addAttribute("order", new ServiceOrder());
        return "demander/add";
    }

    // 我的申请
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(ServiceOrder order, HttpSession session) {
        User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
        order.setCreateBy(u.getId());
        order.setCreatename(u.getUsername());
        order.setCreateDate(new Date());
        order.setStatus(Constants.T_SERVICE_ORDER_STATUS_NEW);// 新需求
        order.setServiceOrderId(String.valueOf(RandomUtils.nextInt(10000)));
        order.setCategory(order.getCategory());
        order.setServiceType(order.getServiceType());
        serviceOrderService.add(order);
        return "redirect:/demander/list";
    }

    // 进入服务需求方-注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "fromPath", required = false) String fromPath,
            @RequestParam(value = "openid", required = false) String openid, Demander demander, HttpSession session,
            Model model) {
        demander.setOpenid(openid);
        demander.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
        demanderService.add(demander);
        if (fromPath.equals("1")) {
            return "redirect:/demander/add";
        } else if (fromPath.equals("2")) {
            return "redirect:/demander/list";
        }
        return "redirect:/demander/add";
    }

    @RequestMapping(value = "/evaluate/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String update(@PathVariable int id, String evaluate) {
        ServiceOrder tu = serviceOrderService.load(id);
        if (StringUtils.isNotBlank(tu.getEvaluate())) {
            return "您已经评价过";
        }
        tu.setEvaluate(evaluate);
        serviceOrderService.update(tu);
        return "评价成功";
    }

}
