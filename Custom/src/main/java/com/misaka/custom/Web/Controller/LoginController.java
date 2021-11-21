package com.misaka.custom.Web.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.misaka.Domain.User;
import com.misaka.InterFace.LoginService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Reference(interfaceClass = LoginService.class, timeout = 30000, version = "1.0.0")
    LoginService loginService;

    //    登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map Login(HttpServletResponse response, HttpServletRequest request, String password, String username) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");


        Map state = new HashMap();
        User user = loginService.Login(username, password);
        if (user != null) {

//            存储session
//            ResponseCookie cookie = ResponseCookie.from("myCookie2", "myCookieValue") // key & value
//                    .httpOnly(true)		// 禁止js读取
//                    .secure(true)		// 在http下也传输
//                    .domain("395789ob20.zicp.vip")// 域名
//                    .path("/")			// path
//                    .maxAge(Duration.ofSeconds(30))	// 1个小时候过期
//                    .sameSite("none")	// 大多数情况也是不发送第三方 Cookie，但是导航到目标网址的 Get 请求除外
//                    .build()
//                    ;

//            // 设置Cookie Header
//            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());


            request.getSession().setAttribute("user", user);
            request.getSession().setMaxInactiveInterval(30000);
            state.put("message", "成功");
            state.put("state", true);
            System.out.println(request.getSession().isNew());
            return state;
        } else {
            state.put("message", "失败");
            state.put("state", false);
            return state;
        }

    }

    //    注册
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Map Submit(HttpServletResponse response,HttpServletRequest request, String password, String username, String url) {
        response.setHeader("Access-Control-Allow-Credentials", "true");//允许携带cookie

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        判断是否为空
        if (url == null) {
            url = "";
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

    @RequestMapping(value = "/home")
    public Object home(HttpServletRequest request) {
//        从session中获取user对象
        User user = (User) request.getSession().getAttribute("user");
//        返回用户数据给前端
        return user;


    }
}
