package demo.dubbo.service.adaptive.impl;

import com.alibaba.dubbo.common.URL;
import demo.dubbo.service.adaptive.AdaptiveExt1;
/**
 *@author  mengyuetang
 *@date  2020/7/8
 */
public class ThriftAdaptiveExt1 implements AdaptiveExt1 {
    @Override
    public String echo(String msg, URL url) {
        return "thrift";
    }
}
