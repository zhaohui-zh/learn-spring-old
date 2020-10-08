package com.zh;

import com.zh.bean.Person;
import com.zh.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Zhaohui
 * @date 2020/10/8
 */
public class MainTest {
    public static void main(String[] args) {
        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        // Person peron = (Person) applicationContext.getBean("person");
        // System.out.println(peron);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);
        String[] beanNames = applicationContext.getBeanNamesForType(Person.class);
        for (String name : beanNames) {
            System.out.println(name);
        }
    }
}
