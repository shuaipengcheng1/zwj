package com.misaka.custom.Web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.misaka.Domain.User;
import com.misaka.InterFace.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Reference(interfaceClass = LoginService.class, timeout = 30000, version = "1.0.0")
    LoginService loginService;

    //    登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map Login(HttpServletResponse response, HttpServletRequest request, String password, String username) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        Map state = new HashMap();
        User user = loginService.Login(username, password);
        if (user != null) {
//            存储session
            request.getSession().setAttribute("user", user);
            request.getSession().setMaxInactiveInterval(30000);
            state.put("message", "成功");
            state.put("state", true);
            return state;
        } else {
            state.put("message", "失败");
            state.put("state", false);
            return state;
        }

    }

    //    注册
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Map Submit( HttpServletResponse response, String password, String username, String url) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if(url==null){
            url="";
        }


        int i = loginService.Submit(username, password, url);

        Map state = new HashMap();

        if (i != 0) {
            if (i == -1) {
                state.put("message", "用户名重复");
                state.put("state", false);
                return state;
            }
            state.put("message", "注册成功");
            state.put("state", true);
            return state;
        } else {
            state.put("message", "注册失败");
            state.put("state", false);
            return state;
        }
    }
}
