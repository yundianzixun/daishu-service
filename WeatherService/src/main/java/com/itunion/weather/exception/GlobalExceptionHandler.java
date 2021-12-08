package com.itunion.weather.exception;


import com.itunion.weather.common.BaseResponse;
import com.itunion.weather.common.enums.BaseCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public BaseResponse<Object> handleCustomException(CustomException customException) {
        if (customException == null) {
            return new BaseResponse<>(BaseCode.unknown.getCode(), BaseCode.unknown.getDesc());
        }
        return new BaseResponse<>(customException.getCode(), customException.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse<Object> handleCustomException(Exception e) {
        e.printStackTrace();
        if (e instanceof CustomException){
            CustomException exception = (CustomException)e;
            return new BaseResponse<>(exception.getCode(), exception.getMsg());
        }
        return new BaseResponse<>(BaseCode.unknown.getCode(), BaseCode.unknown.getDesc());
    }
}


