package com.ali.leb.bomd.designpattern.observer.v6;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用观察者interface抽象
 */

interface Observer {
    void actionOnWakeUp(WakeUpEvent wue);
}

// 事件类
class WakeUpEvent {
    long timestamp;
    String location;

    public WakeUpEvent(long timestamp, String loc) {
        this.timestamp = timestamp;
        this.location = loc;
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
        WakeUpEvent wue = new WakeUpEvent(System.currentTimeMillis(), "bed");
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
    }
}

class Mum implements Observer {
    public void hug() {
        System.out.println("Mum hugging...");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent wue) {
        this.hug();
    }
}

class Dog implements Observer {
    public void wang() {
        System.out.println("Dog wang...");
    }

    @Override
    public void actionOnWakeUp(WakeUpEvent wue) {
        this.wang();
    }
}


public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.wakeUp();
    }
}
