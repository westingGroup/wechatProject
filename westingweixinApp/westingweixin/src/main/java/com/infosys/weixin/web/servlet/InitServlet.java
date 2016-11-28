package com.infosys.weixin.web.servlet;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.infosys.weixin.kit.WeixinBasicKit;


public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static WebApplicationContext wc;
	private static String realpath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//初始化spring的工厂
		wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		realpath = config.getServletContext().getRealPath("");
		BeanFactoryContext.setWc(wc);
		WeixinBasicKit.setWeixinContext();
	}
	

	public static String getRealpath() {
		return realpath;
	}
	
	public static WebApplicationContext getWc() {
		return wc;
	}

}