package com.itunion.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config{
    public static final String BASE_PACKAGE = "com";
    @Value("${swagger.enable}")
    private boolean enableSwagger;
    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar1 = new ParameterBuilder();
        ticketPar1.name("verifyCode").description("用户认证verifyCode")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(true).build(); //header中的ticket参数非必填，传空也可以

        ParameterBuilder ticketPar2 = new ParameterBuilder();
        ticketPar2.name("sessionId").description("用户认证sessionId")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        List<Parameter> pars = new ArrayList<Parameter>();
        pars.add(ticketPar1.build());    //根据每个方法名也知道当前方法在设置什么参数
        pars.add(ticketPar2.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())                // 生产环境的时候关闭 swagger 比较安全
                .enable(enableSwagger)                //将Timestamp类型全部转为Long类型
                .directModelSubstitute(Timestamp.class, Long.class)                //将Date类型全部转为Long类型
                .directModelSubstitute(Date.class, Long.class)
                .globalOperationParameters(pars)
                .select()                // 扫描接口的包路径，不要忘记改成自己的
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }    private ApiInfo apiInfo() {        return new ApiInfoBuilder()
            .title("袋鼠天气 RESTful API文档")
            .description("Swagger API 服务")
            .termsOfServiceUrl("http://swagger.io/")
            .contact(new Contact("Swagger", "127.0.0.1", "zhenghhgz@163.com"))
            .version("1.0")
            .build();
    }


}
