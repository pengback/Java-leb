package com.ali.leb.bomd.lambda;

import java.util.function.*;

/**
 * lambda表达式的方法引用设置.
 * @Author: aliber
 * @Date: 2020/12/6 上午4:38
 **/
class Dog{
    private String name = "Wang";
    /**
     * 默认狗粮数目
     */
    private int food = 10;

    Dog() {

    }

    Dog(String n) {
        this.name = n;
    }

    public static void bark(Dog dog) {
        System.out.println(dog + "叫了");
    }

    /**
     * 吃狗粮
     * @param num
     * @return 返回剩余的狗粮数目
     */
    public int eat(Dog this, int num) {
        System.out.println("吃了" + num + "斤狗粮");
        this.food -= num;
        return this.food;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class MethodRefencesDemo {

    public static void main(String[] args) {
        // 常规lambda表达式写法
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("consumer");
        // 方法引用写法, 下面等价于上面
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("Consumer One");

        // 方法引用的各种形式;

        // 1.静态方法的方法引用
        Consumer<Dog> dogConsumer = Dog::bark;
        Dog dog1 = new Dog("gugu");
        dogConsumer.accept(new Dog());
        dogConsumer.accept(dog1);

        // 2. 非静态方法,使用对象实例的方法引用
        //        Function<Integer, Integer> function = dog1::eat;
        //        UnaryOperator<Integer> function = dog1::eat;
        //        System.out.println("还剩下" + function.apply(2) + "斤");

        IntUnaryOperator function = dog1::eat;
        dog1 = null;
        System.out.println("还剩下" + function.applyAsInt(2) + "斤");

        // 使用类名的来方法引用
        BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
        System.out.println("还剩下" + eatFunction.apply(dog1, 2) + "斤");

        // 构造函数的发放引用
        Supplier<Dog> supplier = Dog::new;
        System.out.println(supplier.get());

        // 带有参数的构造函数
        Function<String, Dog> function2 = Dog::new;
        System.out.println(function2.apply("旺财"));

    }

}
