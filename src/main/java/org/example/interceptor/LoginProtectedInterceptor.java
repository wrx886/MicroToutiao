package org.example.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.util.Result;
import org.example.util.ResultCodeEnum;
import org.example.util.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;

    private final TokenUtil tokenUtil;

    public LoginProtectedInterceptor(TokenUtil tokenUtil, ObjectMapper objectMapper) {
        this.tokenUtil = tokenUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从请求头中获取 token
        String token = request.getHeader("token");
        // 2. 检查 token 是否有效，如果有效就放行
        if (tokenUtil.loadToken(token) != null) {
            // token 有效，放行
            return true;
        } else {
            // token 无效
            response.getWriter().print(objectMapper.writeValueAsString(new Result(ResultCodeEnum.NOT_LOGIN)));
            return false;
        }
    }
}
