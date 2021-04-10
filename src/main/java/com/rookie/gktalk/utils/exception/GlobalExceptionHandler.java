package com.rookie.gktalk.utils.exception;

import com.rookie.gktalk.utils.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WebException.class)
    @ResponseBody
    public Result jsonExceptionHandler(WebException e){
        Result result = new Result();

        result.setCode(e.getCode());
        result.setDescription(e.getMessage());

        return result;
    }
}
