package com.itunion.weather.web.controller;

import com.itunion.weather.common.BaseResponse;
import com.itunion.weather.common.enums.BaseCode;
import lombok.extern.slf4j.Slf4j;

;

/**
 * 所有controller继承该类，作为统一返回格式
 */
@Slf4j
public class BaseController {

    // 成功
    public <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(data);
    }

    // 失败
    public <T> BaseResponse<T> fail(String message,T data){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(BaseCode.FAIL.getCode());
        baseResponse.setMessage(message);
        baseResponse.setResult(data);
        return baseResponse;
    }

}

