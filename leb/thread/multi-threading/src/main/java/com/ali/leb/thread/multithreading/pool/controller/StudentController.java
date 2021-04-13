package com.ali.leb.thread.multithreading.pool.controller;

import com.ali.leb.thread.multithreading.pool.bean.Student;
import com.ali.leb.thread.multithreading.pool.service.StudentService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: aliber
 * @Date: 2021/4/13 下午1:35
 **/
@RestController
@RequestMapping("student")
public class StudentController {

    @Resource
    private StudentService studentService;


    @RequestMapping("testSave")
    public String save() {
        Student stu = studentService.saveStudent();
        return JSONObject.toJSONString(stu);
    }

}
