package com.itunion.weather.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "关键字输入提示")
public class WsGeocodeSuggestionRespDto {
    @ApiModelProperty(value = "POI唯一标识")
    private String id;
    @ApiModelProperty(value = "提示文字")
    private String title;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "行政区划代码")
    private String adcode;
    @ApiModelProperty(value = "POI类型，值说明：0:普通POI / 1:公交车站 / 2:地铁站 / 3:公交线路 / 4:行政区划")
    private Integer type;
    @ApiModelProperty(value = "传入location（定位坐标）参数时，返回定位坐标到各POI的距离")
    private Integer _distance;
    @ApiModelProperty(value = "提示所述位置坐标")
    private LocationBean location;
    @Data
    public static class LocationBean{
        @ApiModelProperty(value = "纬度")
        private String lat;
        @ApiModelProperty(value = "经度")
        private String lng;
    }
}
