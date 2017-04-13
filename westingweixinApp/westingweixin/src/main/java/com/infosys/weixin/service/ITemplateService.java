package com.infosys.weixin.service;

import com.infosys.weixin.model.WxTemplate;

public interface ITemplateService {
    public void sendTemplateMessage(WxTemplate template);
}
