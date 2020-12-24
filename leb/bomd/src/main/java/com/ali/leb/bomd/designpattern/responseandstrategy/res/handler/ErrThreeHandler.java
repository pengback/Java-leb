package com.ali.leb.bomd.designpattern.responseandstrategy.res.handler;

import com.ali.leb.bomd.designpattern.responseandstrategy.handler.BaseHandler;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseMessage;
import com.ali.leb.bomd.designpattern.responseandstrategy.message.BaseOutMessage;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.message.ResMessage;
import com.ali.leb.bomd.designpattern.responseandstrategy.res.message.ResOutMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午2:06
 **/
@Slf4j
public class ErrThreeHandler implements BaseHandler {
    @Override
    public BaseOutMessage handler(BaseMessage message) {
        ResMessage resMessage = (ResMessage) message;
        String desc = "这里是进行了错误 3 的Handler, 获取到" + message.printMessageInfo();
        log.info(desc);
        ResOutMessage outMessage = new ResOutMessage();
        outMessage.setMessageCode(resMessage.getMessageCode());
        outMessage.setMessageBody(resMessage.getMessageBody());
        return outMessage;
    }
}
