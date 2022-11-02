package org.master.qrcode;

import java.util.List;


public interface QrCodeService {

    /**
     * 根据二维码图片地址解析二维码信息
     * @param url 图片地址
     * @return 二维码信息
     */
    List<String> decodeQrCode(String url);
}
