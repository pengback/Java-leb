package com.ali.leb.demo.hello.modules.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: aliber
 * @Date: 2021/4/10 下午10:45
 **/
@RestController
@RequestMapping(value = "hello")
public class HelloController {

    @RequestMapping(value = "world")
    public String world() {
        return "Hello World";
    }

}
