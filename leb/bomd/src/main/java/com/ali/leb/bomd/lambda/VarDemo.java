package com.ali.leb.bomd.lambda;

import java.util.function.Consumer;

/**
 * 变量引用
 * @Author: aliber
 * @Date: 2020/12/6 下午11:13
 **/
public class VarDemo {

    public static void main(String[] args) {
        String str = "time is: ";
        // str = "" // 编译报错, lambda表达式中的值应该是final类型的, 因为java传参传的是值, 不是引用
        Consumer<String> consumer = s -> System.out.println(str + s);
        consumer.accept("12:23:03");
    }

}
