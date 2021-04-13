package com.ali.leb.thread.multithreading.pool.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: aliber
 * @Date: 2021/4/12 下午11:13
 **/

@Data
@Builder
@TableName("student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    @TableField("name")
    private String name;

    @TableField("sex")
    private String sex;

    @TableField("phone")
    private String phone;

    @TableField("create_time")
    private Date createTime;

}
