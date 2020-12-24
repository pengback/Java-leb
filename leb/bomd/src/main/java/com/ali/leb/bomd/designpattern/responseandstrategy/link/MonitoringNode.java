package com.ali.leb.bomd.designpattern.responseandstrategy.link;

import com.ali.leb.bomd.designpattern.responseandstrategy.event.BaseEvent;
import com.ali.leb.bomd.designpattern.responseandstrategy.handler.BaseHandler;
import com.ali.leb.bomd.designpattern.responseandstrategy.matcher.BaseMatcher;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseMessage;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseOutMessage;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午2:27
 **/

public class MonitoringNode {

    private final MonitoringLink linkBuilder;
    private BaseEvent event;
    private BaseHandler handler;
    private BaseMatcher matcher;

    public MonitoringNode(MonitoringLink l) {
        this.linkBuilder = l;
    }

    public MonitoringNode event(BaseEvent e) {
        this.event = e;
        return this;
    }

    public BaseEvent getEvent() {
        return this.event;
    }

    public MonitoringNode handler(BaseHandler handler) {
        this.handler = handler;
        return this;
    }

    public MonitoringNode matcher(BaseMatcher matcher) {
        this.matcher = matcher;
        return this;
    }

    public MonitoringLink end() {
        this.linkBuilder.getLinks().add(this);
        return this.linkBuilder;
    }

    public BaseOutMessage service(BaseMessage message) {
        return handler.handler(message);
    }

}
