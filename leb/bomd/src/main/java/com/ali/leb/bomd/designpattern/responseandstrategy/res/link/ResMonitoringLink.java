package com.ali.leb.bomd.designpattern.responseandstrategy.res.link;

import com.ali.leb.bomd.designpattern.responseandstrategy.link.MonitoringLink;
import com.ali.leb.bomd.designpattern.responseandstrategy.link.MonitoringNode;
import com.ali.leb.bomd.designpattern.responseandstrategy.matcher.BaseMatcher;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseMessage;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseOutMessage;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午5:22
 **/
public class ResMonitoringLink extends MonitoringLink {

    public ResMonitoringLink(BaseMatcher matcher) {
        super(matcher);
    }

    /**
     * 实现link链中,节点的操作方法
     *
     * @param message
     * @return
     */
    @Override
    public BaseOutMessage exec(BaseMessage message) {
        BaseOutMessage outMessage = null;
        for (final MonitoringNode node : getLinks()) {
            // 进行逻辑匹配, 判断 入参的message消息
            if (this.getMatcher().doMatch(node.getEvent(), message)) {
                outMessage = node.service(message);
                break;
            }
        }
        return outMessage;
    }

}
