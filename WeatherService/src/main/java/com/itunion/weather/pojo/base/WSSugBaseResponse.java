package com.itunion.weather.pojo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class WSSugBaseResponse<T> {
    @ApiModelProperty(value = "状态码（0为正常\n" +
            "310请求参数信息有误\n" +
            "311Key格式错误\n" +
            "306请求有护持信息请检查字符串\n" +
            "110请求来源未被授权）")
    private Integer status;
    @ApiModelProperty(value = "状态说明")
    private String message;
    @ApiModelProperty(value = "结果总数")
    private Integer count;
    @ApiModelProperty(value = "结果")
    private List<T> data ;
}
