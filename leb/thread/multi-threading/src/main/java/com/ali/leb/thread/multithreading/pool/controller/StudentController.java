package com.ali.leb.thread.multithreading.pool.controller;

import com.ali.leb.thread.multithreading.pool.bean.Student;
import com.ali.leb.thread.multithreading.pool.service.AsyncService;
import com.ali.leb.thread.multithreading.pool.service.StudentService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: aliber
 * @Date: 2021/4/13 下午1:35
 **/
@RestController
@RequestMapping("student")
@Slf4j
public class StudentController {

    @Resource
    private StudentService studentService;

    @Resource
    private AsyncService asyncService;

    @Resource(name = "threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @RequestMapping("testSave")
    public String save() {
        Student stu = studentService.saveStudent();
        return JSONObject.toJSONString(stu);
    }

    @RequestMapping("asyncTest")
    public void asyncTest() {
        for (int i = 0; i < 20; i++) {
            asyncService.asyncOne();
        }
        log.info("controller内容结束");
    }

    @RequestMapping("checkPools")
    public String checkPools() {
        Map map = new HashMap();
        Object[] myThread = {threadPoolTaskExecutor};
        for (Object thread : myThread) {
            ThreadPoolTaskExecutor threadTask = (ThreadPoolTaskExecutor) thread;
            ThreadPoolExecutor threadPoolExecutor = threadTask.getThreadPoolExecutor();
            System.out.println("提交任务数" + threadPoolExecutor.getTaskCount());
            System.out.println("完成任务数" + threadPoolExecutor.getCompletedTaskCount());
            System.out.println("当前有" + threadPoolExecutor.getActiveCount() + "个线程正在处理任务");
            System.out.println("还剩" + threadPoolExecutor.getQueue().size() + "个任务");
            map.put("提交任务数-->", threadPoolExecutor.getTaskCount());
            map.put("完成任务数-->", threadPoolExecutor.getCompletedTaskCount());
            map.put("当前有多少线程正在处理任务-->", threadPoolExecutor.getActiveCount());
            map.put("还剩多少个任务未执行-->", threadPoolExecutor.getQueue().size());
            map.put("当前可用队列长度-->", threadPoolExecutor.getQueue().remainingCapacity());
            map.put("当前时间-->", Calendar.getInstance());
        }
        return JSONObject.toJSONString(map);
    }


}
