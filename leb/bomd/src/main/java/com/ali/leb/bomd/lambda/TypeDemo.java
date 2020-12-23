package com.ali.leb.bomd.lambda;

/**
 * lambda表达式中的类型推断
 * @Author: aliber
 * @Date: 2020/12/6 下午11:02
 **/

interface IMath {
    int add(int x, int y);
}

interface IMath2 {
    int sub(int x, int y);
}

public class TypeDemo {

    public static void main(String[] args) {
        // 变量类型定义
        IMath lambda = (x, y) -> x + y;

        // 数组里
        IMath[] lambdas = { (x, y) -> x + y };

        // 强制
        Object lambda2 = (IMath) (x, y) -> x + y;

        // 通过返回类型
        IMath createLambda = createLambda();

        TypeDemo td = new TypeDemo();
        //  td.test(((x, y) -> x + y));
        td.test((IMath2) (x, y) -> x + y);
    }

    public void test(IMath math) {

    }

    public void test(IMath2 math2) {

    }

    public static IMath createLambda() {
        return (x, y) -> x + y;
    }

}
