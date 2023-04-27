package com.example.demo.mongo.pojo.autoinc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tangmengyue
 * @ClassName AutoIncKey.java
 * @Description TODO
 * @createTime 2023年04月26日 10:09:00
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoIncKey {
}
