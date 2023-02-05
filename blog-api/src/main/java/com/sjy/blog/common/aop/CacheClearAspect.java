package com.sjy.blog.common.aop;

import com.alibaba.fastjson.JSON;
import com.sjy.blog.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Set;

/**
 * @author: Kiko
 * @date: 2023/2/5 10:41
 */
@Component
@Aspect
@Slf4j
public class CacheClearAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("@annotation(com.sjy.blog.common.aop.CacheClear)")
    public void pc(){}


    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            log.info("清空缓存...");
            Set<String> keys = redisTemplate.keys("*");
            if(keys != null) {
                for (String key : keys) {
                    redisTemplate.delete(key);
                }
            }
            return pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return R.fail(-999,"系统错误");
    }
}
