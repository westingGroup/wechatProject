package com.infosys.weixin.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.infosys.basic.entity.Provider;
import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.entity.User;
import com.infosys.basic.service.IProviderService;
import com.infosys.basic.service.IServiceOrderService;
import com.infosys.basic.util.Constants;

//服务提供方
@RequestMapping("/provider")
@Controller
public class ProviderController {
    @Inject
    private IServiceOrderService serviceOrderService;

    @Inject
    private IProviderService providerService;

    // 我的服务
    @RequestMapping("/list")
    public String list(HttpSession session, Model model) {
        User u = (User) session.getAttribute("user");
        Provider provider = providerService.loadByOpenid(u.getOpenid());
        if (provider == null) {
            model.addAttribute("provider", new Provider());
            // 进入服务供应商-注册页面
            model.addAttribute("fromPath", "2");
            model.addAttribute("openid", u.getOpenid());
            return "provider/register";
        }
        // 进入服务供应商-我的服务
        ServiceOrder order = new ServiceOrder();
        List<ServiceOrder> orders = serviceOrderService.listProvider(order);
        model.addAttribute("orders", orders);
        return "provider/list";
    }

    // 服务认领
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constants.WEIXIN_USER);
        Provider provider = providerService.loadByOpenid(u.getOpenid());
        if (provider == null) {
            model.addAttribute("provider", new Provider());
            // 进入服务供应商-注册页面
            model.addAttribute("fromPath", "1");
            model.addAttribute("openid", u.getOpenid());
            return "provider/register";
        }
        // 进入服务供应商-认领页面
        ServiceOrder order = new ServiceOrder();
        List<ServiceOrder> orders = serviceOrderService.listProvider(order);
        model.addAttribute("orders", orders);
        return "provider/add";
    }

    // 认领
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user) {
        return "redirect:/provider/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(value = "fromPath", required = false) String fromPath,
            @RequestParam(value = "openid", required = false) String openid, Provider provider, HttpSession session,
            Model model) {
        provider.setOpenid(openid);
        provider.setStatus(1);
        providerService.add(provider);
        if (fromPath.equals("1")) {
            return "redirect:/provider/add";
        } else if (fromPath.equals("2")) {
            return "redirect:/provider/list";
        }
        return "redirect:/provider/add";
    }

}
