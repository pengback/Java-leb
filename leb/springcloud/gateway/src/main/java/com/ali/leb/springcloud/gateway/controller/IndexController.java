package com.ali.leb.springcloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: aliber
 * @Date: 2020/12/14 下午11:31
 **/
@RestController
public class IndexController {

    @RequestMapping("index")
    public String index() {
        return "This is Gateway";
    }

    @RequestMapping("fallback")
    public String fallback() {
        return "gateway fallback";
    }
}
