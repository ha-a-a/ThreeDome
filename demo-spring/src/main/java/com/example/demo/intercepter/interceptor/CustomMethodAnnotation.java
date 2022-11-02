package com.example.demo.intercepter.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomMethodAnnotation {
    /**
     * 方法名描述
     */
    String name() default "";

    /**
     * 加密
     */
    String[] encrypt() default {};

    /**
     * 解密
     */
    String[] decrypt() default {};
}
