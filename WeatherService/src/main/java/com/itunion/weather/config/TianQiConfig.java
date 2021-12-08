package com.itunion.weather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "tianqi")
public class TianQiConfig {
    private String appid;
    private String appsecret;
}
