package com.ali.leb.springcloud.nacosserverdemo.controller;

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

    @GetMapping("hello")
    public String hello() {
        log.info("send Hello");
        return "Server port : " + port + "----" + providerService.sayHello();
    }

}
