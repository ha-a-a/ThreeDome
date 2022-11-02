package com.example.demo.qrcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2019/9/9
 * @Desc
 */
@SpringBootTest
@RunWith(value = SpringRunner.class)
@Slf4j
public class QrCodeServiceImplTest {

    @Autowired
    org.master.qrcode.QrCodeService qrCodeService;
    @Test
    public void decodeQrCode() {
        String url="D:\\project\\测试图片\\微信图片_20190909163612.png";
        List<String> decodeQrCode = qrCodeService.decodeQrCode(url);
        log.info("decodeQrCode:{}",decodeQrCode);
    }
}