package com.ali.leb.bomd.designpattern.responseandstrategy.link;

import com.ali.leb.bomd.designpattern.responseandstrategy.matcher.BaseMatcher;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseMessage;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseOutMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午2:14
 **/
public abstract class MonitoringLink {

    private List<MonitoringNode> links = new ArrayList<>();
    private BaseMatcher matcher;

    public MonitoringLink(BaseMatcher matcher) {
        this.matcher = matcher;
    }

    public List<MonitoringNode> getLinks() {
        return this.links;
    }

    public BaseMatcher getMatcher() {
        return this.matcher;
    }

    public MonitoringNode newNode() {
        return new MonitoringNode(this);
    }

    /**
     * 执行匹配方法
     *
     * @param message
     * @return
     */
    public abstract BaseOutMessage exec(BaseMessage message);
}
