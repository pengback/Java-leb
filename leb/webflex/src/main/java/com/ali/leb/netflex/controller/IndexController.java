package com.ali.leb.netflex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: aliber
 * @Date: 2020/12/1 下午11:10
 **/
@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping("/index")
    public Mono<String> index() {
        return Mono.just("index");
    }
}
