package com.zh.bean;

/**
 * @author Zhaohui
 * @date 2020/10/10
 */
public class Car {
    public Car() {
        System.out.println("Car constructor...");
    }

    public void init() {
        System.out.println("Car init...");
    }

    public void destroy() {
        System.out.println("Car destroy...");
    }
}
