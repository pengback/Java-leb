package com.ali.leb.thread.multithreading.pool.service.impl;

import com.ali.leb.thread.multithreading.pool.bean.Student;
import com.ali.leb.thread.multithreading.pool.mapper.StudentMapper;
import com.ali.leb.thread.multithreading.pool.service.StudentService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * @Author: aliber
 * @Date: 2021/4/12 下午11:36
 **/
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    @Transactional
    public Student saveStudent() {
        Student stu = Student.builder().name("test save Student").createTime(Calendar.getInstance().getTime()).build();
        log.info(JSONObject.toJSONString(stu));
        this.save(stu);
        return stu;
    }

    @Override
    public Student saveByName(String name) {
        Student stu = Student.builder().name(name).createTime(Calendar.getInstance().getTime()).build();
        this.save(stu);
        return stu;
    }
}
