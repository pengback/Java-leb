package com.ali.leb.bomd.designpattern.strategy;

/**
 * @Author: aliber
 * @Date: 2020/12/23 上午11:00
 **/
public class CashClinent {

    public static void main(String[] args) {
        CashContext cashContext = null;

        int in = 1;
        String type = "";

        switch (in) {
            case 1:
                cashContext = new CashContext(new CashNormal());
                type += "正常收费";
                break;

            case 2:
                cashContext = new CashContext(new CashReturn(300, 100));
                type += "满300返100";
                break;

            case 3:
                cashContext = new CashContext(new CashRebate(0.8));
                type += "打8折";
                break;

            default:
                System.out.println("请输入1/2/3");
                break;
        }
        double price = 234.54;
        double num = 3;

        double totalPrices = 0;
        totalPrices = cashContext.getResult(price * num);

        System.out.println("单价：" + price + "，数量：" + num + "，类型：" + type + "，合计：" + totalPrices);


    }

}
