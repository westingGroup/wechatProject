package com.infosys.weixin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.basic.util.JsonUtil;
import com.infosys.weixin.kit.WeixinBasicKit;
import com.infosys.weixin.model.WeixinFinalValue;
import com.infosys.weixin.model.WxTemplate;

@Service("templateService")
@Transactional
public class TemplateService implements ITemplateService {


    @Override
    public void sendTemplateMessage(WxTemplate template) {
        String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.SEND_TEMPLATE_MSG);
        String j = JsonUtil.getInstance().obj2json(template);
        System.out.println(j);
        String json = WeixinBasicKit.sendJsonPost(url, j);
        System.out.println("-------template---" + json);
    }

}
