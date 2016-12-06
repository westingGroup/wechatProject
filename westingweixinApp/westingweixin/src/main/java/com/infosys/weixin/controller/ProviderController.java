package com.infosys.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infosys.basic.dto.ServiceOrderDto;
import com.infosys.basic.entity.Apply;
import com.infosys.basic.entity.Provider;
import com.infosys.basic.entity.User;
import com.infosys.basic.service.IApplyService;
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

    @Inject
    private IApplyService applyService;
    

    // 我的服务
    @RequestMapping("/list")
    public String list(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
        Provider provider = providerService.loadByOpenid(u.getOpenid());
        if (provider == null) {
            model.addAttribute("provider", new Provider());
            // 进入服务供应商-注册页面
            model.addAttribute("fromPath", "2");
            model.addAttribute("openid", u.getOpenid());
            return "provider/register";
        }
        // 进入服务供应商-我的服务 (未申领；别人已申领待分配)
        List<ServiceOrderDto> orders = serviceOrderService.listProviderServiceOrder(u.getOpenid());
        model.addAttribute("orders", orders);
        return "provider/list";
    }

    // 服务认领
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
        Provider provider = providerService.loadByOpenid(u.getOpenid());
        if (provider == null) {
            model.addAttribute("provider", new Provider());
            // 进入服务供应商-注册页面
            model.addAttribute("fromPath", "1");
            model.addAttribute("openid", u.getOpenid());
            return "provider/register";
        }
        // 进入服务供应商-认领页面
        List<ServiceOrderDto> orders = serviceOrderService.listProviderServiceOrder(u.getOpenid());
        model.addAttribute("orders", orders);
        model.addAttribute("apply", new Apply());
        model.addAttribute("provider", provider);
        return "provider/add";
    }

    // 认领
    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String add(@PathVariable int id,Apply apply,@RequestParam(value = "providerId", required = false) String providerId,
            @RequestParam(value = "providerName", required = false) String providerName) {
        apply.setApplyBy(Integer.parseInt(providerId));//外部人员
        apply.setApplyname(providerName);
        apply.setApplyDate(new Date());
        apply.setsId(id);
        applyService.add(apply);
        return "您已经认领成功";
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
