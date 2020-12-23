package com.ali.leb.bomd.designpattern.responsibilitychain;



/**
 * @Author: aliber
 * @Date: 2020/12/23 上午11:59
 **/
public class UseMain {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();

        handler1.setNextHandler(handler2);
        handler1.handerRequest("two");
    }
}
