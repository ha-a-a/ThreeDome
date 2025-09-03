package demo.nacos.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import demo.nacos.model.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/9/9
 * @Desc
 */
@RestController
@RequestMapping("/config")
public class NacosConfigController {

    @Autowired
    private ActivityService activityService;
    @NacosValue(value = "${test.data.example}", autoRefreshed = true)
    private String message;

    @GetMapping("message/get")
    public String getMessage() {
        return message;
    }

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @GetMapping(value = "/get")
    public boolean get() {
        return useLocalCache;
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id) {
        return JSONObject.toJSONString(activityService.detailById(id));
    }
}
