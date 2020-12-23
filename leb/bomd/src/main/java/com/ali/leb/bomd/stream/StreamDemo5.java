package com.ali.leb.bomd.stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author: aliber
 * @Date: 2020/12/20 上午11:51
 **/
public class StreamDemo5 {


    public static void main(String[] args) {
        // 调用parallel 产生一个并行流
        // 疑问 parallel 并行流的默认现场数的多少[]? 线程数是否可以手动设置?
        // parallelStream 默认使用了fork-join框架, 其默认线程数是CPU核心数.
        IntStream.range(1, 100).parallel().peek(StreamDemo5::print).count();

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream().peek(StreamDemo5::print).count();

        // 改变默认线程数量的2中方式;
        // 方法一: 全局设置: (不推荐使用) 一般不建议修改，因为修改虽然改进当前的业务逻辑，
        // 但对于整个项目中其它地方只是用来做非耗时的并行流运算，性能就不友好了
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelis", "6");

        // 方法二: 引入Fork-join PoolSSss sss
        IntStream range = IntStream.range(1, 100000);
        new ForkJoinPool().submit(() -> range.parallel().forEach(StreamDemo5::print));

        // 现在要实现这样一个效果: 先并行,再串行
        // 多次调用 parallel / sequential , 以最后一次为准
        IntStream.range(1, 100)
                // 调用parallel产生并行流
                .parallel().peek(StreamDemo5::print)
                // 调用sequential产生串行流
                .sequential().peek(StreamDemo5::print2)
                .count();

        // 并行流使用的线程池: ForkJoinPool.commonPool
        // 默认的线程数是当前机器的CPU个数
        // 使用下面这个属性可以修改默认的线程数
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelis", "6");
        IntStream.range(1, 100).parallel().peek(StreamDemo5::print).count();

        // 使用自己的线程池, 不是用默认的线程池, 防治任务被阻塞
        ForkJoinPool pool = new ForkJoinPool(20);
        pool.submit(() -> IntStream.range(1, 100).parallel().peek(StreamDemo5::print).count());
        pool.shutdown();

        // 主线程等待
        synchronized (pool) {
            try {
                pool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void print(int i) {
        System.out.println(Thread.currentThread().getName() + " print: " + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void print2(int i) {
        System.err.println("print: " + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
