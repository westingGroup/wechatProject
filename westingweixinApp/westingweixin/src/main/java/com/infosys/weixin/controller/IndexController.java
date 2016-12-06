package com.infosys.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("process/registers/approvalRegister");
		return modelAndView;
	}

	@RequestMapping(value = "/dispatchRegister")
	public ModelAndView dispatchRegister() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register/dispatchRegister");
		return modelAndView;
	}

	@RequestMapping(value = "/ordersRegister")
	public ModelAndView ordersRegister() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register/ordersRegister");
		return modelAndView;
	}

	@RequestMapping(value = "/demanderView")
	public ModelAndView demanderView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("demander/view");
		return modelAndView;
	}

	@RequestMapping(value = "/approvalDemand")
	public ModelAndView approvalDemand() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("process/demanders/approvalDemander");
		return modelAndView;
	}
}
