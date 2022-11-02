package com.example.demo.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * @author mengyuetang
 */
@Slf4j
@Service
public class QrCodeServiceImpl implements org.master.qrcode.QrCodeService {

    private static final int MAX_QRCODE_SIZE = 1000;

    @Override
    public List<String> decodeQrCode(String url) {
        if (Strings.isBlank(url)) {
            return null;
        }
        List<String> resultList = new ArrayList();
        BufferedImage image;
        try {
            File file = new File(url);
            image = ImageIO.read(file);
            resultList = readToString(image);
        } catch (Exception e) {
            log.error("readToString failed", e);
        }

        return resultList;
    }


    private List<String> readToString(BufferedImage sourceImage) {
        BufferedImage image = toGrayImage(sourceImage);
        // second
        if (sourceImage.getWidth() > MAX_QRCODE_SIZE && sourceImage.getHeight() > MAX_QRCODE_SIZE) {
            image = resizeToMaxSize(sourceImage);
        }
        List<String> result = readDirectly(image);
        if (!CollectionUtils.isEmpty(result)) {
            return result;
        }
        int minSize = 170;
        int imgSize = Math.min(image.getWidth(), image.getHeight());
        int level = 1;
        while (imgSize > minSize) {
            BufferedImage newImage = new BufferedImage((int) (image.getWidth() * Math.pow(0.9, level)),
                    (int) (image.getHeight() * Math.pow(0.9, level)), image.getType());
            newImage.getGraphics().drawImage(image, 0, 0, newImage.getWidth(), newImage.getHeight(), 0, 0, image.getWidth(),
                    image.getHeight(), null);
            result = readDirectly(newImage);
            if (!CollectionUtils.isEmpty(result)) {
                return result;
            }
            imgSize = Math.min(newImage.getWidth(), newImage.getHeight());
            level++;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 将图片转成灰阶。
     *
     * @param image 图片buffer
     * @return result
     */
    private BufferedImage toGrayImage(BufferedImage image) {
        BufferedImage result = image;
        if (BufferedImage.TYPE_BYTE_GRAY != image.getType()) {
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            newImage.getGraphics().drawImage(image, 0, 0, null);
            result = newImage;
        }
        return result;
    }

    /**
     * 图片若过大，则缩放图片。
     *
     * @param image 图片buffer
     * @return result
     */
    private BufferedImage resizeToMaxSize(BufferedImage image) {
        int height = MAX_QRCODE_SIZE;
        int width = MAX_QRCODE_SIZE;
        if (image.getWidth() > image.getHeight()) {
            width = (int) (height * (((double) image.getWidth()) / image.getHeight()));
        } else {
            height = (int) (width * (((double) image.getHeight()) / image.getWidth()));
        }
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        newImage.getGraphics().drawImage(image, 0, 0, newImage.getWidth() + 1,
                newImage.getHeight() + 1, 0, 0, image.getWidth(), image.getHeight(), null);
        return newImage;
    }

    private List<String> readDirectly(BufferedImage image) {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Map<DecodeHintType, Object> hints = Collections.singletonMap(DecodeHintType.CHARACTER_SET, "UTF-8");
        List<String> resultList = new ArrayList<>();
        String res;
        try {
            Result[] results = new QRCodeMultiReader().decodeMultiple(binaryBitmap, hints);
            for (Result result : results) {
                 res = result.getText();
                if (StringUtils.isNotBlank(res)) {
                    resultList.add(res);
                }
            }
            return resultList;
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }
}
