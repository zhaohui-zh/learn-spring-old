package com.zh.test;

import com.zh.bean.Boss;
import com.zh.bean.Car;
import com.zh.bean.Color;
import com.zh.bean.Yellow;
import com.zh.config.MainConfigAutowired;
import com.zh.config.MainConfigProfile;
import com.zh.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author Zhaohui
 * @date 2020/10/10
 */
public class IOCTestProfile {
    private void printBeans(ApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    // 1. 使用命令行动态参数:在虚拟机参数位置加载：-Dspring.profiles.active=test
    // 2. 使用代码的方式激活某种环境
    @Test
    public void test01() {
        // AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigProfile.class);

        // 1. 创建一个applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 2. 设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("dev");
        // 3. 注册主配置类
        applicationContext.register(MainConfigProfile.class);
        // 4. 启动刷新容器
        applicationContext.refresh();


        String[] names = applicationContext.getBeanNamesForType(DataSource.class);

        for (String name : names) {
            System.out.println(name);
        }
        Yellow yellow = applicationContext.getBean(Yellow.class);
        System.out.println(yellow);
        applicationContext.close();
    }
}
