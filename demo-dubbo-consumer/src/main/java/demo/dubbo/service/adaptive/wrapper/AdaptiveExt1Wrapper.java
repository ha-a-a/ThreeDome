package demo.dubbo.service.adaptive.wrapper;

import com.alibaba.dubbo.common.URL;
import demo.dubbo.service.adaptive.AdaptiveExt1;
import lombok.extern.slf4j.Slf4j;

/**
 * 扩展点实现包装类,@Adaptive在实现类上时，该实现类不会被包装
 *@author  mengyuetang
 *@date  2020/7/9
 */
@Slf4j
public class AdaptiveExt1Wrapper implements AdaptiveExt1 {
    private AdaptiveExt1 adaptiveExt1;

    public AdaptiveExt1Wrapper(AdaptiveExt1 adaptiveExt1) {
        this.adaptiveExt1 = adaptiveExt1;
    }

    @Override
    public String echo(String msg, URL url) {
        log.info("before echo in wrapper, adaptiveExt1:{}", adaptiveExt1.getClass().getName());
        return adaptiveExt1.echo(msg,url);
    }
}
