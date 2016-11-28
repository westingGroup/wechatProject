package com.infosys.weixin.web.filter;


import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infosys.basic.entity.User;
import com.infosys.basic.service.IUserService;
import com.infosys.weixin.model.WeixinFinalValue;
import com.infosys.weixin.service.IWUserService;
import com.infosys.weixin.web.servlet.BeanFactoryContext;
import com.infosys.weixin.web.servlet.WeixinContext;

public class WeixinAuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest)request;
		HttpServletResponse hResponse = (HttpServletResponse)response;
		User tu = (User)hRequest.getSession().getAttribute("user");
//		System.out.println(tu+"------");
		if(tu==null) {
			String agent = hRequest.getHeader("User-Agent");
			System.out.println("agent::::::::::::"+agent);
			//micromessenger是微信客户端，电脑浏览器里没有这个micromessenger，电脑端直接chain.doFilter
			if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
			    //sendRedirect请求到微信服务器，微信服务器返回到的REDIRECT_URI，就是我们的应用的请求地址，filter拦截所有的请求，就进来到了WeixinAuthFilter
			    //微信服务器会带回来认证code和state，state可以自己设置
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				if(code!=null&&state!=null&&state.equals("1")) {
					//通过Code获取openid来进行授权
					IWUserService wUserService = (IWUserService)BeanFactoryContext.getService("wUserService");
					String openid = wUserService.queryOpenidByCode(code);
					if(openid!=null) {
						IUserService userService = (IUserService)BeanFactoryContext.getService("userService");
						User u = userService.loadByOpenid(openid);
						if(u==null) {
							u = wUserService.queryByOpenid(openid).getUser();
							userService.add(u);
						} else {
							if(u.getStatus()==0) {
								u.setStatus(1);
								userService.update(u);
							}
						}
						hRequest.getSession().setAttribute("user", u);
					}
				} else {
					String path = hRequest.getRequestURL().toString();
					String query = hRequest.getQueryString();
					if(query!=null) {
						path = path+"?"+query;
					}
					String uri = WeixinFinalValue.AUTH_URL;
					uri = uri.replace("APPID", WeixinContext.getInstance().getAppId())
					   .replace("REDIRECT_URI",URLEncoder.encode(path, "UTF-8"))
					   .replace("SCOPE", "snsapi_base")
					   .replace("STATE", "1");
					//只能是sendRedirect，因为如果是sendGet(uri)是以当前服务器来请求到微信服务器，会提示请使用微信浏览器 ,sendRedirect先定向到微信服务器
					hResponse.sendRedirect(uri);
//					System.out.println(uri);
					return;
				}
			}
		}
		chain.doFilter(hRequest, hResponse);
	}

	@Override
	public void destroy() {

	}

}
