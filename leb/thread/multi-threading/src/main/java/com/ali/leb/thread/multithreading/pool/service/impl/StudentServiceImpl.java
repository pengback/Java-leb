package com.ali.leb.thread.multithreading.pool.service.impl;

import com.ali.leb.thread.multithreading.pool.bean.Student;
import com.ali.leb.thread.multithreading.pool.mapper.StudentMapper;
import com.ali.leb.thread.multithreading.pool.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: aliber
 * @Date: 2021/4/12 下午11:36
 **/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private StudentMapper studentMapper;

}
