package com.infosys.weixin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infosys.course.entity.WeixinMenu;
import com.infosys.course.service.IWeixinMenuService;
import com.infosys.weixin.service.IWMenuService;

@RequestMapping("/weixinMenu")
@Controller
public class WeixinMenuController {
	@Inject
	private IWeixinMenuService weixinMenuService;
	@Inject
	private IWMenuService wMenuService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("menus", weixinMenuService.listAll());
		model.addAttribute("wmds", weixinMenuService.generateWeixinMenuDto());
		return "weixinMenu/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("menu", new WeixinMenu());
		return "weixinMenu/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(WeixinMenu menu) {
		weixinMenuService.add(menu);
		return "redirect:/weixinMenu/list";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		model.addAttribute("menu", weixinMenuService.load(id));
		return "weixinMenu/add";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,WeixinMenu menu) {
		WeixinMenu twm = weixinMenuService.load(id);
		twm.setContent(menu.getContent());
		twm.setMenuKey(twm.getMenuKey());
		twm.setName(menu.getName());
		twm.setRespType(menu.getRespType());
		twm.setType(menu.getType());
		twm.setUrl(menu.getUrl());
		twm.setRespType(menu.getRespType());
		twm.setPid(menu.getPid());
		weixinMenuService.update(twm);
		return "redirect:/weixinMenu/list";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id,Model model) {
		weixinMenuService.delete(id);
		return "redirect:/weixinMenu/list";
	}
	
	@RequestMapping("/queryPublishMenu")
	public String queryPublish(Model model) {
		model.addAttribute("ms", wMenuService.queryMenu());
		return "weixinMenu/publish";
	}
	
	@RequestMapping("/publishMenu")
	public String publishMenu() {
		wMenuService.publishMenu();
		return "redirect:/weixinMenu/queryPublishMenu";
	}
}
