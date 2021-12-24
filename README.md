 <p align="center">
   <img src="https://img.shields.io/badge/JDK-1.8+-green.svg" alt="Build Status">
   <img src="https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2.2.2.RELEAS-blue.svg" alt="Coverage Status">
   <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR1-blue.svg" alt="Coverage Status">
   <img src="https://img.shields.io/badge/SpringBoot-2.3.7.RELEASE-brightgreen" alt="Coverage Status">
   <img src="https://img.shields.io/badge/Swagger-2.10.5-brightgreen" alt="Coverage Status">
 </p> 
 
> 启动小程序端项目时，还需要启动另一个项目[WeatherService](https://github.com/yundianzixun/daishu-service/tree/main/WeatherService) 作为天气业务的数据服务
> 在开发的过程中，可能会没发现到一些特别的问题，有什么建议和问题，欢迎大牛们在[Issues](https://github.com/yundianzixun/daishu-service/issues) 提出更加好的方案。


## 一、袋鼠天气微信小程序用户端（daishu-weather）
- [x]  地址定位
- [x]  逆地址解析（坐标位置描述）
- [x]  地址解析（地址转坐标）
- [x]  关键字输入提示
- [x]  当前定位天气情况
- [x]  24小时天气情况
- [x]  7天天气情况
- [x]  当前地址相关指数
- [x]  历史记录

#### 目录结构
```
- ec-canvas                 canvas插件
- iconfont                  字体图片
- images                    可以将图片放在服务器请求（减少文件体积）
    index                       指数图片
    weather                     天气描述图片
- libs                      
    city-code.js                城市代码文件
    makePy.js                      文字转拼音首字母
    weather.js                  天气描述
- pages
    weather                     天气首页
    search                      搜索地址页
- server
    type.js                     请求地址
- utils                         工具文件
```

## 二、袋鼠天气微信小程序服务端（WeatherService）
* 后端基础框架 SpringCloud/SpringCloud Alibaba
* 远程调用 OpenFeign
* 缓存 CacheBuilder
* API在线接口文档 Swagger
* 地理位置服务 腾讯地图 （个人开发需独立申请：https://lbs.qq.com ）
* 天气API服务（个人开发需独立申请：https://www.tianqiapi.com/ ）

API访问地址：http://127.0.0.1:9002/weather/api

## 预览
<img src="https://github.com/yundianzixun/daishu-service/blob/main/daishu-weather/gif/qr_code.jpg"/>


## 视图展示
##### 首页
<img src="https://github.com/yundianzixun/daishu-service/blob/main/daishu-weather/gif/home.gif"/>

##### 搜索✌️
<img src="https://github.com/yundianzixun/daishu-service/blob/main/daishu-weather/gif/search.gif"/>


##### 关键字搜索✌️
<img src="https://github.com/yundianzixun/daishu-service/blob/main/daishu-weather/gif/search2.gif"/>
