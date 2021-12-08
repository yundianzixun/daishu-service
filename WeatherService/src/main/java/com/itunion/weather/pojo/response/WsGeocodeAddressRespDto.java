package com.itunion.weather.pojo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "腾讯地址解析（地址转坐标实体类）")
public class WsGeocodeAddressRespDto {
    private String title;
    private LocationBean location;
    @Data
    public static class LocationBean{
        @ApiModelProperty(value = "纬度")
        private String lat;
        @ApiModelProperty(value = "经度")
        private String lng;
    }
    private AdInfoBean ad_info;
    @Data
    public static class AdInfoBean {
        @ApiModelProperty(value = "行政区划代码")
        private String adcode;
    }
    @ApiModelProperty(value = "地址部件，address不满足需求时可自行拼接")
    private AddressComponentsBean address_components;
    @Data
    public static class AddressComponentsBean{
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
    @ApiModelProperty(value = "可信度参（考值范围 1 <低可信> - 10 <高可信>）,该值>=7时，解析结果较为准确")
    private String reliability;
    @ApiModelProperty(value = "精确度级别（分为11个级别，一般>=9即可采用（定位到点，精度较高））")
    private String level;

}
