package com.rookie.gktalk.utils.common;

import java.util.regex.Pattern;

public class StringUtil {
    public static final String USERNAME_REGEX = "^[A-Za-z0-9]*{2,16}$";
    public static final String PASSWORD_REGEX = "^[A-Za-z]\\w{5,19}$";
    public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public static boolean isValid(String source,String regex){
        return Pattern.matches(regex,source);
    }
}
