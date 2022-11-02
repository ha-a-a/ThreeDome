package com.example.demo.enums;

/**
 * @author xiaoduozhang
 * @date 2019/5/29 16:34
 */
public enum PushTypeEnum {
    /**
     * 0:保留值
     */
    Retain,
    /**
     * 通知栏推送
     */
    NOTIFICATION,
    /**
     * 透传推送
     */
    PASS_THROUGH;

    private int type;
    private String description;

    public int getType() {
        return type;
    }

}
