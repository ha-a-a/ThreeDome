package com.example.demo.mongo.config;

import com.example.timeline.demo.common.GlobalCons;
import com.example.timeline.demo.mongo.pojo.bindingdata.CarouselMaterialInfo;
import com.example.timeline.demo.mongo.pojo.bindingdata.ListMaterialInfo;
import com.example.timeline.demo.mongo.pojo.bindingdata.MaterialInfo;
import com.example.timeline.demo.mongo.pojo.bindingdata.TimelineMaterialInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/14
 * @Desc
 */
@Configuration
public class MaterialTypeResolver implements TypeIdResolver {


    @Override
    public void init(JavaType baseType) {

    }

    @Override
    public String idFromValue(Object value) {
        return null;
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return null;
    }

    @Override
    public String idFromBaseType() {
        return null;
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) throws IOException {
        if (id.equals(GlobalCons.CONTAINER_TIMELINE))
            return context.constructSpecializedType(context.constructType(MaterialInfo.class), TimelineMaterialInfo.class);
        if (id.equals(GlobalCons.CONTAINER_LIST))
            return context.constructSpecializedType(context.constructType(MaterialInfo.class), ListMaterialInfo.class);
        if (id.equals(GlobalCons.CONTAINER_CAROUSEL))
            return context.constructSpecializedType(context.constructType(MaterialInfo.class), CarouselMaterialInfo.class);
        return null;
    }

    @Override
    public String getDescForKnownTypeIds() {
        return null;
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return null;
    }
}
