package com.example.demo.enums;

public enum TokenTypeEnum {

    Retain, HUAWEI, XIAOMI, IOS, OTHER, OPPO;

    public static TokenTypeEnum getTokenType(int type) {
        for (TokenTypeEnum enumTokenType : TokenTypeEnum.values()) {
            if (enumTokenType.ordinal() == type) {
                return enumTokenType;
            }
        }
        return null;
    }
}
