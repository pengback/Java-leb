package com.ali.leb.bomd.designpattern.responseandstrategy.message;

import lombok.Data;

/**
 * @Author: aliber
 * @Date: 2020/12/23 下午3:25
 **/
public abstract class BaseMessage implements IMessage {

    public abstract String getMatchCode();
}
