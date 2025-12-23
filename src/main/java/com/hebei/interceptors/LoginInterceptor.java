package com.hebei.interceptors;

import com.hebei.pojo.Result;
import com.hebei.util.JwtUtil;
import com.hebei.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/*
* 拦截器
* */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        try {
            //解析token
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //ThreadLocal存储用户信息
            ThreadLocalUtil.set(claims);
            //无异常，放行
            return true;
        } catch (Exception e) {
            //有异常
            response.setStatus(401);
            return false;
        }
    }
}
