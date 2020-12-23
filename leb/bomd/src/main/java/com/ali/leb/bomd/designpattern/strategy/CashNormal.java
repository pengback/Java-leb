package com.ali.leb.bomd.designpattern.strategy;

/**
 * @Author: aliber
 * @Date: 2020/12/23 上午10:52
 **/
public class CashNormal extends CashSuper {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
