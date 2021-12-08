package com.itunion.weather.client;

import com.itunion.weather.config.FeignClientConfig;
import com.itunion.weather.pojo.base.WSBaseResponse;
import com.itunion.weather.pojo.base.WSSugBaseResponse;
import com.itunion.weather.pojo.response.WsGeocodeAddressRespDto;
import com.itunion.weather.pojo.response.WsGeocodeLocationRespDto;
import com.itunion.weather.pojo.response.WsGeocodeSuggestionRespDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ws-service", url = "${ws.url}", configuration = FeignClientConfig.class)
public interface WsFeignClient {

    /**
     * 地址解析（地址转坐标）
     * @param address 地址
     * @key 腾讯地图开发者key
     * @return
     */
    @GetMapping(value = "/ws/geocoder/v1/")
    WSBaseResponse<WsGeocodeAddressRespDto> getWSGeocoderByAddress(@RequestParam("address") String address,@RequestParam("key") String key);

    /**
     *
     * 功能描述: 逆地址解析（坐标位置描述）
     * @param location 经纬度（GCJ02坐标系），格式 location=lat<纬度>,lng<经度>
     * @param getPoi 是否返回周边地点（POI）列表，可选值：0-不返回（默认）1-返回
     * @param key 开发秘钥（Key）
     * @return: 
     */
    @GetMapping(value = "/ws/geocoder/v1/")
    WSBaseResponse<WsGeocodeLocationRespDto> getWSGeocoderByLocation(@RequestParam("location") String location, @RequestParam("key") String key, @RequestParam("get_poi") String getPoi);


    /**
     *
     * 功能描述: 关键字输入提示
     * @param keyword 用户输入的关键词（希望获取后续提示的关键词）
     * @param region 限制城市范围：
     * @return:
     */
    @GetMapping(value = "/ws/place/v1/suggestion/")
    WSSugBaseResponse<WsGeocodeSuggestionRespDto> getWSGeocoderBySuggestion(@RequestParam("keyword") String keyword, @RequestParam("region") String region, @RequestParam("key") String key);


}

