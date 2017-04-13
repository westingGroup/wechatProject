package com.infosys.weixin.web.servlet;

import com.infosys.weixin.json.AccessToken;

public class WeixinContext {
	private String appId;
	private String appSecurt;
	private String baseUrl;
	private String token;
	private String templateProvider;
	private String templateDemander;
    private String templateRegisterAudit;
	private AccessToken accessToken;
	private static WeixinContext wc;
	
	private WeixinContext(){}
	public static WeixinContext getInstance() {
		if(wc==null) wc = new WeixinContext();
		return wc;
	}
	
	
	public AccessToken getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecurt() {
		return appSecurt;
	}
	public void setAppSecurt(String appSecurt) {
		this.appSecurt = appSecurt;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
    public String getTemplateProvider() {
        return templateProvider;
    }
    public void setTemplateProvider(String templateProvider) {
        this.templateProvider = templateProvider;
    }
    public String getTemplateDemander() {
        return templateDemander;
    }
    public void setTemplateDemander(String templateDemander) {
        this.templateDemander = templateDemander;
    }
    public String getTemplateRegisterAudit() {
        return templateRegisterAudit;
    }
    public void setTemplateRegisterAudit(String templateRegisterAudit) {
        this.templateRegisterAudit = templateRegisterAudit;
    }
}
