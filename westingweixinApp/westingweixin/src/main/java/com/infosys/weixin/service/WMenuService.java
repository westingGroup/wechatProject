package com.infosys.weixin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.course.service.IWeixinMenuService;
import com.infosys.course.util.JsonUtil;
import com.infosys.weixin.kit.WeixinBasicKit;
import com.infosys.weixin.model.WeixinFinalValue;
import com.infosys.weixin.model.WeixinMenuDto;

@Service("wMenuService")
@Transactional
public class WMenuService implements IWMenuService {
	@Inject
	private IWeixinMenuService weixinMenuService;

	public void publishMenu() {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.MENU_ADD);
		System.out.println(url);
		List<WeixinMenuDto> wmds = weixinMenuService.generateWeixinMenuDto();
		Map<String,List<WeixinMenuDto>> maps = new HashMap<String,List<WeixinMenuDto>>();
		maps.put("button", wmds);
		String json =  JsonUtil.getInstance().obj2json(maps);
//		System.out.println(json);
		String rel = WeixinBasicKit.sendJsonPost(url,json);
		if(!WeixinBasicKit.checkRequestSucc(rel)) {
			throw new RuntimeException("发布菜单失败："+WeixinBasicKit.getRequestMsg(rel));
		}
	}

	public String queryMenu() {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.MENU_QUERY);
		return WeixinBasicKit.sendGet(url);
	}

}
