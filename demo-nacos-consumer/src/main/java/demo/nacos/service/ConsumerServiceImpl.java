package demo.nacos.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/9/11
 * @Desc
 */
@Service
@Slf4j
public class ConsumerServiceImpl {
    @Reference(version = "${demo.service.version}")
    private DemoService demoService;
    public String sayName(String name) {
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = demoService.sayName(name + i);
            log.info("nacos test,s:{}",s);
            list.add(s);
        }
        return JSONObject.toJSONString(list);
    }
}
