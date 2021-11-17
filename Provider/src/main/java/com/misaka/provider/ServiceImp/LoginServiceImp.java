package com.misaka.provider.ServiceImp;

import com.alibaba.dubbo.config.annotation.Service;
import com.misaka.Domain.User;
import com.misaka.InterFace.LoginService;
import com.misaka.provider.Mapper.LoginMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Service(interfaceClass = LoginService.class, timeout = 30000, version = "1.0.0")
public class LoginServiceImp implements LoginService {
    //    dao
    @Resource
    LoginMapper loginMapper;

    @Override
    public User Login(String username, String password) {
        User user = loginMapper.Login(username, password);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }

    @Override
    public int Submit(String username, String password, String url) {
//        判断是否重名
        System.out.println(loginMapper);
        User user = loginMapper.Same(username);
        if (user != null) {
//            弹出
            return -1;
        }

//       判断是否传入了头像
        if (url.equals("")) {
            System.out.println("无头像");
//
            int code = loginMapper.Submit(username, password);
            return code;
        } else {
            System.out.println("头像");
            int code = loginMapper.Icon_Submit(username, password, url);
            return code;
        }

    }
}

