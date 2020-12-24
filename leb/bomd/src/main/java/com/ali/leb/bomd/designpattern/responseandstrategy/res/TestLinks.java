package com.ali.leb.bomd.designpattern.responseandstrategy.res;

import com.ali.leb.bomd.designpattern.responseandstrategy.link.MonitoringLink;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseOutMessage;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.err.ResErr;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.event.ResEvent;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.handler.ErrOneHandler;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.handler.ErrThreeHandler;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.handler.ErrTwoHandler;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.link.ResMonitoringLink;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.matcher.ResMatcher;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.message.ResMessage;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午5:20
 **/
public class TestLinks {

    public static void main(String[] args) {
        MonitoringLink link = new ResMonitoringLink(new ResMatcher());
        link.newNode().event(new ResEvent(ResErr.ERR_1)).handler(new ErrOneHandler()).end();
        link.newNode().event(new ResEvent(ResErr.ERR_2)).handler(new ErrTwoHandler()).end();
        link.newNode().event(new ResEvent(ResErr.ERR_3)).handler(new ErrThreeHandler()).end();

        ResMessage resMessage = new ResMessage();
        resMessage.setMessageCode("error_3");
        resMessage.setMessageState("error");
        resMessage.setMessageBody("这里是错误信息的body");
        System.out.println(resMessage.printMessageInfo());

        BaseOutMessage outMessage = link.exec(resMessage);
        System.out.println("最后的打印信息: " + outMessage.printMessageInfo());

    }

}
