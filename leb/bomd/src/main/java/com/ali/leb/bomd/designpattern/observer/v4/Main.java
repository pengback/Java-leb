package com.ali.leb.bomd.designpattern.observer.v4;

/**
 * 加入多个观察者
 */


class Child {
    private boolean cry = false;
    private Dad d = new Dad();
    private Mum m = new Mum();
    private Dog dog = new Dog();

    public boolean isCry() {
        return this.cry;
    }

    public void wakeUp() {
        System.out.println("Wake up, Crying wuwuwuwuwuw.....");
        this.cry = true;
        d.feed();
        m.hug();
        dog.wang();
    }

}

class Dad {
    public void feed() {
        System.out.println("Dad feeding...");
    }
}

class Mum {
    public void hug() {
        System.out.println("Mum hugging...");
    }
}

class Dog {
    public void wang() {
        System.out.println("Dog wang...");
    }
}

public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.wakeUp();
    }
}
