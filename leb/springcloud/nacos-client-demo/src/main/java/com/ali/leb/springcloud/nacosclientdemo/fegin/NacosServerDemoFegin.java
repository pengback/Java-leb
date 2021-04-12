package com.ali.leb.springcloud.nacosclientdemo.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: aliber
 * @Date: 2020/12/11 下午6:04
 **/

@FeignClient("nacos-server-demo")
public interface NacosServerDemoFegin {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    String hello();

    @RequestMapping(value = "show", method = RequestMethod.GET)
    String show();

}
