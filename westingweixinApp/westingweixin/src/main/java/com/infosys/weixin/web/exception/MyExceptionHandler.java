package com.infosys.weixin.web.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);

        // 根据不同错误转向不同页面
        if (ex instanceof TimeoutException) {
            model.put("error", ex.getMessage());
            return new ModelAndView("error/error-timeout", model);
        } else if (ex instanceof BusinessException) {
            model.put("error", ex.getMessage());
            return new ModelAndView("error/error-business", model);
        } else if (ex instanceof RuntimeException) {
            model.put("error", ex.getMessage());
            return new ModelAndView("error/error-business", model);
        } else if (ex instanceof Exception) {
            return new ModelAndView("error/error-parameter", model);
        } else {
            return new ModelAndView("error/error", model);
        }
    }

}