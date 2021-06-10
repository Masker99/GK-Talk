package com.rookie.gktalk.utils.interceptor;


import com.auth0.jwt.JWT;
import com.rookie.gktalk.pojo.User;
import com.rookie.gktalk.services.Impl.UserServiceImpl;
import com.rookie.gktalk.utils.annotation.PassToken;
import com.rookie.gktalk.utils.annotation.UserLoginToken;
import com.rookie.gktalk.utils.common.TokenUtil;
import com.rookie.gktalk.utils.exception.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()){
                return true;
            }
        }

        if(method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()){
                if (token == null || token.equals("")){
                    throw new WebException("token不存在");
                }

                String userName = JWT.decode(token).getAudience().get(0);
                User user = userService.selectUserByUserName(userName);
                if(user == null){
                    throw new WebException("未寻找到该用户");
                }

                TokenUtil.verifyToken(user,token);

                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
