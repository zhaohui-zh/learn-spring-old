package com.zh.test;

import com.zh.bean.Boss;
import com.zh.bean.Car;
import com.zh.bean.Color;
import com.zh.bean.Person;
import com.zh.config.MainConfigAutowired;
import com.zh.config.MainConfigOfPropertyValues;
import com.zh.dao.BookDao;
import com.zh.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Zhaohui
 * @date 2020/10/10
 */
public class IOCTestAutowired {
    private void printBeans(ApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void test01() {
        // 1. 创建ioc容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigAutowired.class);

        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);

        // BookDao bookDao = applicationContext.getBean(BookDao.class);
        // System.out.println(bookDao);

        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);

        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);
        System.out.println(applicationContext);
        applicationContext.close();
    }
}
