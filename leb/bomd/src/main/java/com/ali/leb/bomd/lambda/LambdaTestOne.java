package com.ali.leb.bomd.lambda;

/**
 * @Author: aliber
 * @Date: 2020/12/4 上午11:04
 **/

/**
 * 函数接口: 只有一个实现方法,
 */
@FunctionalInterface
interface InterfaceOne {
    int doubleNum(int i);

    default int add (int i, int j) {
        return i + j;
    }
}

@FunctionalInterface
interface InterfaceTwo {
    int doubleNum(int i);

    default int add (int i, int j) {
        return i + j;
    }
}

@FunctionalInterface
interface InterfaceThree extends InterfaceOne, InterfaceTwo {
    @Override
    int doubleNum(int i);

    @Override
    default int add(int i, int j) {
        return InterfaceOne.super.add(i, j);
    }

}

public class LambdaTestOne {
    public static void main(String[] args) {
        // before jdk8
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("OK");
            }
        });
        // after jdk8
        new Thread(() -> System.out.println("OK")).start();


        InterfaceOne i1 = (i) -> {
            System.out.println("===============");
            return i * 2;
        };
        System.out.println(i1.doubleNum(3));

        InterfaceOne i2 = (i) -> i * 2;

        InterfaceOne i3 = i -> i * 2;

        InterfaceOne i4 = (int i) -> i * 2;



    }
}
