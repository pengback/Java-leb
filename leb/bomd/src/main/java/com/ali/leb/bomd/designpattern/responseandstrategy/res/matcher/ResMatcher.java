package com.ali.leb.bomd.designpattern.responseandstrategy.res.matcher;

import com.ali.leb.bomd.designpattern.responseandstrategy.event.BaseEvent;
import com.ali.leb.bomd.designpattern.responseandstrategy.matcher.BaseMatcher;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseMessage;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午5:13
 **/
public class ResMatcher implements BaseMatcher {
    @Override
    public boolean doMatch(BaseEvent event, BaseMessage message) {
        return event.getEventCode().equals(message.getMatchCode());
    }
}
