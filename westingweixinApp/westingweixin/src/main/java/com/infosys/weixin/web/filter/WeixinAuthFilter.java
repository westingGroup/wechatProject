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

import com.infosys.basic.entity.Demander;
import com.infosys.basic.entity.Provider;
import com.infosys.basic.entity.User;
import com.infosys.basic.service.IDemanderService;
import com.infosys.basic.service.IProviderService;
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
		User tu = (User)hRequest.getSession().getAttribute(com.infosys.basic.util.Constants.WEIXIN_SESSION_USER);
//		System.out.println(tu+"------");
		if(tu==null) {
			String agent = hRequest.getHeader("User-Agent");
			System.out.println("agent::::::::::::"+agent);
			if(agent!=null&&agent.toLowerCase().indexOf("micromessenger")>=0) {
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				if(code!=null&&state!=null&&state.equals("1")) {
					//通过Code获取openid来进行授权
				    IWUserService wUserService = (IWUserService) BeanFactoryContext.getService("wUserService");
					String openid = wUserService.queryOpenidByCode(code);
					if(openid!=null) {
					    IUserService userService = (IUserService) BeanFactoryContext.getService("userService");
						User u = userService.loadByOpenid(openid);
						if(u==null) {
							u = wUserService.queryByOpenid(openid).getUser();
							userService.add(u);
						} else {
							if(u.getStatus()==com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
								u.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
								userService.update(u);
							}
							IDemanderService demanderService = (IDemanderService)BeanFactoryContext.getService("demanderService");
							IProviderService providerService = (IProviderService)BeanFactoryContext.getService("providerService");
							Provider per = providerService.loadByOpenid(openid);
                            Demander der = demanderService.loadByOpenid(openid);
                            if (u.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
                                u.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
                                userService.update(u);
                            }
                            if (per != null && per.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
                                per.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
                                providerService.update(per);
                            }
                            if (der != null && der.getStatus() == com.infosys.basic.util.Constants.T_USER_STATUS_DELETE) {
                                der.setStatus(com.infosys.basic.util.Constants.T_USER_STATUS_NORMAL);
                                demanderService.update(der);
                            }
						}
						hRequest.getSession().setAttribute(com.infosys.basic.util.Constants.WEIXIN_SESSION_USER, u);
					}
				} else {
					String path = hRequest.getRequestURL().toString();
					String query = hRequest.getQueryString();
					if(query!=null) {
						path = path+"?"+query;
					}
					//System.out.println("path-----"+path);
					String uri = WeixinFinalValue.AUTH_URL;
					uri = uri.replace("APPID", WeixinContext.getInstance().getAppId())
					   .replace("REDIRECT_URI",URLEncoder.encode(path, "UTF-8"))
					   .replace("SCOPE", "snsapi_base")
					   .replace("STATE", "1");
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
