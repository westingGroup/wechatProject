package com.infosys.weixin.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.util.JsonUtil;
import com.infosys.weixin.kit.SecurityKit;
import com.infosys.weixin.kit.WeixinBasicKit;
import com.infosys.weixin.model.WeixinFinalValue;

@Service("kfService")
@Transactional
public class KfService implements IKfService {

	@Override
	public void add(String account, String nickname, String password) {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.KF_ADD);
		Map<String,String> maps = new HashMap<String,String>();
		maps.put("kf_account", account+"@gh_009926e0d568");
		maps.put("nickname", nickname);
		maps.put("password", SecurityKit.md5(password));
		String j = JsonUtil.getInstance().obj2json(maps);
		System.out.println(j);
		String json = WeixinBasicKit.sendJsonPost(url, j);
		System.out.println("-------Kf---"+json);
	}

}
