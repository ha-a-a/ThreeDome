package com.example.demo.enums;

/**
 * @author xiaoduozhang
 * @date 2019/5/29 16:34
 */
public enum TargetTypeEnum {
    /**
     * 0:保留值
     */
    Retain,
    /**
     * 1,全量
     */
    ALL,
    /**
     * 2，指定cid列表
     */
    TARGET_CIDS;

    public static TargetTypeEnum getTargetType(int msgType) {
        for (TargetTypeEnum type : TargetTypeEnum.values()) {
            if (type.ordinal() == msgType) {
                return type;
            }
        }
        return null;
    }
}
