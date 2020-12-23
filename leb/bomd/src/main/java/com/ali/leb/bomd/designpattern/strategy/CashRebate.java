package com.ali.leb.bomd.designpattern.strategy;

import lombok.AllArgsConstructor;

/**
 * @Author: aliber
 * @Date: 2020/12/23 上午10:53
 **/
@AllArgsConstructor
public class CashRebate extends CashSuper{

    private double moneyRebate = 1;

    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}
