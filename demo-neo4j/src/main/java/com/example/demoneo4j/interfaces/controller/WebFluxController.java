package com.example.demoneo4j.interfaces.controller;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author tangmengyue
 * @ClassName WebFluxController.java
 * @Description TODO
 * @createTime 2023年03月06日 16:45:00
 */
@RestController
@RequestMapping("/webflux")
@Slf4j
public class WebFluxController {
    //    http://localhost:8080/webflux/hello,不需要path-content
    @GetMapping("/hello")
    public String sayHello() {
        return "hello webflux";
    }

    // 阻塞响应
    @GetMapping("/sync/name/get")
    public String getNameBySync(String name) {
        log.info("getPersonBySync before,name={}", name);
        String res = this.execute(name);
        log.info("getPersonBySync after,name={}", name);
        return res;
    }

    @GetMapping("/async/name/get")
    public Mono<String> getNameByAsync(String name) {
        log.info("getPersonByAsync before,name={}", name);
        Mono<String> stringMono = Mono.fromSupplier(() -> this.execute(name));
        log.info("getPersonByAsync after,name={}", name);
        return stringMono;
    }

    private String execute(String name) {
        try {
            TimeUnit.SECONDS.sleep(5);
            log.info("{} execute finish!!!", name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }
}


