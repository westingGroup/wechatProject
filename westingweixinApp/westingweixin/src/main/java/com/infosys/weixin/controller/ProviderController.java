package com.infosys.weixin.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infosys.basic.entity.User;
import com.infosys.basic.service.IUserService;
//服务提供方
@RequestMapping("/provider")
@Controller
public class ProviderController {
	@Inject
	private IUserService userService;
	
	//我的服务
	@RequestMapping("/list")
	public String list(HttpSession session,Model model) {
		User u = (User)session.getAttribute("user");
        if(u.getProvider()==0) {
            model.addAttribute("user", u);
            //进入服务供应商-注册页面
            return "provider/update";
        }
        //进入服务供应商-我的服务
        model.addAttribute("users", userService.list());
        return "provider/list"; 
	}
	
	//服务认领
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpSession session,Model model) {
	    User u = (User)session.getAttribute("user");
        if(u.getProvider()==0) {
            model.addAttribute("user", u);
            //进入服务供应商-注册页面
            return "provider/update";
        }
        //进入服务供应商-认领页面
        model.addAttribute("user", u);
        return "provider/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(User user) {
		
		return "redirect:/provider/list";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(User user,HttpSession session,Model model) {
        User tu = userService.load(user.getId());
        tu.setLinkname(user.getLinkname());
        tu.setLinkphone(user.getLinkphone());
        tu.setBusiness(user.getBusiness());
        tu.setCompany(user.getCompany());
        tu.setQualification(user.getQualification());
        tu.setProvider(1);
        userService.update(tu);
        session.setAttribute("user", tu);
        return "redirect:/provider/add";
    }
	
	
}
