package com.hebei.controller;

import com.hebei.pojo.Result;
import com.hebei.pojo.User;
import com.hebei.server.UserServer;
import com.hebei.util.JwtUtil;
import com.hebei.util.Md5Util;
import com.hebei.util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/user")
@Validated  //validation中的，进行参数校验
public class UserController {
    @Autowired
    private UserServer userServer;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
            //装进redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,12, TimeUnit.HOURS);
            return Result.success(token);
        } else {
            return Result.error("登录失败");
        }
    }

    /*
     * 获取用户信息
     * */
    @GetMapping("/userInfo")
    public Result getInfo() { //无参数
        //读取ThreadLocal中的用户信息
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        log.info("获取用户信息：{}", username);
        //读取用户信息
        User user = userServer.findByUsername(username);
        return Result.success(user);
    }


    /*
     * 更新用户信息
     * */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        log.info("更新用户信息：{}", user);
        userServer.update(user);
        return Result.success();
    }

    /*
     * 修改密码
     * */
    @PatchMapping("/updatePwd")
    public Result updatePwd(String old_pwd, String new_pwd, String re_pwd, @RequestHeader("Authorization") String token) throws Exception {
        log.info("修改密码");

        userServer.updatePwd(old_pwd, new_pwd, re_pwd);

        //删除redis中的token
        stringRedisTemplate.delete(token);

        return Result.success();
    }
}
