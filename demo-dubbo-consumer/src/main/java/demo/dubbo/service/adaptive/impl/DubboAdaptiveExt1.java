package demo.dubbo.service.adaptive.impl;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import demo.dubbo.service.adaptive.AdaptiveExt1;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/7/8
 * @Desc
 */
@Adaptive
public class DubboAdaptiveExt1 implements AdaptiveExt1 {
    @Override
    public String echo(String msg, URL url) {
        return "dubbo";
    }
}
