package com.itunion.weather.web.controller;

import com.itunion.weather.logic.TianQiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/")
public class TianQiController{
    @Autowired
    private TianQiService tianQiService;
    /**
     *
     * 功能描述: 获取当前地址的早晚天气详情
     * @param: lat 纬度
     * @param: lon 经度
     * @return: 
     * @auther: lin
     * @date: 2021/12/1 2:01 下午
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object localDetails(@RequestParam("lat")String lat,@RequestParam("lon")String lon) {
        return tianQiService.localDetails(lat,lon);
    }
    /**
     *
     * 功能描述: 近日40天和24小时
     *
     * @param: cityCode 城市Code
     * @return:
     * @auther: lin
     * @date: 2021/12/1 2:05 下午
     */
    @RequestMapping(value = "/recently", method = RequestMethod.GET)
    public Object recently(@RequestParam("cityCode")String cityCode) {
        return tianQiService.recently(cityCode);
    }
    /**
     *
     * 功能描述: 获取天气api
     *
     * @param: version：api版本
     * @param: cityid：城市ID
     * @return: 
     * @auther: lin
     * @date: 2021/12/1 2:08 下午
     */
    @RequestMapping(value = "/tianqiapi", method = RequestMethod.GET)
    public Object getTianQi(@RequestParam("version")String version,@RequestParam("cityid")String cityid) {
        return tianQiService.tianqiapi(version,cityid);

    }
    /**
     *
     * 功能描述: 获取城市的天气信息
     *
     * @param: version：api版本
     * @param: cityid：城市ID
     * @return:
     * @auther: lin
     * @date: 2021/12/1 2:08 下午
     */
    @RequestMapping(value = "/localInfo", method = RequestMethod.GET)
    public Object localInfo(@RequestParam("cityCode")String cityCode) {
        return tianQiService.localInfo(cityCode);

    }
}