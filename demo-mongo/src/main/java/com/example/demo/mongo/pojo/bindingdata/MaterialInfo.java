package com.example.demo.mongo.pojo.bindingdata;

import com.example.demo.mongo.config.MaterialTypeResolver;
import com.example.demo.mongo.service.MaterialService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

public abstract class MaterialInfo implements Serializable, MaterialService {

    private static final long serialVersionUID = 1900645582180323160L;
    @Id
    @Field("_id")
    public String id;
    @NotNull
    public Integer containerId;
    @NotBlank
    public String imgUrl;
    @NotNull
    public Integer priorityId;
}
