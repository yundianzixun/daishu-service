package com.itunion.weather.aop;

import com.itunion.weather.common.enums.BaseCode;
import com.itunion.weather.exception.CustomException;
import com.itunion.weather.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Pointcut("execution(public * com.itunion.weather.web.controller.*.*(..))")
    public void pointCut() {
    }

    /**
     * 记录请求日志信息
     * @param joinPoint
     */
    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            log.info("方法URI地址：{}, 方法名称：{}, 方法入参：{}", request.getRequestURI(),
                    joinPoint.getSignature().getName(), null);
        } else {
            log.info(" 方法URI地址：{}, 方法名称：{}, 方法入参：{}", request.getRequestURI(),
                    joinPoint.getSignature().getName(), joinPoint.getArgs()[0]);
        }
    }

    /**
     * 记录返回数据信息
     * @param joinPoint
     * @param val
     */
    @AfterReturning(value = "pointCut()", returning = "val")
    public void afterReturning(JoinPoint joinPoint, Object val) {
        log.info("访问天气结束时间：{},响应参数数据：{}",LocalDateTime.now(), val);
    }

}
