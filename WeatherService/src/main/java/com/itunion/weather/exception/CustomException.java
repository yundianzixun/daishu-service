package com.itunion.weather.exception;

import com.itunion.weather.common.enums.BaseCode;
import lombok.ToString;

@ToString
public class CustomException extends RuntimeException{
    private String code;
    private String msg;
    //    private Object result = new Object();
    private Object result = new Object();
    public CustomException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CustomException(BaseCode code){
        this.code = code.getCode();
        this.msg = code.getDesc();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
