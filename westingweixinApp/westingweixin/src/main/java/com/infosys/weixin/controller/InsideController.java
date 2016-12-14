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
import com.infosys.weixin.kit.SecurityKit;
import com.infosys.weixin.web.exception.BusinessException;

@RequestMapping("/inside")
@Controller
public class InsideController {
	@Inject
	private IInsideProviderService insideProviderService;

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
	public String add(int id, InsideProvider inside) throws BusinessException {
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
	public String login(HttpSession session) {
		InsideProvider in = (InsideProvider) session
				.getAttribute(com.infosys.basic.util.Constants.PC_SESSION_USER);
		if (in != null)
			return "main";
		return "inside/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute(Constants.PC_SESSION_USER);
		session.invalidate();
		return "redirect:/inside/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session)
			throws BusinessException {
		InsideProvider u = insideProviderService.login(username, password);
		session.setAttribute(Constants.PC_SESSION_USER, u);// 登录成功
		return "main";
	}

	@RequestMapping(value = "/forgetPwd", method = RequestMethod.GET)
	public String forgetPassword(HttpSession session, Model model) {
		InsideProvider u = (InsideProvider) session
				.getAttribute(Constants.PC_SESSION_USER);
		model.addAttribute("inside", u);
		return "inside/forgetPwd";

	}

	@RequestMapping(value = "/forgetPwd", method = RequestMethod.POST)
	public @ResponseBody String forgetPassword(HttpSession session,
			String oldPwd, String newPwd) {
		InsideProvider u = (InsideProvider) session
				.getAttribute(Constants.PC_SESSION_USER);
		if (!SecurityKit.md5(oldPwd).equals(u.getPassword()))
			return "旧密码不正确";
		u.setPassword(SecurityKit.md5(newPwd));
		session.setAttribute(Constants.PC_SESSION_USER, u);
		insideProviderService.update(u);
		return "修改成功";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody String resetPassword(int id) {
		InsideProvider u = insideProviderService.load(id);
		u.setPassword(SecurityKit.md5("123"));
		insideProviderService.update(u);
		return "重置成功";
	}

}
