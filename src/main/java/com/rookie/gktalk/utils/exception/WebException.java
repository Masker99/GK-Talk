package com.rookie.gktalk.utils.exception;

public class WebException extends RuntimeException{
    private final static long serialVersionUID = 1L;
    private int code;
    private String message;

    public WebException(String message){
        this.code = 201;
        this.message = message;
    }

    public WebException(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
