package com.zh.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Zhaohui
 * @date 2020/10/10
 */
@Component
public class Dog {
    public Dog() {
        System.out.println("dog constructor...");
    }

    // 对象创建并赋值之后调用
    @PostConstruct
    public void init() {
        System.out.println("dog @PostConstruct ... ");
    }

    // 容器移除对象之前
    @PreDestroy
    public void destroy() {
        System.out.println("dog  @PreDestroy ... ");
    }

}
