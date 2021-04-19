package com.ali.leb.thread.multithreading.pool.service;

import com.ali.leb.thread.multithreading.pool.bean.Student;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: aliber
 * @Date: 2021/4/12 下午10:34
 **/
@Service
@Slf4j
public class AsyncService {

    @Resource
    private StudentService studentService;

    int LOGIC_NUM = 0;

    @Async("threadPoolTaskExecutor")
    public void asyncOne() {
        String threadName = Thread.currentThread().getName();
        int threadNum = 0;
        while( threadNum <= 10000) {
            Student stu = studentService.saveByName(threadName + "-" + LOGIC_NUM);
            log.info(threadName + "创建对象：" + JSONObject.toJSONString(stu));
            threadNum++;
            LOGIC_NUM++;
        }
        log.info("线程：" + threadName + "创建对象 " + threadNum + "个");
    }

}
