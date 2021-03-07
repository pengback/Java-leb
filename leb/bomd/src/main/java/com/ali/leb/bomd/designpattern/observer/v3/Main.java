package com.ali.leb.bomd.designpattern.observer.v3;

/**
 * 第三个版本，加入观察者
 */

class Child {
    private boolean cry = false;
    private Dad d = new Dad();

    public boolean isCry() {
        return this.cry;
    }

    public void wakeUp() {
        System.out.println("Wake up, Crying wuwuwuwuwuw.....");
        this.cry = true;
        d.feed();
    }

}

class Dad {
    public void feed() {
        System.out.println("Dad feeding...");
    }
}

public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.wakeUp();
    }
}
