package com.infosys.weixin.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infosys.course.util.JsonUtil;
import com.infosys.weixin.kit.WeixinBasicKit;
import com.infosys.weixin.model.WUser;
import com.infosys.weixin.model.WeixinFinalValue;
import com.infosys.weixin.web.servlet.WeixinContext;

@Service("wUserService")
@Transactional
public class WUserService implements IWUserService {

	@Override
	public WUser queryByOpenid(String openid) {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.USER_QUERY);
		url = url.replace("OPENID", openid);
		String json = WeixinBasicKit.sendGet(url);
		System.out.println(json);
		return (WUser)JsonUtil.getInstance().json2obj(json, WUser.class);
	}

	@Override
	public String queryOpenidByCode(String code)  {
		try {
			String url = WeixinFinalValue.AUTH_GET_OID;
			url = url.replace("APPID", WeixinContext.getInstance().getAppId())
			   .replace("SECRET",WeixinContext.getInstance().getAppSecurt())
			   .replace("CODE", code);
			String json = WeixinBasicKit.sendGet(url);
			String openid = JsonUtil.getMapper().readTree(json).get("openid").asText();
			return openid;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
