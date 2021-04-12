package com.ali.leb.springcloud.nacosserverdemo.controller;

import com.ali.leb.springcloud.nacosserverdemo.server.DiffService;
import com.ali.leb.springcloud.nacosserverdemo.server.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: aliber
 * @Date: 2020/12/11 下午4:52
 **/
@Slf4j
@RestController
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @Resource
    private ProviderService providerService;

    private final DiffService diffService;

    ProviderController(DiffService diffService) {
        this.diffService = diffService;
    }

    @GetMapping("hello")
    public String hello() {
        log.info("send Hello");
//        return "Server port: "  + port + "----"  + this.providerService.sayHello();
        return "Server port: " + this.providerService.sayHello();
    }

    @GetMapping("difhello")
    public String difhello() {
        log.info("I say Hello");
        return "Server port: " + this.diffService.sayHello();
    }

    public String show() {
        return this.providerService.show();
    }

}
