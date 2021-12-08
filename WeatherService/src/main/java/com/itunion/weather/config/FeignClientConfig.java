package com.itunion.weather.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
@Data
public class FeignClientConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("Content-Type", "text/html");
        template.header("referer", "http://m.weather.com.cn");
        template.header("host", "d1.weather.com.cn");
    }

    /*打印feign请求日志级别*/
    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }

}
