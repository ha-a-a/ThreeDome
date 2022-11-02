/**
 *
 */
package com.example.demo.common;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalCons {

    /**
     * jackson日期转字符串format
     */
    public final static String DATETIME_FORMAT = "YYYY-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT = "YYYY-MM-dd";

    /**
     * 用户有效状态：1.有效；0.无效
     */
    public static int USE_STATE_VALID = 1;
    public static int USE_STATE_INVALID = 0;

    /**
     * 角色有效状态：1.有效；0.无效
     */
    public static int ROLE_STATE_VALID = 1;
    public static int ROLE_STATE_INVALID = 0;

    /**
     * 权限有效状态：1.有效；0.无效
     */
    public static int PERM_STATE_VALID = 1;
    public static int PERM_STATE_INVALID = 0;

    /**
     * 默认的角色名称
     */
    public static String DEFAULT_ROLE = "DEFAULT_ROLE";
    /**
     * 容器类型：timeline：1，list:2,carousel:3
     */
    public static final String CONTAINER_TIMELINE="1";
    public static final String CONTAINER_LIST="2";
    public static final String CONTAINER_CAROUSEL="3";

}