package com.itunion.weather.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "腾讯地址解析（逆地址解析-坐标位置描述）")
public class WsGeocodeLocationRespDto {
    private LocationBean location;
    @Data
    public static class LocationBean{
        @ApiModelProperty(value = "纬度")
        private String lat;
        @ApiModelProperty(value = "经度")
        private String lng;
    }
    @ApiModelProperty(value = "以行政区划+道路+门牌号等信息组成的标准格式化地址")
    private String address;

    @ApiModelProperty(value = "地址部件，address不满足需求时可自行拼接")
    private AddressComponentBean address_component;
    @Data
    public static class AddressComponentBean{
        @ApiModelProperty(value = "国家")
        private String nation;
        @ApiModelProperty(value = "省")
        private String province;
        @ApiModelProperty(value = "市")
        private String city;
        @ApiModelProperty(value = "区")
        private String district;
        @ApiModelProperty(value = "街道")
        private String street;
        @ApiModelProperty(value = "门牌")
        private String street_number;
    }

    @ApiModelProperty(value = "行政区划信息\n")
    private AdInfoBean ad_info;
    @Data
    public static class AdInfoBean{
        @ApiModelProperty(value = "国家代码")
        private String nation_code;
        @ApiModelProperty(value = "行政区划代码")
        private String adcode;
        @ApiModelProperty(value = "城市代码")
        private String city_code;
        @ApiModelProperty(value = "行政区划名称")
        private String name;
        @ApiModelProperty(value = "行政区划中心点坐标")
        private LocationBean location;
        @Data
        public static class LocationBean{
            @ApiModelProperty(value = "纬度")
            private String lat;
            @ApiModelProperty(value = "经度")
            private String lng;
        }
        @ApiModelProperty(value = "国家")
        private String nation;
        @ApiModelProperty(value = "省 / 直辖市")
        private String province;
        @ApiModelProperty(value = "市 / 地级区")
        private String city;
        @ApiModelProperty(value = "区 / 县级市 及同级行政区划")
        private String district;
    }

}
