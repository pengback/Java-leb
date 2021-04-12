package com.ali.leb.netflex.spring.webflex.springwebflex.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author: aliber
 * @Date: 2020/12/2 上午10:16
 **/
@RestController
@RequestMapping("api")
@Slf4j
public class IndexController {

    @RequestMapping("index")
    public Mono<Object> index() {
        return Mono.create(monoSink -> {
            log.info("创建Mono");
            monoSink.success("Hello WebFlex");
        }).doOnSubscribe(subscription -> {
            //当订阅者去订阅发布者的时候，该方法会调用
            log.info("doOnSubscribe: {}",subscription);
        }).doOnNext(o -> {
            //当订阅者收到数据时，改方法会调用
            log.info("doOnNext: {}",o);
        });
    }

    @RequestMapping("flux")
    public Flux<String> flex() {
        return Flux.just("Hello", "this", "is", "flux");
    }

}
