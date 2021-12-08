package com.itunion.weather.common;

import com.itunion.weather.common.enums.BaseCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 5851419527617790088L;
    @ApiModelProperty(notes = "返回状态码")
    String code;
    @ApiModelProperty(notes = "返回信息")
    String message;
    @ApiModelProperty(notes = "返回对象")
    T result;

    public BaseResponse() {
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(T data) {
        this.code = BaseCode.SUCCESS.getCode();
        this.message = BaseCode.SUCCESS.getDesc();
        this.result = data;
    }

}