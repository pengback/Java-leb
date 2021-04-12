package com.ali.leb.thread.multithreading.pool.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: aliber
 * @Date: 2021/4/12 下午11:13
 **/

@Data
@TableName("student")
public class Student implements Serializable {

    @TableField("id")
    private long id;

    @TableField("name")
    private String name;

    @TableField("sex")
    private String sex;

    @TableField("phone")
    private String phone;

    @TableField("create_time")
    private Date createTime;

}
