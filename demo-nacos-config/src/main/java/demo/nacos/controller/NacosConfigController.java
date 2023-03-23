package demo.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
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

    @NacosValue(value = "${test.data.example}")
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
}
