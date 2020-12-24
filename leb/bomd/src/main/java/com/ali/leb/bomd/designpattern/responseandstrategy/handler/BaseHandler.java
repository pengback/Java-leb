package com.ali.leb.bomd.designpattern.responseandstrategy.handler;

import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseMessage;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseOutMessage;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午1:59
 **/
public interface BaseHandler {

    BaseOutMessage handler(BaseMessage message);

}
