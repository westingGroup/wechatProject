package com.infosys.weixin.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.entity.User;
import com.infosys.basic.service.IServiceOrderService;
import com.infosys.basic.service.IUserService;
//服务提供方
@RequestMapping("/provider")
@Controller
public class ProviderController {
	@Inject
	private IUserService userService;
	@Inject
    private IServiceOrderService serviceOrderService;
	//我的服务
	@RequestMapping("/list")
	public String list(HttpSession session,Model model) {
		User u = (User)session.getAttribute("user");
        if(u.getProvider()==0) {
            model.addAttribute("user", u);
            //进入服务供应商-注册页面
            model.addAttribute("fromPath", "2");
            return "provider/update";
        }
        //进入服务供应商-我的服务
        ServiceOrder order = new ServiceOrder();
        order.setApplyBy(u.getId());
        List<ServiceOrder> orders = serviceOrderService.listProvider(order);
        model.addAttribute("orders", orders);
        return "provider/list"; 
	}
	
	//服务认领
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpSession session,Model model) {
	    User u = (User)session.getAttribute("user");
        if(u.getProvider()==0) {
            model.addAttribute("user", u);
            //进入服务供应商-注册页面
            model.addAttribute("fromPath", "1");
            return "provider/update";
        }
        //进入服务供应商-认领页面
        ServiceOrder order = new ServiceOrder();
        order.setApplyBy(u.getId());
        List<ServiceOrder> orders = serviceOrderService.listProvider(order);
        model.addAttribute("orders", orders);
        return "provider/add";
	}
	
	//认领
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(User user) {
		return "redirect:/provider/list";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@RequestParam(value = "fromPath", required = false) String fromPath,User user,HttpSession session,Model model) {
        User tu = userService.load(user.getId());
        tu.setLinkname(user.getLinkname());
        tu.setLinkphone(user.getLinkphone());
        tu.setBusiness(user.getBusiness());
        tu.setCompany(user.getCompany());
        tu.setQualification(user.getQualification());
        tu.setProvider(1);
        userService.update(tu);
        session.setAttribute("user", tu);
        if(fromPath.equals("1")){
            return "redirect:/provider/add";
        }else if(fromPath.equals("2")){
            return "redirect:/provider/list";
        }
        return "redirect:/provider/add";
    }
	
	
}
