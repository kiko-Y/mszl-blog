package com.sjy.blog.common.aop;

import java.lang.annotation.*;

/**
 * @author: Kiko
 * @date: 2023/2/4 21:41
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";

    String operate() default "";
}
