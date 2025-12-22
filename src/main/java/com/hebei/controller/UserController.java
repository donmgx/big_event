package com.hebei.controller;

import com.hebei.pojo.Result;
import com.hebei.pojo.User;
import com.hebei.server.UserServer;
import com.hebei.util.JwtUtil;
import com.hebei.util.Md5Util;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@Validated  //validation中的，进行参数校验
public class UserController {
    @Autowired
    private UserServer userServer;

    /*
     * 用户注册
     * */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        log.info("判断用户“{}”是否存在", username);
        User user = userServer.findByUsername(username);
        if (user == null) {
            log.info("注册用户：{}", username);
            userServer.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户已存在");
        }
    }


    /*
     * 用户登录
     * */
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        log.info("用户登录：{}", username);
        //登录验证
        User user = userServer.findByUsername(username);
        if (Md5Util.getMD5String(password).equals(user.getPassword())) {
            //登录，下发JWT
            Map<String, Object> claims = new HashMap();
            claims.put("username", user.getUsername());
            claims.put("id", user.getId());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        } else {
            return Result.error("登录失败");
        }
    }
}
