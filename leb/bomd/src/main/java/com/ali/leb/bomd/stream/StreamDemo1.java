package com.ali.leb.bomd.stream;

import java.util.stream.IntStream;

/**
 * @Author: aliber
 * @Date: 2020/12/7 下午5:59
 **/
public class StreamDemo1 {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};

        // 外部迭代
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        System.out.println(sum);


        // 内部迭代
        // 中间操作: map; 中间操作就是产生流的操作.
        // 终止操作: sum; 终止操作是产生结果的操作.

        int sum2 = IntStream.of(nums).map(StreamDemo1::doubleSum).sum();
        System.out.println(sum2);

        System.out.println("惰性操作就是在终止没有调用的情况下, 中间操作不会执行.");
        IntStream.of(nums).map(StreamDemo1::doubleSum);

    }

    public static int doubleSum(int d) {
        System.out.println("数据乘以2了");
        return d * 2;
    }
}
