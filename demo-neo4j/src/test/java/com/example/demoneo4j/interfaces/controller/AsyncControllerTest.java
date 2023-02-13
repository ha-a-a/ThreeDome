package com.example.demoneo4j.interfaces.controller;

import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.*;
import org.asynchttpclient.util.HttpConstants;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

/**
 * @author tangmengyue
 * @ClassName AsyncControllerTest.java
 * @Description TODO
 * @createTime 2023年02月09日 16:32:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class AsyncControllerTest {

    /**
     * 客户端
     *
     * @param i
     * @return
     */
    private Request createUnboundRequest(int i) {
        String url = "http://localhost:8080/neo4j-demo/response/async/callable?time=" + i * 1000 + "&operate=ttttt";
        return new RequestBuilder(HttpConstants.Methods.GET).setUrl(url).build();
    }

    private BoundRequestBuilder createBoundRequest(int i, AsyncHttpClient asyncHttpClient) {
        String url = "http://localhost:8080/neo4j-demo/response/async/callable?time=" + i * 1000 + "&operate=ttttt";
        return asyncHttpClient.prepareGet(url);
    }

    private AsyncHttpClient createAsyncHttpClient() {
        DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config().setConnectTimeout(5 * 10000);
        return Dsl.asyncHttpClient(clientBuilder.build());
    }

    @Test
    public void syncCallable() throws InterruptedException, ExecutionException {
        AsyncHttpClient asyncHttpClient = createAsyncHttpClient();
        for (int i = 0; i < 10; i++) {
            log.info("{} aysnc start .....", i);
            ListenableFuture<Response> responseListenableFuture = asyncHttpClient.executeRequest(createUnboundRequest(i));
            Response response = responseListenableFuture.get();
            log.info("{} aysnc request over ..... result={}", i, response.getResponseBody());

        }
        Thread.sleep(10 * 1000);
    }

    @Test
    public void asyncCallable() throws InterruptedException {
        AsyncHttpClient asyncHttpClient = createAsyncHttpClient();
        for (int i = 0; i < 10; i++) {
            BoundRequestBuilder request = createBoundRequest(i, asyncHttpClient);
            log.info("{} aysnc start .....", i);
            int count = i;
            request.execute(new AsyncCompletionHandler<String>() {
                @Override
                public String onCompleted(Response response) throws Exception {
                    String body = response.getResponseBody();
                    log.info("request completed. i={},body={}", count, body);
                    return body;
                }
            });
        }
        Thread.sleep(20 * 1000);
    }


}