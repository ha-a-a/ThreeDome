package demo.nacos.listener;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangmengyue
 * @ClassName NacosCustomListener.java
 * @Description TODO
 * @createTime 2023年03月23日 15:09:00
 */
@Configuration
@Slf4j
public class NacosCustomListener {

    @NacosConfigListener(dataId = "test_data.yml",
            groupId = "test_group",
            type = ConfigType.YAML)
    public void receiveConfigInfo(String config) {
        log.info("config={}", config);
    }
}
