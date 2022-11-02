package com.example.demo.log.pojo;

/**
 * @FUNC 操作模块类型
 * @Author mengyuetang
 * @createTime 2019/7/19
 * @Desc
 */
public enum OpModuleType {
    /**
     * 保留
     */
    RETAIN(0, "保留"),
    /**
     * 用户登陆
     */
    LOGIN(1, "登陆"),
    /**
     * 用户注销
     */
    LOGOUT(2, "注销");
    public Integer code;
    public String module;

    OpModuleType(Integer code, String module) {
        this.code = code;
        this.module = module;
    }

    public Integer getCode() {
        return code;
    }

    public String getModule() {
        return module;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
