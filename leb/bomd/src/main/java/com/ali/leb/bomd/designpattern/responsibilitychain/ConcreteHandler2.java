package com.ali.leb.bomd.designpattern.responsibilitychain;

/**
 * @Author: aliber
 * @Date: 2020/12/23 上午11:54
 **/
public class ConcreteHandler2 extends Handler {
    @Override
    public void handerRequest(String request) {
        if ("two".equals(request)) {
            System.out.println("处理者2负责");
        } else{
            if (this.getNextHandler() != null) {
                this.getNextHandler().handerRequest(request);
            } else {
                System.out.println("没有人处理");
            }
        }
    }
}
