package com.infosys.weixin.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infosys.basic.entity.User;
import com.infosys.basic.entity.WeixinQr;
import com.infosys.basic.service.IUserService;
import com.infosys.basic.service.IWeixinQrService;
import com.infosys.weixin.service.IKfService;

@RequestMapping("/user")
@Controller
public class UserController {
	@Inject
	private IUserService userService;
	
	@Inject
	private IWeixinQrService weixinQrService;
	
	@Inject
	private IKfService kfService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", userService.list());
		return "user/list";	
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(User user) {
		user.setBind(0);
		userService.add(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		User u = userService.load(id);
		model.addAttribute("user",u);
		return "user/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,User user) {
		User tu = userService.load(id);
		tu.setNickname(user.getNickname());
		tu.setPassword(user.getPassword());
		tu.setUsername(user.getUsername());
		userService.update(tu);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(HttpSession session) {
	    session.removeAttribute("user");
	    session.invalidate();
        return "redirect:/user/login";
    }
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,HttpSession session) {
		User u = userService.login(username, password);
		session.setAttribute("user", u);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/bindNewUser",method=RequestMethod.GET)
	public String bindNewUser(Model model,HttpSession session,HttpServletResponse resp) throws IOException {
		User u = (User)session.getAttribute("user");
		if(u==null) resp.sendRedirect("/user/login");
		System.out.println(u.getBind());
		if(u.getBind()==1) return "redirect:/user/list";
		model.addAttribute("user", u);
		return "user/bindNewUser";
	}
	
	
	@RequestMapping(value="/bindNewUser",method=RequestMethod.POST)
	public String bindNewUser(String username,String password,HttpSession session,HttpServletResponse resp) throws IOException {
		User u = (User)session.getAttribute("user");
		if(u==null) resp.sendRedirect("/user/login");
		
		System.out.println(username);
		User tu = userService.load(u.getId());
		tu.setBind(1);
		tu.setUsername(username);
		tu.setPassword(password);
		userService.update(tu);
		session.setAttribute("user", tu);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/bindExistUser",method=RequestMethod.GET)
	public String bindExistUser(Model model,HttpSession session,HttpServletResponse resp) throws IOException {
		User u = (User)session.getAttribute("user");
		if(u==null) resp.sendRedirect("/user/login");
		if(u.getBind()==1) return "redirect:/user/list";
		return "user/bindExistUser";
	}
	
	@RequestMapping(value="/bindExistUser",method=RequestMethod.POST)
	public String bindExistUser(String username,String password,Model model,HttpSession session,HttpServletResponse resp) throws IOException {
		User u = (User)session.getAttribute("user");
		if(u==null) resp.sendRedirect("/user/login");
		User tu = userService.loadByUsername(username);
		if(tu.getPassword().equals(password)) {
			tu.setBind(1);
			tu.setImgUrl(u.getImgUrl());
			tu.setSex(u.getSex());
			tu.setStatus(1);
			tu.setNickname(u.getNickname());
			tu.setOpenid(u.getOpenid());
			userService.update(tu);
			userService.delete(u.getId());
			session.setAttribute("user", tu);
		} else {
			throw new RuntimeException("原始的密码输入出错!");
		}
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/forgetPwd",method=RequestMethod.GET)
	public String forgetPassword(HttpSession session,Model model) {
		User u = (User)session.getAttribute("user");
		if(u.getBind()==0) {
			throw new RuntimeException("请绑定用户之后才能在微信中修改密码！");
		}
		model.addAttribute("user", u);
		return "user/forgetPwd";
		
	}
	
	@RequestMapping(value="/forgetPwd",method=RequestMethod.POST)
	public String forgetPassword(HttpSession session,String password) {
		User u = (User)session.getAttribute("user");
		if(u.getBind()==0) {
			throw new RuntimeException("请绑定用户之后才能在微信中修改密码！");
		}
		u.setPassword(password);
		userService.update(u);
		return "redirect:/user/list";
		
	}
	
	@RequestMapping(value="/qrlogin")
	public String qrlogin(Model model) {
		WeixinQr wq = generateLoginQr();
		weixinQrService.add(wq);
		model.addAttribute("wq", wq);
		return "user/qrlogin";
	}
	
	@RequestMapping(value="checkqrlogin")
	public void checkqrlogin(int snum,HttpSession session,HttpServletResponse resp) throws IOException {
		WeixinQr wq = weixinQrService.loadBySnum(snum);
		resp.setContentType("text/txt");
		if(wq.getQrData()!=null&&wq.getStatus()==1) {
			String openid = wq.getQrData();
			User u = userService.loadByOpenid(openid);
			session.setAttribute("user", u);
			resp.getWriter().println(1);
		} else {
			resp.getWriter().println(0);
		}
		
	}

	private WeixinQr generateLoginQr() {
		WeixinQr wq = new WeixinQr();
		wq.setName("扫码登录");
		wq.setMsg("扫码登录");
		wq.setSnum(RandomUtils.nextInt()+(WeixinQr.MAX_BASE_SNUM+1));
		wq.setStatus(0);
		wq.setType(WeixinQr.TEMP_LOGIN);
		return wq;
	}
	
	@RequestMapping(value="addKf",method=RequestMethod.GET)
	public String addKf() {
		return "user/addKf";
	}
	
	@RequestMapping(value="addKf",method=RequestMethod.POST)
	public String addKf(String account,String password,String nickname) {
		kfService.add(account, nickname, password);
		return "user/addKf";
	}
	
	
}
