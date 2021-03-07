package com.ali.leb.bomd.designpattern.observer.v8;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 有很多时候，观察者需要根据事件的具体情况来进行处理
 * 大多数时候，我们处理事件的时候，需要事件源对象
 * 事件也可以形成继承体系
 */

interface Observer {
    void actionOnWakeUp(WakeUpEvent wue);
}

abstract class Event<T> {
    abstract T getSource();
}

// 事件类
class WakeUpEvent extends Event<Child> {
    long timestamp;
    String location;
    Child source;

    public WakeUpEvent(long timestamp, String loc, Child source) {
        this.timestamp = timestamp;
        this.location = loc;
        // 原对象本身
        this.source = source;
    }

    @Override
    Child getSource() {
        return this.source;
    }
}


class Child {
    private boolean cry = false;
    private List<Observer> observerList = new ArrayList<>();

    {
        observerList.add(new Dad());
        observerList.add(new Mum());
        observerList.add(new Dog());
    }

    public boolean isCry() {
        return this.cry;
    }

    public void wakeUp() {
        System.out.println("Wake up, Crying wuwuwuwuwuw.....");
        this.cry = true;
        WakeUpEvent wue = new WakeUpEvent(System.currentTimeMillis(), "bed", this);
        for (Observer o : observerList) {
            o.actionOnWakeUp(wue);
        }
    }

}

class Dad implements Observer {
    public void feed() {
        System.out.println("Dad feeding...");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent wue) {
        this.feed();
        wue.getSource();
    }
}

class Mum implements Observer {
    public void hug() {
        System.out.println("Mum hugging...");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent wue) {
        this.hug();
        wue.getSource();
    }
}

class Dog implements Observer {
    public void wang() {
        System.out.println("Dog wang...");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent wue) {
        this.wang();
        wue.getSource();
    }
}


public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.wakeUp();
    }
}
