package com.ali.leb.bomd.designpattern.responsibilitychain;

import lombok.Data;

/**
 * @Author: aliber
 * @Date: 2020/12/23 上午11:30
 **/
@Data
public abstract class Handler {
    Handler nextHandler;
    public abstract void handerRequest(String request);

}
