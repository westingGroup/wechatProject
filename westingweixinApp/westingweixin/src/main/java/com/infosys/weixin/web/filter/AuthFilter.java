package com.infosys.weixin.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infosys.basic.entity.InsideProvider;
import com.infosys.basic.entity.User;
import com.infosys.basic.util.CheckMobile;
import com.infosys.basic.util.Constants;



public class AuthFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)req;
		HttpServletResponse httpResp = (HttpServletResponse)resp;
		String url = httpReq.getRequestURI();
		if(url.indexOf("resources")>0||url.indexOf("login")>0||url.indexOf("/wreceive")>0||url.indexOf("logout")>0) {
			chain.doFilter(httpReq, httpResp);
			return;
		} else {
		    String userAgent =  httpReq.getHeader( "User-Agent" ).toLowerCase(); 
		    boolean isFromMobile = CheckMobile.check(userAgent); 
		    if(isFromMobile){  
                System.out.println("移动端访问");
                User u = (User)httpReq.getSession().getAttribute(Constants.WEIXIN_SESSION_USER);
                if(u==null) {
                    httpResp.sendRedirect(httpReq.getContextPath()+Constants.LOGIN);
                    return;
                }
            } else {  
                System.out.println("pc端访问");
                InsideProvider u = (InsideProvider)httpReq.getSession().getAttribute(Constants.PC_SESSION_USER);
                //todo anne 测试
                User user = new User();
                user.setOpenid("oE8m_wrXmKxdD2L-j9skZahf5ABI");
                user.setId(2);
                user.setNickname("anne");
                user.setStatus(1);
                httpReq.getSession().setAttribute(Constants.WEIXIN_SESSION_USER,user);
                if(u==null) {
                    httpResp.sendRedirect(httpReq.getContextPath()+Constants.LOGIN);
                    return;
                }
            }  
			
			chain.doFilter(httpReq, httpResp);
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		
	}

}
