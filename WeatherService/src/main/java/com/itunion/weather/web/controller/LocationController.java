package com.itunion.weather.web.controller;

import com.itunion.weather.common.BaseResponse;
import com.itunion.weather.common.Constants;
import com.itunion.weather.common.enums.BaseCode;
import com.itunion.weather.logic.LocationService;
import com.itunion.weather.pojo.base.WSBaseResponse;
import com.itunion.weather.pojo.base.WSSugBaseResponse;
import com.itunion.weather.pojo.response.WsGeocodeAddressRespDto;
import com.itunion.weather.pojo.response.WsGeocodeLocationRespDto;
import com.itunion.weather.pojo.response.WsGeocodeSuggestionRespDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/location")
public class LocationController extends BaseController {
    @Autowired
    private LocationService locationService;
    /**
     *
     * 功能描述: 逆地址解析（坐标位置描述）
     * @param: lat 纬度
     * @param: lon 经度
     * @return: 
     * @auther: lin
     * @date: 2021/12/1 2:01 下午
     */
    @RequestMapping(value = "/geocoderByLocation", method = RequestMethod.GET)
    public BaseResponse<WsGeocodeLocationRespDto> getWSGeocoderByLocation(@RequestParam("lat")String lat, @RequestParam("lon")String lon, @RequestParam("getPoi")String getPoi) {
        // 31.253386607006473,121.48406735852905
        if(StringUtils.isEmpty(lat) || StringUtils.isEmpty(lon)){
            return this.fail(BaseCode.PARAM_IS_BLANK.getDesc(),null);
        }
        if(StringUtils.isEmpty(getPoi) || !getPoi.equals(Constants.STATE_1) ){
            getPoi = "1";
        }
        WSBaseResponse<WsGeocodeLocationRespDto> wsBaseResponse = locationService.getWSGeocoderByLocation(lat+","+lon,getPoi);
        if(wsBaseResponse.getStatus() == 0){
            return this.success(wsBaseResponse.getResult());
        }
        return this.fail(wsBaseResponse.getMessage(),null);
    }
    /**
     *
     * 功能描述: 地址解析（地址转坐标）
     * @param: address 地址
     * @return:
     * @auther: lin
     * @date: 2021年12月04日21:01:56
     */
    @RequestMapping(value = "/geocoderByAddress", method = RequestMethod.GET)
    public BaseResponse<WsGeocodeAddressRespDto> getWSGeocoderByAddress(@RequestParam("address")String address) {
        if(StringUtils.isEmpty(address)){
            return this.fail(BaseCode.PARAM_IS_BLANK.getDesc(),null);
        }
        WSBaseResponse<WsGeocodeAddressRespDto> wsBaseResponse = locationService.getWSGeocoderByAddress(address);
        if(wsBaseResponse.getStatus() == 0){
            return this.success(wsBaseResponse.getResult());
        }
        return this.fail(wsBaseResponse.getMessage(),null);
    }
    /**
     *
     * 功能描述: 关键字输入提示
     * @param keyword 用户输入的关键词（希望获取后续提示的关键词）
     * @param region 限制城市范围：
     * @return:
     * @auther: lin
     * @date: 2021年12月04日21:01:56
     */
    @RequestMapping(value = "/geocoderBySuggestion", method = RequestMethod.GET)
    public BaseResponse<List<WsGeocodeSuggestionRespDto>> getWSGeocoderBySuggestion(@RequestParam("keyword")String keyword, @RequestParam("region")String region) {
        if(StringUtils.isEmpty(keyword) || StringUtils.isEmpty(region)){
            return this.fail(BaseCode.PARAM_IS_BLANK.getDesc(),null);
        }
        WSSugBaseResponse<WsGeocodeSuggestionRespDto> wsSugBaseResponse = locationService.getWSGeocoderBySuggestion(keyword,region);
        if(wsSugBaseResponse.getStatus() == 0){
            return this.success(wsSugBaseResponse.getData());
        }
        return this.fail(wsSugBaseResponse.getMessage(),null);
    }
}