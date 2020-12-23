package com.ali.leb.bomd.reactiveprogram;

import java.util.stream.IntStream;

/**
 * 最小值 提现 函数式编程 和 命令式编程 的区别
 *
 * @Author: aliber
 * @Date: 2020/12/4 上午10:51
 **/
public class MiniDemo {

    public static void main(String[] args) {

        // 命令式编程
        int[] nums = {33, 44, 55, 1, 34, -19, 65};
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            if ( i < min) {
                min = i;
            }
        }
        System.out.println(min);

        // 函数式编程
        // JDK8
        int min2 = IntStream.of(nums).min().getAsInt();
        System.out.println(min2);

    }

}
