package com.infosys.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.infosys.course.service.CoreService;
import com.infosys.course.util.SignUtil;

@Controller
public class CoreServletController {
    private static Logger log = LoggerFactory.getLogger(CoreServletController.class);

    @RequestMapping(value = "/coreServlet", method = RequestMethod.GET)
    public ModelAndView doCoreServletGet(HttpServletRequest request, HttpServletResponse response) throws IOException {// 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
        return null;
    }

    @RequestMapping(value = "/coreServlet", method = RequestMethod.POST)
    public void doCoreServletPost(HttpServletRequest request, HttpServletResponse response) throws IOException {// 微信加密签名
        // 消息的接收、处理、响应

        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            // 调用核心业务类接收消息、处理消息
            String respMessage = CoreService.processRequest(request);
            // 响应消息
            PrintWriter out = response.getWriter();
            out.print(respMessage);
            out.close();
        }
    }
}
