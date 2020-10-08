package com.zh.test;

import com.zh.bean.Person;
import com.zh.config.MainConfig;
import com.zh.config.MainConfig2;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhaohui
 * @date 2020/10/8
 */
class IOCTest {
    @Test
    public void test01() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void test02() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        // String[] definitionNames = applicationContext.getBeanDefinitionNames();
        // for (String name : definitionNames) {
        //     System.out.println(name);
        // }
        System.out.println("IOC容器创建完成");
        Person person = (Person) applicationContext.getBean("person");
        Person person2 = (Person) applicationContext.getBean("person");
        // System.out.println(person == person2);
    }
}
