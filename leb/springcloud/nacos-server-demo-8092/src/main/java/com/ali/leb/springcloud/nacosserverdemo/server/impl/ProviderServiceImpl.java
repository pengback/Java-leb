package com.ali.leb.springcloud.nacosserverdemo.server.impl;

import com.ali.leb.springcloud.nacosserverdemo.server.ProviderService;
import org.springframework.stereotype.Service;

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
}
