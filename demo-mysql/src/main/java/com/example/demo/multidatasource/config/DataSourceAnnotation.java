package com.example.demo.multidatasource.config;

import java.lang.annotation.*;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/12
 * @Desc
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceAnnotation {
     String value() default DataSourceNames.OPERATOR;
}
