package com.ali.leb.thread.multithreading.pool.service;

import com.ali.leb.thread.multithreading.pool.bean.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: aliber
 * @Date: 2021/4/12 下午11:34
 **/
public interface StudentService extends IService<Student> {

    /**
     * 测试保存
     *
     * @return
     */
    Student saveStudent();

    /***
     * 根据name创建
     * @param name
     * @return
     */
    Student saveByName(String name);

}
