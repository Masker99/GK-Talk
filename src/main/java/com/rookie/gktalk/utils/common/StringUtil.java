package com.rookie.gktalk.utils.common;

import java.util.regex.Pattern;

/**
 * 自定义字符串工具类
 * @author Masker
 */
public class StringUtil {
    public static final String USERNAME_REGEX = "^[A-Za-z0-9]*{2,16}$";
    public static final String PASSWORD_REGEX = "^[A-Za-z]\\w{5,19}$";
    public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 利用正则表达式进行校验
     * @param source 带校验字符串
     * @param regex 正则表达式
     * @return
     */
    public static boolean isValid(String source,String regex){
        return Pattern.matches(regex,source);
    }
}
