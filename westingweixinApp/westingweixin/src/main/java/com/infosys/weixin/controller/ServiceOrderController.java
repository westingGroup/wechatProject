package com.infosys.weixin.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.service.IServiceOrderService;

@RequestMapping("/serviceOrder")
@Controller
public class ServiceOrderController {
	@Inject
	private IServiceOrderService serviceOrderService;
	
	@RequestMapping("/list")
	public String list(Model model) {
	    List<ServiceOrder> orders = serviceOrderService.listDemander(new ServiceOrder());
		model.addAttribute("orders", orders);
		return "serviceOrder/list";	
	}
	
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
	    ServiceOrder order = serviceOrderService.load(id);
		model.addAttribute("order",order);
		return "serviceOrder/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,ServiceOrder order) {
	    ServiceOrder sorder = serviceOrderService.load(id);
	    //sorder.setStatus(1);
	    sorder.setRemark1(order.getRemark1());
		serviceOrderService.update(sorder);
		return "redirect:/serviceOrder/list";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
	    serviceOrderService.delete(id);
		return "redirect:/serviceOrder/list";
	}
	
	
}
