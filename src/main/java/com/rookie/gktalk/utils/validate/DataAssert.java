package com.rookie.gktalk.utils.validate;

import com.rookie.gktalk.utils.exception.WebException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class DataAssert extends Assert {
    public static void notEmpty(String string, String message) {
        if(!StringUtils.hasLength(string)){
            throw new WebException(message);
        }
    }

    public static void isTrue(boolean expresion,String message){
        if(!expresion){
            throw new WebException(message);
        }
    }

    public static void isNull(Object object,String message){
        if(object != null){
            throw new WebException(message);
        }
    }
}
