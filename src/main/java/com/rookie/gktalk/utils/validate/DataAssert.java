package com.rookie.gktalk.utils.validate;

import com.rookie.gktalk.utils.exception.WebException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 数据断言，对数据进行合法性检查
 * @author Masker
 */
public class DataAssert extends Assert {
    /**
     * 字符串是否为空，为空抛出自定义异常信息
     * @param string 待校验字符串
     * @param message 自定义的异常信息
     */
    public static void notEmpty(String string, String message) {
        if(!StringUtils.hasLength(string)){
            throw new WebException(message);
        }
    }

    /**
     * 逻辑表达式是否为假，为假抛出自定义异常信息
     * @param expresion 逻辑表达式
     * @param message  自定义异常信息
     */
    public static void isTrue(boolean expresion,String message){
        if(!expresion){
            throw new WebException(message);
        }
    }

    /**
     * 对象是否为Null，为Null抛出自定义异常信息
     * @param object
     * @param message
     */
    public static void isNull(Object object,String message){
        if(object != null){
            throw new WebException(message);
        }
    }
}
