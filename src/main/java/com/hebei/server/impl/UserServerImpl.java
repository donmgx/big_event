package com.hebei.server.impl;

import com.hebei.constant.CommenConstant;
import com.hebei.mapper.UserMapper;
import com.hebei.pojo.Result;
import com.hebei.pojo.User;
import com.hebei.server.UserServer;
import com.hebei.util.Md5Util;
import com.hebei.util.ThreadLocalUtil;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Builder
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


    /*
     * 修改密码
     * */
    public void updatePwd(String oldPwd, String newPwd, String rePwd) throws Exception {
        if (oldPwd.isEmpty() || newPwd.isEmpty() || rePwd.isEmpty()) {
            throw new Exception(CommenConstant.PASSWORD_NOT_EMPTY);

        }

        if (!newPwd.equals(rePwd)) {
            throw new Exception(CommenConstant.PASSWORD_DO_NOT_MATCH);
        }

        //判断新密码与原密码是否一致
        if (newPwd.equals(oldPwd)) {
            throw new Exception(CommenConstant.OLD_PASSWORD_AND_NEW_PASSWORD_NOT_ALLOW_SAME);
        }

        //判断原密码是否正确
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userMapper.findByUsername(username);
        //对原密码进行加密
        String oldPassword = Md5Util.getMD5String(oldPwd);
        if (!user.getPassword().equals(oldPassword)) {
            throw new Exception(CommenConstant.OLD_PASSWORD_ERROR);
        }

        //进行密码修改
        user.setPassword(Md5Util.getMD5String(newPwd));
        userMapper.update(user);
    }
}
