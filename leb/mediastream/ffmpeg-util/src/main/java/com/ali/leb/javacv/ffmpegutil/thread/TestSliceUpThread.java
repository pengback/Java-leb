package com.ali.leb.javacv.ffmpegutil.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Callable;

/**
 * @Author: aliber
 * @Date: 2020/12/29 下午4:59
 **/
@Slf4j
public class TestSliceUpThread implements Callable<AsyncResult<Boolean>> {

    private boolean flag = false;

    @SneakyThrows
    @Override
    public AsyncResult call() {
        log.info("[test1]方法开始");
        Thread.sleep(10000);
        log.info("[test1]开始设置flag");
        this.flag = true;
        Thread.sleep(10000);
        log.info("[test1]结束运行");
        return new AsyncResult<>(true);
    }

    public boolean changeFlag() {
        this.flag = !flag;
        return this.flag;
    }

    public boolean checkFlag() {
        return this.flag;
    }
}
