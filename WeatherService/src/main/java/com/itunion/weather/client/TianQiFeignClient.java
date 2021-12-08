package com.itunion.weather.client;

import com.itunion.weather.config.FeignClientConfig;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tianqi-service", url = "${tianqi.url}", configuration = FeignClientConfig.class)
public interface TianQiFeignClient {
    /**
     * 查询近日天气
     *
     * @param version api版本
     * @param cityid 城市ID
     * @param appid 账号appId
     * @param appsecret 账号appsecret
     * @return
     */
    @GetMapping(value = "/api")
    Object tianqiapi(@RequestParam(value = "version") String version,@RequestParam(value = "cityid") String cityid,@RequestParam(value = "appid") String appid,@RequestParam(value = "appsecret") String appsecret);

}
