package com.ali.leb.bomd.designpattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: aliber
 * @Date: 2020/12/23 上午10:41
 **/
@Data
@AllArgsConstructor
public class CashContext {

    private CashSuper cashSuper;

    public double getResult(double money) {
        return cashSuper.acceptCash(money);
    }

}
