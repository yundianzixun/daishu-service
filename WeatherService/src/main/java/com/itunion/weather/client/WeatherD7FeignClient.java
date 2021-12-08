package com.itunion.weather.client;

import com.itunion.weather.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-d7-service", url = "${weather.d7_url}", configuration = FeignClientConfig.class)
public interface WeatherD7FeignClient {

    /**
     * 近40天天气信息和最近24小时
     *
     * @param lat 纬度
     * @param lon 经度
     * @return 天气详情
     */
    @GetMapping(value = "/fishing/api/v1/tab")
    String localDetails(@RequestParam(value = "lat") String lat,@RequestParam(value = "lon") String lon);
}
