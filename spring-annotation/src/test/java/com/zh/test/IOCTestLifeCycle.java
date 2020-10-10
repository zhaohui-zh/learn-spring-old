package com.zh.test;

import com.zh.config.MainConfigOfLifeCycle;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Zhaohui
 * @date 2020/10/10
 */
public class IOCTestLifeCycle {
    @Test
    public void test01() {
        // 1. 创建ioc容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成...");
        // applicationContext.getBean("car");
        // 关闭容器
        applicationContext.close();
    }
}
