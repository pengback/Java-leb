package com.ali.leb.bomd.lambda;

import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @Author: aliber
 * @Date: 2020/12/5 下午1:51
 **/
public class FuntionDemo {

    public static void main(String[] args) {
        // 断言函数接口
        Predicate<Integer> integerPredicate = i -> i > 0;
        // IntPredicate
        System.out.println(integerPredicate.test(19));
        System.out.println(integerPredicate.test(-8));

        // 消费接口
        Consumer<String> consumer = s -> System.out.println("Consumer: " + s);
        consumer.accept("Hahahahahaha");
    }


}
