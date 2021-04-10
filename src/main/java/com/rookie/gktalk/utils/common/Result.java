package com.rookie.gktalk.utils.common;

public class Result {
    private Integer code;
    private String description;
    private Object detail;

    public Result() { }

    public Result(Integer code, String description, Object detail){
        setCode(code);
        setDescription(description);
        setDetail(detail);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
