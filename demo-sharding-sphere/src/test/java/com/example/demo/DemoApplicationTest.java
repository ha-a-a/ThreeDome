package com.example.demo;

import com.example.demo.model.TbOrder;
import com.example.demo.service.TbOrderService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/4/5
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

    @Autowired
    TbOrderService tbOrderService;
    @Test
    public void save(){
        List<String> merchantList = Lists.newArrayList("aliyun", "taobao", "tmall");
        // 测试20笔订单，且所属商户随机产生
        for (int i = 0; i < 20; i++) {
            TbOrder order = new TbOrder();
            order.setUserId(i);
            order.setOrderNo(System.currentTimeMillis() + String.format("%06d", i));
            order.setOrderTime(new Date());
            order.setMerchant(merchantList.get(new Random().nextInt(merchantList.size())));
            tbOrderService.save(order);
        }
    }
}