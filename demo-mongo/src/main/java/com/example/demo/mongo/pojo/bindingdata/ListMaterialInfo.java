package com.example.demo.mongo.pojo.bindingdata;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/14
 * @Desc
 */
@Data
@Document(value = "list_material")
public class ListMaterialInfo extends MaterialInfo {

    @NotBlank
    public String activityUrl;

    @NotBlank
    @Max(value = 25)
    public String note;

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
