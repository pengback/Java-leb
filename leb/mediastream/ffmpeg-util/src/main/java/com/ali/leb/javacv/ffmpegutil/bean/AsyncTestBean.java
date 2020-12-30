package com.ali.leb.javacv.ffmpegutil.bean;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Author: aliber
 * @Date: 2020/12/29 下午4:25
 **/
@Slf4j
@Component
public class AsyncTestBean {

    private boolean flag = false;

    @SneakyThrows
    @Async
    public void test1() {
        log.info("[test1]方法开始");
        Thread.sleep(10000);
        log.info("[test1]开始设置flag");
        this.flag = true;
        Thread.sleep(10000);
        log.info("[test1]结束运行");
    }

    @SneakyThrows
    @Async
    public Future<String> test2() {
        log.info("[test2]方法开始");
        log.info("[test2]开始监听flag");
        while(!flag) {
            log.info("[test2]正在监听flaging");
            Thread.sleep(1000);
        }
        log.info("[test2]flag的值发生了改变,监听结束");
//        return "[test2]flag的值发生了改变,监听结束";
        return new AsyncResult<>("[test2]flag的值发生了改变,监听结束");
    }

}
