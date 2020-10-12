package com.zh.test;

import com.zh.aop.MathCalculator;
import com.zh.bean.Yellow;
import com.zh.config.MainConfig;
import com.zh.config.MainConfigOfAOP;
import com.zh.config.MainConfigProfile;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author Zhaohui
 * @date 2020/10/10
 */
public class IOCTestAOP {
    private void printBeans(ApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        // 不要自己创建对象
        // MathCalculator mathCalculator = new MathCalculator();
        // mathCalculator.div(1, 1);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        mathCalculator.div(1, 0);
        applicationContext.close();
    }
}
