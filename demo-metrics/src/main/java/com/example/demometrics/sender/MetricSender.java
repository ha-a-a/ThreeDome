package com.example.demometrics.sender;

import com.google.common.net.HttpHeaders;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xerial.snappy.Snappy;

import java.util.concurrent.TimeUnit;

/**
 * @author tangmengyue
 * @Description
 * @createTime 2023年07月14日 15:31:00
 */
public class MetricSender {
    private Logger log = LoggerFactory.getLogger(MetricSender.class);
    public static final MediaType MEDIA_TYPE_PROTO = MediaType.parse("application/x-protobuf");
    private static final OkHttpClient OK_HTTP_CLIENT = newOkHttpClient();
    private static final String url = "http://127.0.0.1:17000/prometheus/v1/write";


    private static OkHttpClient newOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(3000L, TimeUnit.MILLISECONDS)
                .readTimeout(3000L, TimeUnit.MILLISECONDS)
                .writeTimeout(3000L, TimeUnit.MILLISECONDS)
                .build();
    }

    public void send(Remote.WriteRequest writeRequest) {
        try {
            RequestBody requestBody = RequestBody.create(MEDIA_TYPE_PROTO, Snappy.compress(writeRequest.toByteArray()));
            doSendByRemoteWrite(url, requestBody);
        } catch (Exception e) {
            log.error("send to prometheus error", e);
        }
    }

    private void doSendByRemoteWrite(String url, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Basic c25zLW9wOnNucy1vcA==")
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            log.debug("response code: {}, body: {}", response.code(), responseBody != null ? responseBody.string() : null);
        } catch (Exception e) {
            log.error("send to prometheus error", e);
        }
    }
}
