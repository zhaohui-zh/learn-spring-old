package com.zh.config;

import com.zh.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Zhaohui
 * @date 2020/10/12
 */
// 使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量;加载完外部的配置文件后使用${}取出配置文件中的值
@PropertySource({"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValues {

    @Bean
    public Person person() {
        return new Person();
    }
}
