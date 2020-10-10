package com.zh.test;

import com.zh.bean.Blue;
import com.zh.bean.Person;
import com.zh.config.MainConfig;
import com.zh.config.MainConfig2;
import com.zh.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhaohui
 * @date 2020/10/8
 */
class IOCTest {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void test01() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
        BookController bookController = (BookController) applicationContext.getBean("bookController");
        BookController bookController2 = (BookController) applicationContext.getBean("bookController");
        System.out.println(bookController == bookController2);
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
        System.out.println(person == person2);
    }

    @Test
    public void test03() {
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        Environment environment = applicationContext.getEnvironment();
        // 动态获取环境变量的值
        String property = environment.getProperty("os.name");
        System.out.println(property);
        for (String name : namesForType) {
            System.out.println(name);
        }

        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
    }

    private void printBeans(ApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void testImport() {
        printBeans(applicationContext);
        // 工厂Bean获取的是调用getObject方法创建的对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        Object colorFactoryBean2 = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean的类型" + colorFactoryBean.getClass());
        System.out.println(colorFactoryBean == colorFactoryBean2);
        Object colorFactoryBean4 = applicationContext.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean4.getClass());
    }
}
