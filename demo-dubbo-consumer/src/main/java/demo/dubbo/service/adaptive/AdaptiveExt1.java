package demo.dubbo.service.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/7/8
 * @Desc
 */
@SPI("thrift")
public interface AdaptiveExt1 {
    @Adaptive({"adapterKey"})
    String echo(String msg, URL url);
}
