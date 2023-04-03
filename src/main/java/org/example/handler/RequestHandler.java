package org.example.handler;
import org.example.common.Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(!Utils.checkToken(token)){
            response.setHeader("missed-token","token is missed!");
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
