package com.zh.bean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Zhaohui
 * @date 2020/10/9
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    // AnnotationMetadata：当前类的注解信息
    // BeanDefinitionRegistry：BeanDefinition注册类
    // 把所有需要添加到容器中的bean，调用BeanDefinition.registerBeanDefinitions方法，手工注册
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.zh.bean.Red");
        boolean blue = registry.containsBeanDefinition("com.zh.bean.Blue");
        if (red && blue) {
            // 指定bean定义信息（类型、作用域等）
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            // 注册一个bean，指定bean名
            registry.registerBeanDefinition("rainBow", rootBeanDefinition);
        }
    }
}
