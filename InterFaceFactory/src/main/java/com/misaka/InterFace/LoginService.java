package com.misaka.InterFace;

import com.misaka.Domain.User;

public interface LoginService {
    //    登录
    User Login(String username, String password);

    //    注册
    int Submit(String username, String password,String url);
}
