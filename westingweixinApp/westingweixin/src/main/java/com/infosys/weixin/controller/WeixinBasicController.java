package com.infosys.weixin.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infosys.basic.util.BasicKit;
import com.infosys.weixin.kit.SecurityKit;
import com.infosys.weixin.kit.WeixinMessageKit;
import com.infosys.weixin.web.servlet.WeixinContext;

@Controller
public class WeixinBasicController {

	@RequestMapping(value="/wreceive",method=RequestMethod.GET)
	public void handlerGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		String[] arrs = {WeixinContext.getInstance().getToken(),nonce,timestamp};
		Arrays.sort(arrs);
		StringBuffer sb = new StringBuffer();
		for(String a:arrs) {
			sb.append(a);
		}
		String sha1 = SecurityKit.sha1(sb.toString());
//		System.out.println(sha1.equals(signature));
		if(sha1.equals(signature)) {
			resp.getWriter().println(echostr);
		}
	}
	
	@RequestMapping(value="/wreceive",method=RequestMethod.POST)
	public void handlerPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/xml;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		String rel = WeixinMessageKit.handlerReceiveMsg(req);
		System.out.println("--------rel:"+rel);
		if(!BasicKit.isEmpty(rel)) {
			resp.getWriter().write(rel);
		}
	}
	
	
	
}
