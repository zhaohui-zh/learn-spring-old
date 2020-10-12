package com.zh.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zhaohui
 * @date 2020/10/12
 */
// 默认加载ioc容器中的组件，容器启动会调用无参构造器，在进行初始化赋值等操作
@Component
public class Boss {

    private Car car;

    // 构造器要用的组件，都是从ioc容器中取

    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss 有参构造器");
    }

    public Car getCar() {
        return car;
    }

    // 标注在方法上，Spring容器创建当前对象时，就会调用方法，完成赋值
    // 方法使用的参数，自定义类型的值从ioc容器中获取
    // @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
