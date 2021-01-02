package com.ali.leb.bomd.designpattern.observer.v5;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用观察者interface抽象
 */

interface Observer {
    void actionOnWakeUp();
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
        for (Observer o : observerList) {
            o.actionOnWakeUp();
        }
    }

}

class Dad implements Observer {
    public void feed() {
        System.out.println("Dad feeding...");
    }

    @Override
    public void actionOnWakeUp() {
        this.feed();
    }
}

class Mum implements Observer {
    public void hug() {
        System.out.println("Mum hugging...");
    }

    @Override
    public void actionOnWakeUp() {
        this.hug();
    }
}

class Dog implements Observer {
    public void wang() {
        System.out.println("Dog wang...");
    }

    @Override
    public void actionOnWakeUp() {
        this.wang();
    }
}


public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.wakeUp();
    }
}
