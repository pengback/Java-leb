package com.ali.leb.bomd.reactiveprogram;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @Author: aliber
 * @Date: 2020/12/4 上午11:21
 **/

interface IMoneyFormat{
    String format(int i);
}

class MyMoney {

    private final int money;

    MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(IMoneyFormat moneyFormat) {
        System.out.println("我的存款: " + moneyFormat.format(money));
    }

    public void printMoneyTwo(Function<Integer, String> function) {
        System.out.println("我的存款: " + function.apply(money));
    }
}

public class MoneyDemo {
    public static void main(String[] args) {
        MyMoney my = new MyMoney(82345491);
        my.printMoney(a -> new DecimalFormat("#,###").format(a));

        Function<Integer, String> moneyFormat = i -> new DecimalFormat("#,###").format(i);
        // 函数接口的链式操作
        my.printMoneyTwo(moneyFormat.andThen(s -> "人民币 " + s));
    }
}
