package com.rookie.gktalk.utils.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class CookiesUtil {
    public static void setCookie(String key,String value){
        HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getResponse();
        Cookie cookie = new Cookie(key,value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*5);
        cookie.setDomain("localhost:8080");

        assert response != null;
        response.addCookie(cookie);
    }

    public static String getCookie(String key){
        HttpServletRequest request = ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes())).getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals(key)){
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
