package demo.dubbo.service.adaptive.impl;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import demo.dubbo.service.adaptive.AdaptiveExt1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/7/8
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboAdaptiveExt1Test {

    /**
     * 接口的@SPI注解中有value值,url中也有key-value，以及拓展点实现类上有@Adative注解激活指定实现类
     * 优先级：实现类上加@Adaptive > url中(key与接口方法中@Adaptive中的key相同 > 任意key) > @SPI中的value值
     */
    @Test
    public void echo() {
        ExtensionLoader<AdaptiveExt1> extensionLoader = ExtensionLoader.getExtensionLoader(AdaptiveExt1.class);
        AdaptiveExt1 adaptiveExtension = extensionLoader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test")
//                .addParameter("t","dubbo");
        // 需要配置接口方法中@Adaptive中的key
                .addParameter("adapterKey","cloud");
        String dubbo = adaptiveExtension.echo("dubbo", url);
        System.out.printf("result:"+dubbo);

    }
}