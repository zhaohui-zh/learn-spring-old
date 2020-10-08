package com.zh.config;

import com.zh.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Zhaohui
 * @date 2020/10/8
 */
@Configuration
public class MainConfig2 {

    // SCOPE_PROTOTYPE 多实例：IOC容器启动并不会调用方法创建对象放在容器中，每次获取才会调用方法创建对象
    // SCOPE_SINGLETON 单实例(默认值)：IOC容器启动时会调用方法创建对象放到IOC容器中，每次都是直接从容器中拿
    // SCOPE_REQUEST   同一次请求创建以个实例
    // SCOPE_SESSION   同一个session创建一个实例

    // 默认单实例
    @Scope("prototype")
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加Person");
        return new Person("张三", 25);
    }
}
