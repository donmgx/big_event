package com.hebei.interceptors;

import com.hebei.pojo.Result;
import com.hebei.util.JwtUtil;
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
            Map<String, Object> parseToken = JwtUtil.parseToken(token);
            //无异常，放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}
