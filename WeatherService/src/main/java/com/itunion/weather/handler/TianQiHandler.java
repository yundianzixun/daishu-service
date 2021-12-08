package com.itunion.weather.handler;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.itunion.weather.client.TianQiFeignClient;
import com.itunion.weather.client.WeatherD1FeignClient;
import com.itunion.weather.client.WeatherD7FeignClient;
import com.itunion.weather.config.TianQiConfig;
import com.itunion.weather.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 *
 * 功能描述: 天气缓存信息
 *
 * @auther: lin
 * @date: 2021年12月01日18:03:24
 */

@Component
@Slf4j
public class TianQiHandler {
    @Autowired
    private TianQiConfig tianQiConfig;
    @Autowired
    private TianQiFeignClient tianQiFeignClient;
    @Autowired
    private WeatherD7FeignClient weatherD7FeignClient;
    @Autowired
    private WeatherD1FeignClient weatherD1FeignClient;

    private LoadingCache<Map<String, String>, Object> localDetailsCacher;
    private LoadingCache<String, Object> recentlyCacher;
    private LoadingCache<Map<String, String>, Object> tianqiapiCacher;
    private LoadingCache<String, Object> localInfoCacher;


    @PostConstruct
    public void init() {
        // TODO 获取当前地址的早晚天气详情
        localDetailsCacher = CacheBuilder.newBuilder()
                .maximumSize(500L)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .build(new CacheLoader<Map<String, String>, Object>() {
                    @Override
                    public Object load(Map<String, String> map) throws Exception {
                        if (map == null) {
                            log.info("经纬度信息不能为空，map={}", map);
                            return null;
                        }
                        return new Gson().fromJson(weatherD7FeignClient.localDetails(map.get("lat"), map.get("lon")), Object.class);
                    }
                });
        // TODO 近日40天和24小时
        recentlyCacher = CacheBuilder.newBuilder()
                .maximumSize(500L)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String cityCode) throws Exception {
                        if (StringUtils.isEmpty(cityCode)) {
                            log.info("城市Code不能为空，cityCode={}", cityCode);
                            return null;
                        }
                        String recently = weatherD1FeignClient.recently(cityCode);
                        recently = StringUtil.formatVar(recently);
                        Object o = new Gson().fromJson(recently, Object.class);
                    return o;
                    }
                });
        // TODO  获取天气api
        tianqiapiCacher = CacheBuilder.newBuilder()
                .maximumSize(500L)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .build(new CacheLoader<Map<String, String>, Object>() {
                    @Override
                    public Object load(Map<String, String> map) throws Exception {
                        if (map == null) {
                            log.info("tianqiapiCacher城市信息不能为空，map={}", map);
                            return null;
                        }
                        return tianQiFeignClient.tianqiapi(map.get("version"), map.get("cityid"), tianQiConfig.getAppid(), tianQiConfig.getAppsecret());
                    }
                });
        // TODO  获取城市的天气信息
        localInfoCacher = CacheBuilder.newBuilder()
                .maximumSize(500L)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String cityCode) throws Exception {
                        if (StringUtils.isEmpty(cityCode)) {
                            log.info("localInfoCacher城市Code不能为空，cityCode={}", cityCode);
                            return null;
                        }
                        String recently = weatherD1FeignClient.localInfo(cityCode);
                        recently = StringUtil.formatVar(recently);
                        Object o = new Gson().fromJson(recently, Object.class);
                    return o;
                    }
                });

    }
    // TODO 获取当前地址的早晚天气详情
    public Object getLocalDetailsCacher(String lat, String lon){
        Map<String,String> map = new HashMap<>();
        map.put("lat",lat);
        map.put("lon",lon);
        try {
            return localDetailsCacher.getUnchecked(map);
        }catch (Throwable t){
            log.error("获取当前地址的早晚天气详情，map={}",map);
            return null;
        }
    }
    // TODO 近日40天和24小时
    public Object getRecentlyCacher(String cityCode){
        try {
            return recentlyCacher.getUnchecked(cityCode);
        }catch (Throwable t){
            log.error("Recently城市Code异常，cityCode={}",cityCode);
            return null;
        }
    }
    // TODO 获取天气api
    public Object getTianqiapiCacher(String version, String cityid){
        Map<String,String> map = new HashMap<>();
        map.put("version",version);
        map.put("cityid",cityid);
        try {
            return tianqiapiCacher.getUnchecked(map);
        }catch (Throwable t){
            log.error("获取天气api，map={}",map);
            return null;
        }
    }
    // TODO 获取城市的天气信息
    public Object getLocalInfoCacher(String cityCode){
        try {
            return localInfoCacher.getUnchecked(cityCode);
        }catch (Throwable t){
            log.error("LocalInfo城市Code异常，cityCode={}",cityCode);
            return null;
        }
    }
}
