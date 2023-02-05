package com.sjy.blog.common.aop;

import java.lang.annotation.*;

/**
 * @author: Kiko
 * @date: 2023/2/4 21:41
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    /**
     * 默认1分钟缓存时间
     * @return
     */
    long expire() default 60 * 1000;

    /**
     * 缓存标识 key
     * @return
     */
    String name() default "";
}
