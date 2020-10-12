package com.zh.test;

import com.zh.bean.Person;
import com.zh.config.MainConfigOfLifeCycle;
import com.zh.config.MainConfigOfPropertyValues;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Zhaohui
 * @date 2020/10/10
 */
public class IOCTestPropertyValue {
    private void printBeans(ApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void test01() {
        // 1. 创建ioc容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        printBeans(applicationContext);
        System.out.println("==========");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println(environment.getProperty("person.nickName"));;

        applicationContext.close();
    }
}
