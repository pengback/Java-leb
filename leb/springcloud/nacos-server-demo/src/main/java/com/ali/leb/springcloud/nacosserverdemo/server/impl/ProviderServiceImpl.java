package com.ali.leb.springcloud.nacosserverdemo.server.impl;

import com.ali.leb.springcloud.nacosserverdemo.server.ProviderService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: aliber
 * @Date: 2020/12/11 下午5:03
 **/
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {
    @Override
    public String sayHello() {
        return "Hello, from provider";
    }

    @Override
    public String show() {
        return "show methods";
    }
}
