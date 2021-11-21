package com.misaka.custom.Web.Handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        判断session
      Object session=  request.getSession().getAttribute("user");
      if(session!=null){
        return true;
      }else {
          response.getWriter().write("please login first!!");
          return false;
      }
    }
}
