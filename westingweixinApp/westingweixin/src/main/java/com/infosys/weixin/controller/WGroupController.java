package com.infosys.weixin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infosys.weixin.model.WGroup;
import com.infosys.weixin.service.IWGroupService;


@Controller
@RequestMapping("/wgroup")
public class WGroupController {
	@Inject
	private IWGroupService wGroupService;

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("datas",wGroupService.queryAll());
		return "wgroup/list";
	}
	
	@RequestMapping("/query/{openid}")
	public String list(@PathVariable String openid,Model model) {
		model.addAttribute("wg",wGroupService.queryUserGroup(openid));
		return "wgroup/query";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("wg", new WGroup());
		return "wgroup/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(WGroup wg) {
		wGroupService.add(wg);
		return "redirect:/wgroup/list";
	}
}
