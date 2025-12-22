package com.hebei.server;

import com.hebei.pojo.User;

public interface UserServer {

    /*
    * 用户注册
    * */
    void register(String username, String password);

    /*
    * 判断用户名是否存在
    * */
    User findByUsername(String username);
}
