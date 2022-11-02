package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/2/25
 * @Desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface AutoIncKey {
    String value() default "";
}
