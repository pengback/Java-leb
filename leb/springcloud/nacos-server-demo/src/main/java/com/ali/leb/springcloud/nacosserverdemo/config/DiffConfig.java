package com.ali.leb.springcloud.nacosserverdemo.config;

import com.ali.leb.springcloud.nacosserverdemo.server.DiffService;
import com.ali.leb.springcloud.nacosserverdemo.server.impl.DiffServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: aliber
 * @Date: 2020/12/22 下午5:23
 **/
@Configuration
public class DiffConfig {

    @Bean
    public DiffService diffService() {
        DiffService diffService = new DiffServiceImpl();
        return diffService;
    }

}
