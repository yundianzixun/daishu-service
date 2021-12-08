package com.itunion.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WeatherServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WeatherServiceApplication.class, args);
    }

}
