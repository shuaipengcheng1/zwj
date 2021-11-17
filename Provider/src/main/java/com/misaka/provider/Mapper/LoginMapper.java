package com.misaka.provider.Mapper;

import com.misaka.Domain.User;

public interface LoginMapper {
    //    登录
    User Login(String username, String password);

    //    无头像注册
    int Submit(String username, String password);

    //    头像注册
    int Icon_Submit(String username, String password, String url);

//    查看是否有同名
    User Same(String username);

}
