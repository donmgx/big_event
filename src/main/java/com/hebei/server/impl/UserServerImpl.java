package com.hebei.server.impl;

import com.hebei.mapper.UserMapper;
import com.hebei.pojo.User;
import com.hebei.server.UserServer;
import com.hebei.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServerImpl implements UserServer {
    @Autowired
    private UserMapper userMapper;
    /*
    * 用户注册
    * */
    public void register(String username, String password) {
        //密码加密
        String md5Password = Md5Util.getMD5String(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5Password);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    /*
    * 判断用户名是否存在
    * */
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }


    /*
     * 更新用户信息
     * */
    public void update(User user) {

        userMapper.update(user);
    }
}
