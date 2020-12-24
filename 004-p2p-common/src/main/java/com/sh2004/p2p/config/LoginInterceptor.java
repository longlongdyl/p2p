package com.sh2004.p2p.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: springboot
 * @Package: com.sh2004.springboot.config
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/12 14:27
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");

        if (null == user){
            String queryString = request.getQueryString();
            String requestURL = request.getRequestURL()+"";
            String url =  requestURL+"?"+(queryString == null ? "" : queryString);
            response.sendRedirect("/login?url="+url);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
