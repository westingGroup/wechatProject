package com.infosys.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.entity.User;
import com.infosys.basic.service.IServiceOrderService;
import com.infosys.basic.service.IUserService;

//服务需求方
@RequestMapping("/demander")
@Controller
public class DemanderController {
	@Inject
	private IUserService userService;
    @Inject
    private IServiceOrderService serviceOrderService;
	
	//我的服务
	@RequestMapping("/list")
	public String list(HttpSession session,Model model) {
	    User u = (User)session.getAttribute("user");
        if(u.getDemander()==0) {
            model.addAttribute("user", u);
            //进入服务需求方-注册页面
            return "demander/update";
        }
        //进入服务需求方-我的服务
        ServiceOrder order = new ServiceOrder();
        order.setCreateBy(u.getId());
        List<ServiceOrder> orders = serviceOrderService.list(order);
        model.addAttribute("orders", orders);
        return "demander/list"; 
	}
	
	//服务申请
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpSession session,Model model) {
	    User u = (User)session.getAttribute("user");
        if(u.getDemander()==0) {
            model.addAttribute("user", u);
            //进入服务需求方-注册页面
            return "demander/update";
        }
        //进入服务需求方-服务申请
        model.addAttribute("serviceType", ServiceOrder.ServiceType.values());  
        model.addAttribute("categoryType", ServiceOrder.CategoryType.values());  
        model.addAttribute("order", new ServiceOrder());
		return "demander/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(ServiceOrder order,HttpSession session) {
	    User u = (User)session.getAttribute("user");
        order.setCreateBy(u.getId());
        order.setCreatename(u.getUsername());
        order.setCreateDate(new Date());
        order.setStatus(0);//新需求
        order.setServiceOrderId(String.valueOf(RandomUtils.nextInt(10000)));
        order.setCategory(ServiceOrder.CategoryType.valueOf(order.getCategory()).getInfo());
        order.setServiceType(ServiceOrder.ServiceType.valueOf(order.getServiceType()).getInfo());
        serviceOrderService.add(order);
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
        tu.setDemander(1);
        userService.update(tu);
        session.setAttribute("user", tu);
        model.addAttribute("serviceType", ServiceOrder.ServiceType.values());  
        model.addAttribute("categoryType", ServiceOrder.CategoryType.values());  
        model.addAttribute("order", new ServiceOrder());
        return "redirect:/demander/add";
    }
	
	@RequestMapping(value="/evaluate/{id}",method=RequestMethod.POST)
	@ResponseBody
    public String update(@PathVariable int id,String evaluate) {
	    ServiceOrder tu = serviceOrderService.load(id);
	    if(StringUtils.isNotBlank(tu.getEvaluate())){
	        return "您已经评价过";
	    }
        tu.setEvaluate(evaluate);
        serviceOrderService.update(tu);
        return "评价成功";
    }
    
    
}
