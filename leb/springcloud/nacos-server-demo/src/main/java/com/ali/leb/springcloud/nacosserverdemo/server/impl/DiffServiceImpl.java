package com.ali.leb.springcloud.nacosserverdemo.server.impl;

import com.ali.leb.springcloud.nacosserverdemo.server.DiffService;

/**
 * @Author: aliber
 * @Date: 2020/12/22 下午5:21
 **/
public class DiffServiceImpl implements DiffService {

    @Override
    public String sayHello() {
        return "I say Hello";
    }
}
