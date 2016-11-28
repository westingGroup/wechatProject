package com.infosys.weixin.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infosys.course.util.JsonUtil;
import com.infosys.weixin.kit.WeixinBasicKit;
import com.infosys.weixin.model.WeixinFinalValue;


@Service("wqrService")
@Transactional
public class WqrService implements IWqrService {

	@Override
	public String loadTicketByBaseQr(int snum) {
		try {
			String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.QR_GET);
			String json = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+snum+"}}}";
			String rjson = WeixinBasicKit.sendJsonPost(url, json);
			if(WeixinBasicKit.checkRequestSucc(json)) {
				return JsonUtil.getMapper().readTree(rjson).get("ticket").asText();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String loadTicketByTempQr(int snum) {
		try {
			String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.QR_GET);
			String json = "{\"expire_seconds\": 180, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+snum+"}}}";
			String rjson = WeixinBasicKit.sendJsonPost(url, json);
			if(WeixinBasicKit.checkRequestSucc(json)) {
				return JsonUtil.getMapper().readTree(rjson).get("ticket").asText();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
