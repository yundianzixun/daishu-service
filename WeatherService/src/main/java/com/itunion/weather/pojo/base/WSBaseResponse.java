package com.itunion.weather.pojo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WSBaseResponse<T> {
    @ApiModelProperty(value = "状态码（0为正常\n" +
            "310请求参数信息有误\n" +
            "311Key格式错误\n" +
            "306请求有护持信息请检查字符串\n" +
            "110请求来源未被授权）")
    private Integer status;
    @ApiModelProperty(value = "状态说明")
    private String message;
    @ApiModelProperty(value = "地址解析结果")
    private T result;
}
