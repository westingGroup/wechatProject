package com.infosys.weixin.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infosys.basic.entity.InsideProvider;
import com.infosys.basic.service.IInsideProviderService;
import com.infosys.basic.util.Constants;
import com.infosys.basic.util.JsonUtil;

@RequestMapping("/inside")
@Controller
public class InsideController {
	@Inject
	private IInsideProviderService insideProviderService;

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("insides", insideProviderService.list());
		return "inside/list";
	}

	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.GET)
	public @ResponseBody String add(int id, Model model) {
		InsideProvider u = null;
		if (id > 0) {
			u = insideProviderService.get(id);
		} else {
			u = new InsideProvider();
		}
		return JsonUtil.getInstance().obj2json(u);
	}

	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	public String add(int id, InsideProvider inside) {
		if (id > 0) {
			InsideProvider tu = insideProviderService.load(id);
			tu.setUsername(inside.getUsername());
			tu.setPhone(inside.getPhone());
			tu.setPassword(inside.getPassword());
			insideProviderService.update(tu);
		} else {
			insideProviderService.add(inside);
		}
		return "redirect:/inside/list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/text; charset=utf-8")
	public @ResponseBody String delete(@PathVariable int id) {
		InsideProvider tu = insideProviderService.load(id);
		tu.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_DELETE);
		insideProviderService.update(tu);
		return "删除成功";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "inside/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute(Constants.PC_SESSION_USER);
		session.invalidate();
		return "redirect:/inside/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		InsideProvider u = insideProviderService.login(username, password);
		session.setAttribute(Constants.PC_SESSION_USER, u);// 登录成功
		return "redirect:/inside/list";
	}

	@RequestMapping(value = "/forgetPwd", method = RequestMethod.GET)
	public String forgetPassword(HttpSession session, Model model) {
		InsideProvider u = (InsideProvider) session
				.getAttribute(Constants.PC_SESSION_USER);
		model.addAttribute("inside", u);
		return "inside/forgetPwd";

	}

	@RequestMapping(value = "/forgetPwd", method = RequestMethod.POST)
	public String forgetPassword(HttpSession session, String password) {
		InsideProvider u = (InsideProvider) session
				.getAttribute(Constants.PC_SESSION_USER);
		u.setPassword(password);
		insideProviderService.update(u);
		return "redirect:/inside/list";

	}

}
