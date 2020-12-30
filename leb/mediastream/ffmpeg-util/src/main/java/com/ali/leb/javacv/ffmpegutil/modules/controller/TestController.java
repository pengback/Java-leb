package com.ali.leb.javacv.ffmpegutil.modules.controller;

import com.ali.leb.javacv.ffmpegutil.bean.AsyncTestBean;
import com.ali.leb.javacv.ffmpegutil.thread.FfmpegSliceUpThread;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: aliber
 * @Date: 2020/12/29 下午4:38
 **/
@RestController
@Slf4j
public class TestController {

    @Resource
    private AsyncTestBean asyncTestBean;

    @Resource
    private ThreadPoolTaskExecutor executor;

    @GetMapping("test")
    public String test() {
//        AsyncTestBean b = new AsyncTestBean();
        asyncTestBean.test1();
        asyncTestBean.test2();
        return "finsh";
    }

    @GetMapping("test2")
    public String test2() throws InterruptedException {
        String filepath = "/Users/aliber/workspace/ze/example/media";
        String filename = "chuan";
        FfmpegSliceUpThread thread = new FfmpegSliceUpThread(filepath, filename);

        ListenableFuture<AsyncResult<Boolean>> submit = executor.submitListenable(thread);
        submit.addCallback(new ListenableFutureCallback<AsyncResult<Boolean>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("线程切片异常", throwable);
            }

            @SneakyThrows
            @Override
            public void onSuccess(AsyncResult<Boolean> booleanAsyncResult) {
                if (booleanAsyncResult.get()) {
                    log.info("[controller]线程切片正常结束");
                    // todo
                } else {
                    // todo 切片失败,需要做相应的业务逻辑处理;
                    log.error("[controller]线程切片异常结束");
                }
            }
        });

        while (!thread.checkFlag()) {
            log.info("controller 还未接收到成功消息");
            Thread.sleep(1000);
        }

        log.info("finsh test 2");
        return "finsh test 2";
    }

}
