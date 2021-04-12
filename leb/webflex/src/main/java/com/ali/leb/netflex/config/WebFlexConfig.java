package com.ali.leb.netflex.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.WebHandler;

/**
 * @Author: aliber
 * @Date: 2020/12/1 下午11:05
 **/
@Configuration
@ComponentScan
@EnableWebFlux
public class WebFlexConfig implements WebFluxConfigurer {

    @Bean
    public WebHandler webHandler(ApplicationContext applicationContext) {
        DispatcherHandler dispatcherHandler = new DispatcherHandler(applicationContext);
        return dispatcherHandler;
    }

}
