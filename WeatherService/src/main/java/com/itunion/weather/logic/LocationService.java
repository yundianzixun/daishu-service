package com.itunion.weather.logic;

import com.itunion.weather.client.WsFeignClient;
import com.itunion.weather.config.WxConfig;
import com.itunion.weather.handler.TianQiHandler;
import com.itunion.weather.pojo.base.WSBaseResponse;
import com.itunion.weather.pojo.base.WSSugBaseResponse;
import com.itunion.weather.pojo.response.WsGeocodeAddressRespDto;
import com.itunion.weather.pojo.response.WsGeocodeLocationRespDto;
import com.itunion.weather.pojo.response.WsGeocodeSuggestionRespDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class LocationService {
    @Autowired
    private WsFeignClient wsFeignClient;
    @Autowired
    private WxConfig wxConfig;

    /**
     *
     * 功能描述: 逆地址解析（坐标位置描述）
     *
     * @param: location
     * @return: 
     * @auther: lin
     * @date: 2021/12/3 11:32 上午
     */
    public WSBaseResponse<WsGeocodeLocationRespDto> getWSGeocoderByLocation(String location,String getPoi) {
        return wsFeignClient.getWSGeocoderByLocation(location,wxConfig.getKey(),getPoi);
    }

    /**
     *
     * 功能描述: 地址解析（地址转坐标）
     *
     * @param: address
     * @return:
     * @auther: lin
     * @date: 2021/12/3 11:32 上午
     */
    public WSBaseResponse<WsGeocodeAddressRespDto> getWSGeocoderByAddress(String address) {
        return wsFeignClient.getWSGeocoderByAddress(address,wxConfig.getKey());
    }
    /**
     *
     * 功能描述: 关键字输入提示
     *
     * @param keyword 用户输入的关键词（希望获取后续提示的关键词）
     * @param region 限制城市范围：
     * @auther: lin
     * @date: 2021/12/3 11:32 上午
     */
    public WSSugBaseResponse<WsGeocodeSuggestionRespDto> getWSGeocoderBySuggestion(String keyword, String region) {
        return wsFeignClient.getWSGeocoderBySuggestion(keyword,region,wxConfig.getKey());
    }
}
