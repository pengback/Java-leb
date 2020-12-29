package com.ali.leb.bomd.designpattern.responseandstrategy.res.matcher;

import com.ali.leb.bomd.designpattern.responseandstrategy.event.BaseEvent;
import com.ali.leb.bomd.designpattern.responseandstrategy.matcher.BaseMatcher;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseMessage;

import javax.annotation.Resource;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午5:13
 **/
public class ResMatcher implements BaseMatcher {

    @Override
    public boolean doMatch(BaseEvent event, BaseMessage message) {
        // 取值直接判断
        // 换一种
//        String eventCode = event.getEventCode();
//        String matchCode = message.getMatchCode();
//
//        if ("YS" + eventCode == matchCode ) {
//            return true;
//        } else {
//            return false;
//        }

        //  从数据库执行业务逻辑的查询代码;

        return event.getEventCode().equals(message.getMatchCode());
    }
}
