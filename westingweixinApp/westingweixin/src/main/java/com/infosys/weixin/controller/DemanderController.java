package com.infosys.weixin.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infosys.basic.entity.User;
import com.infosys.basic.service.IUserService;

//服务需求方
@RequestMapping("/demander")
@Controller
public class DemanderController {
	@Inject
	private IUserService userService;
	
	//我的服务
	@RequestMapping("/list")
	public String list(HttpSession session,Model model) {
	    User u = (User)session.getAttribute("user");
        if(u.getBind()==0) {
            model.addAttribute("user", u);
            //进入服务需求方-注册页面
            return "demander/update";
        }
        //进入服务需求方-我的服务
        model.addAttribute("users", userService.list());
        return "demander/list"; 
	}
	
	//服务申请
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpSession session,Model model) {
	    User u = (User)session.getAttribute("user");
        if(u.getBind()==0) {
            model.addAttribute("user", u);
            //进入服务需求方-注册页面
            return "demander/update";
        }
        //进入服务需求方-服务申请
        model.addAttribute("user", u);
		return "demander/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(User user,HttpSession session,Model model) {
		user.setBind(0);
		userService.add(user);
		return "redirect:/demander/list";
	}
	
	//进入服务需求方-注册
	@RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(User user,HttpSession session,Model model) {
	    User tu = userService.load(user.getId());
        tu.setLinkname(user.getLinkname());
        tu.setLinkphone(user.getLinkphone());
        tu.setBusiness(user.getBusiness());
        tu.setCompany(user.getCompany());
        tu.setBind(1);
        userService.update(tu);
        session.setAttribute("user", tu);
        return "redirect:/demander/add";
    }
    
    
}
