package demo.nacos.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/9/9
 * @Desc
 */
@RestController
@RequestMapping("/discovery")
public class NacosDiscoveryController {
    @NacosInjected
    private NamingService namingService;
    @GetMapping("/get")
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
}
