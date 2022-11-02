package com.example.demo.mongo.service;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/3/15
 * @Desc
 */
public interface MaterialService {
//    List<MaterialInfo> getMaterial(String mid);
//    List<MaterialInfo> sortMaterial(List<MaterialInfo> materialIdList);
//    List<MaterialInfo> afterReturn(List<MaterialInfo> materialInfoList);
    String getMaterial(String mid);
    String sortMaterial(String materialIdList);
    String afterReturn(String materialInfoList);
}
