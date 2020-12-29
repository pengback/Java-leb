package com.ali.leb.bomd.asserts;

import cn.hutool.core.lang.Assert;

/**
 * @Author: aliber
 * @Date: 2020/12/24 上午11:00
 **/
public class AssertDemo1 {

    public static void main(String[] args) {
        String v = null;
        try {
            Assert.notNull(v, "您没有输入必须的参数");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
