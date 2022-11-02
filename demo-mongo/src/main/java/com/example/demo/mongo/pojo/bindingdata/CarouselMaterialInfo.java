package com.example.demo.mongo.pojo.bindingdata;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/14
 * @Desc
 */
@Data
@Document("carousel_material")
public class CarouselMaterialInfo extends MaterialInfo {

    @NotBlank
    public String activityUrl;

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
