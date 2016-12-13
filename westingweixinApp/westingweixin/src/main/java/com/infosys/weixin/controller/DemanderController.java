package com.infosys.weixin.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.dto.ServiceOrderDto;
import com.infosys.basic.dto.ServiceOrderModel;
import com.infosys.basic.entity.Demander;
import com.infosys.basic.entity.ServiceOrder;
import com.infosys.basic.entity.User;
import com.infosys.basic.service.IDemanderService;
import com.infosys.basic.service.IServiceOrderService;
import com.infosys.basic.util.Constants;
import com.infosys.basic.util.DateUtil;
import com.infosys.basic.util.JsonUtil;

//服务需求方
@RequestMapping("/demander")
@Controller
public class DemanderController {
	@Inject
	private IServiceOrderService serviceOrderService;

	@Inject
	private IDemanderService demanderService;

	// 分页
	@RequestMapping(value = "/mydemanders", method = RequestMethod.POST)
	public @ResponseBody String mydemanders(String currentPage,
			String pageSize, String createBy, HttpSession session) {
		JsonUtil jsonUtil = JsonUtil.getInstance();
		ServiceOrderModel demanderSearchModal = new ServiceOrderModel();
		PagerInfo<ServiceOrderDto> demanderPage = new PagerInfo<ServiceOrderDto>();
		if (StringUtils.isBlank(currentPage)) {
			demanderPage.setCurrentPage(1L);
		} else {
			demanderPage.setCurrentPage(Long.valueOf(currentPage));
		}

		if (StringUtils.isBlank(pageSize)) {
			demanderPage.setPageSize(10L);
		} else {
			demanderPage.setPageSize(Long.valueOf(pageSize));
		}

		demanderSearchModal.setCreateBy(createBy);
		demanderSearchModal.setPager(demanderPage);
		PagerInfo<ServiceOrderDto> userResult = serviceOrderService
				.listServiceOrderByKeyword(demanderSearchModal);
		userResult.setTotalPage(userResult.getTotalPages());
		if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
			userResult.setIsCanUp(1);
		}

		if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
			userResult.setIsCanDown(1);
		}
		return jsonUtil.obj2json(userResult);
	}

	// 我的服务
	@RequestMapping("/list")
	public String list(HttpSession session, Model model) {
		User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
		Demander demander = demanderService.loadByOpenid(u.getOpenid());
		if (demander == null) {
			model.addAttribute("demander", new Demander());
			// 进入服务需求方-注册页面
			model.addAttribute("fromPath", "2");
			model.addAttribute("openid", u.getOpenid());
			return "demander/register";
		}
		// 进入服务需求方-我的服务
		// ServiceOrder order = new ServiceOrder();
		// order.setCreateBy(u.getId());
		// List<ServiceOrder> orders = serviceOrderService.listDemander(order);

		ServiceOrderModel demanderSearchModal = new ServiceOrderModel();
		PagerInfo<ServiceOrderDto> demanderPage = new PagerInfo<ServiceOrderDto>();
		demanderPage.setCurrentPage(1L);
		demanderPage.setPageSize(10L);
		demanderSearchModal.setCreateBy(String.valueOf(demander.getId()));
		demanderSearchModal.setPager(demanderPage);
		PagerInfo<ServiceOrderDto> userResult = serviceOrderService
				.listServiceOrderByKeyword(demanderSearchModal);
		userResult.setTotalPage(userResult.getTotalPages());
		if (userResult.getCurrentPage() > 1L) { // 如果大于1表示可以点击上一页
			userResult.setIsCanUp(1);
		}
		if (userResult.getTotalPage() > userResult.getCurrentPage()) { // 表示可以点击下一页
			userResult.setIsCanDown(1);
		}
		model.addAttribute("orders", userResult);
		model.addAttribute("demanderId", demander.getId());
		return "demander/list";
	}

	// 服务申请
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpSession session, Model model) {
		User u = (User) session.getAttribute(Constants.WEIXIN_SESSION_USER);
		Demander demander = demanderService.loadByOpenid(u.getOpenid());
		if (demander == null) {
			model.addAttribute("demander", new Demander());
			// 进入服务需求方-注册页面
			model.addAttribute("fromPath", "1");
			model.addAttribute("openid", u.getOpenid());
			return "demander/register";
		}
		// 进入服务需求方-服务申请
		model.addAttribute("serviceType", ServiceOrder.ServiceType.values());
		model.addAttribute("categoryType", ServiceOrder.CategoryType.values());
		model.addAttribute("order", new ServiceOrder());
		model.addAttribute("demander", demander);
		return "demander/add";
	}

	// 我的申请
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(ServiceOrder order, HttpSession session) {
		order.setCreateDate(new Date());
		order.setStatus(Constants.T_SERVICE_ORDER_STATUS_NEW);// 新需求
		order.setServiceOrderId(String.valueOf(System.currentTimeMillis()));
		order.setCategory(order.getCategory());
		order.setServiceType(order.getServiceType());
		serviceOrderService.add(order);
		return "redirect:/demander/list";
	}

	// 进入服务需求方-注册
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(
			@RequestParam(value = "fromPath", required = false) String fromPath,
			@RequestParam(value = "birth", required = false) String birth,
			@RequestParam(value = "openid", required = false) String openid,
			Demander demander, HttpSession session, Model model) {
		demander.setOpenid(openid);
		demander.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
		demander.setBirthDate(DateUtil.parseDate(birth, "yyyy-MM-dd"));
		demanderService.add(demander);
		if (fromPath.equals("1")) {
			return "redirect:/demander/add";
		} else if (fromPath.equals("2")) {
			return "redirect:/demander/list";
		}
		return "redirect:/demander/add";
	}

	@RequestMapping(value = "/evaluate/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable int id, String evaluate) {
		ServiceOrder tu = serviceOrderService.load(id);
		if (StringUtils.isNotBlank(tu.getEvaluate())) {
			return "您已经评价过";
		}
		tu.setEvaluate(evaluate);
		serviceOrderService.update(tu);
		return "评价成功";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public @ResponseBody String update(@PathVariable int id, Model model) {
		Demander u = demanderService.get(id);
		return JsonUtil.getInstance().obj2json(u);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public @ResponseBody String update(@PathVariable int id, Demander demander,
			@RequestParam(value = "birth", required = false) String birth) {
		Demander tu = demanderService.load(id);
		tu.setBirthDate(DateUtil.parseDate(birth, "yyyy-MM-dd"));
		tu.setBusiness(demander.getBusiness());
		tu.setCompany(demander.getCompany());
		tu.setLinkname(demander.getLinkname());
		tu.setLinkphone(demander.getLinkphone());
		demanderService.update(tu);
		return "更新成功";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/text; charset=utf-8")
	public @ResponseBody String delete(@PathVariable int id) {
		Demander tu = demanderService.load(id);
		tu.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_DELETE);
		demanderService.update(tu);
		return "删除成功";
	}

}
