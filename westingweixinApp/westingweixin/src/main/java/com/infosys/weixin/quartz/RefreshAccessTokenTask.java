package com.infosys.weixin.quartz;

import org.springframework.stereotype.Component;

import com.infosys.course.util.JsonUtil;
import com.infosys.weixin.json.AccessToken;
import com.infosys.weixin.kit.WeixinBasicKit;
import com.infosys.weixin.model.WeixinFinalValue;
import com.infosys.weixin.web.servlet.WeixinContext;

@Component
public class RefreshAccessTokenTask {
	public void refreshToken() {
		String url = WeixinFinalValue.ACCESS_TOKEN_URL;
		url = url.replaceAll("APPID", WeixinContext.getInstance().getAppId());
		url = url.replaceAll("APPSECRET", WeixinContext.getInstance().getAppSecurt());
		String content = WeixinBasicKit.sendGet(url);
		if(WeixinBasicKit.checkRequestSucc(content)) {
			AccessToken at = (AccessToken)JsonUtil.getInstance().json2obj(content, AccessToken.class);
			WeixinContext.getInstance().setAccessToken(at);
			System.out.println("RefreshAccessTokenTask---access_token:"+at.getAccess_token());
		} else {
			refreshToken();
		}
	}
}
