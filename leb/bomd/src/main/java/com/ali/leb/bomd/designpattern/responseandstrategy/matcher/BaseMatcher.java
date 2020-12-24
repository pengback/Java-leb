package com.ali.leb.bomd.designpattern.responseandstrategy.matcher;

import com.ali.leb.bomd.designpattern.responseandstrategy.event.BaseEvent;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseMessage;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午2:34
 **/
public interface BaseMatcher {
    /**
     * 比较器, 执行比较方法;
     * 实现类中需要重新比较方法
     *
     * @param event
     * @param message
     * @return
     */
    boolean doMatch(BaseEvent event, BaseMessage message);
}
