package com.ali.leb.netflex;

import com.ali.leb.netflex.config.WebFlexConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;


/**
 * @Author: aliber
 * @Date: 2020/11/6 5:20 下午
 **/
@SpringBootApplication(scanBasePackages = {"com.ali.leb.netflex"})
public class WebflexApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(WebflexApplication.class, args);

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebFlexConfig.class);
//        HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(applicationContext).build();
//        ReactorHttpHandlerAdapter httpHandlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);
//        HttpServer.create().host("localhost").port(8081).handle(httpHandlerAdapter).bindNow();
//        System.in.read();
    }
}
