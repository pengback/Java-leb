package com.ali.leb.bomd.designpattern.strategy;

import lombok.AllArgsConstructor;

/**
 * @Author: aliber
 * @Date: 2020/12/23 上午10:55
 **/

@AllArgsConstructor
public class CashReturn extends CashSuper {

    // 返利条件
    private double moneyConditation = 0.0;
    // 返利值
    private double moneyReturn = 0.0d;

    @Override
    public double acceptCash(double money) {
        if (money >= moneyConditation) {
            return money - Math.floor(money / moneyConditation) * moneyReturn;
        } else {
            return money;
        }
    }
}
