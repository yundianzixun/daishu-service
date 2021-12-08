package com.itunion.weather.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = " 天气信息请求实体")
@Data
public class TianQiRequest {
    @ApiModelProperty(value = "api版本")
    private String version = "v1";
    @ApiModelProperty(value = "城市ID")
    private String cityid;
}
