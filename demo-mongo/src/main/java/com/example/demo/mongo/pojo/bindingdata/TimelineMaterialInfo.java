package com.example.demo.mongo.pojo.bindingdata;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/14
 * @Desc
 */
@Data
@Document("timeline_material")
public class TimelineMaterialInfo extends MaterialInfo {

    public String activityUrl;
    @NotNull
    public Integer showNum;
    @NotNull
    public Integer interval;

    @Override
    public String getMaterial(String mid) {
        return "mid:"+mid;
    }

    @Override
    public String sortMaterial(String materialIdList) {
        return null;
    }

    @Override
    public String afterReturn(String materialInfoList) {
        return null;
    }
}
