package demo.nacos.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Value;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/9/11
 * @Desc
 */
@Service(version = "${demo.service.version}")
public class DemoServiceImpl implements DemoService {
    @Value("${demo.service.name}")
    private String serviceName;

    @Override
    public String sayName(String name) {
        RpcContext rpcContext=RpcContext.getContext();
        return String.format("Service [name :%s , port : %d] %s(\"%s\") : Hello,%s",
                serviceName,
                rpcContext.getLocalPort(),
                rpcContext.getMethodName(),
                name,
                name);
    }
}
