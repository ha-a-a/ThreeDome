package com.example.demo.mongo.pojo.bindingdata;

import com.example.demo.mongo.config.MaterialTypeResolver;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.Data;

import java.io.Serializable;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/14
 * @Desc
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM, property = "containerId", visible = true)
@JsonTypeIdResolver(value = MaterialTypeResolver.class)
//@JsonSubTypes({@JsonSubTypes.Type(value = TimelineMaterialInfo.class,name ="timeline" )})

public abstract class MaterialInfo implements Serializable {

    private static final long serialVersionUID = 1900645582180323160L;
    public String id;
    public Integer containerId;
    public String imgUrl;
    public Integer priorityId;
}
