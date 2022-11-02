package com.example.demo.log.pojo;

import java.lang.annotation.*;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/7/19
 * @Desc
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperateLog {
    OpModuleType module() default OpModuleType.RETAIN;

    OpWebType webName() default OpWebType.RETAIN;

    String desc() default "";

    String action() default "";

    String remark() default "";
}
