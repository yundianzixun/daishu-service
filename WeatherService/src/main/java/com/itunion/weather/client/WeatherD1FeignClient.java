package com.itunion.weather.client;

import com.itunion.weather.config.FeignClientConfig;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-d1-service", url = "${weather.d1_url}", configuration = FeignClientConfig.class)
public interface WeatherD1FeignClient {
    /**
     * 根据经纬度查询天气详情
     *
     * @param cityCode 城市Code
     * @return 天气详情
     */
    @GetMapping(value = "/wap_40d/{cityCode}.html")
    String recently(@PathVariable("cityCode") String cityCode);
    /**
     * 城市的天气信息
     *
     * @param cityCode 城市Code
     * @return 天气详情
     */
    @GetMapping(value = "/dingzhi/{cityCode}.html")
    String localInfo(@PathVariable("cityCode") String cityCode);
}
