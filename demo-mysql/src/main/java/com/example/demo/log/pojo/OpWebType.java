package com.example.demo.log.pojo;

/**
 * @FUNC 操作页面类型
 * @Author mengyuetang
 * @createTime 2019/7/19
 * @Desc
 */
public enum OpWebType {
    /**
     * 保留
     */
    RETAIN(0, "保留"),
    /**
     * 用户登陆
     */
    HOME(1, "首页");
    public Integer code;
    public String module;

    OpWebType(Integer code, String module) {
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
