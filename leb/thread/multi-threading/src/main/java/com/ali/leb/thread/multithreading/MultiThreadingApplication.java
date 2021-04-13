package com.ali.leb.thread.multithreading;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ali.leb.thread.multithreading")
@MapperScan("com.ali.leb.thread.multithreading.**.mapper")
public class MultiThreadingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiThreadingApplication.class, args);
    }

}
