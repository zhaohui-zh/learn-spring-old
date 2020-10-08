package com.zh.config;

import com.zh.bean.Person;
import com.zh.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author Zhaohui
 * @date 2020/10/8
 */
// 配置类==配置文件
@Configuration //告诉Spring这是一个配置类
@ComponentScan(value = {"com.zh"}, useDefaultFilters = false, includeFilters = {
        // @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
        // @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
})
//@ComponentScans() -> ComponentScan[];
//@ComponentScan value指定要扫描的包
//excludeFilters = Filter[] 指定扫描的时候按照什么规则排除那些组件
//includeFilters = Filter[] 指定扫描的时候只需要包含那些组件
//FilterType.ANNOTATION 按照注解的方式
//FilterType.ASSIGNABLE_TYPE 按照给定的类型
//FilterType.ASPECTJ 使用ASPECTJ表表达式
//FilterType.REGEX 使用正则表达式
//FilterType.CUSTOM 使用自定义规则
public class MainConfig {
    // 给容器中注册一个Bean；类型为返回值的类型，id默认为方法名
    @Bean("person")
    public Person person01() {
        return new Person("李四", 20);
    }
}
