package com.ali.leb.provider8081;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: aliber
 * @Date: 2020/7/31 5:07 下午
 **/
@SpringBootApplication
@EnableEurekaServer
public class Provider8081 {
    public static void main(String[] args) {
        SpringApplication.run(Provider8081.class, args);
    }
}
