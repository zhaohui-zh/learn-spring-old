package com.zh.config;

import com.zh.bean.Color;
import com.zh.bean.MyImportBeanDefinitionRegistrar;
import com.zh.bean.Person;
import com.zh.bean.Red;
import com.zh.condition.LinuxCondition;
import com.zh.condition.MyImportSelector;
import com.zh.condition.WindowsCondition;
import org.springframework.context.annotation.*;

/**
 * @author Zhaohui
 * @date 2020/10/8
 */
// 类中组件统一设置：满足当前条件，这个类中配置的所有bean注册才能生效
@Conditional(WindowsCondition.class)
@Configuration
@Import(value = {Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
// 快速的导入组件，id默认是组建的全类名
public class MainConfig2 {

    // SCOPE_PROTOTYPE 多实例：IOC容器启动并不会调用方法创建对象放在容器中，每次获取才会调用方法创建对象
    // SCOPE_SINGLETON 单实例(默认值)：IOC容器启动时会调用方法创建对象放到IOC容器中，每次都是直接从容器中拿
    // SCOPE_REQUEST   同一次请求创建以个实例
    // SCOPE_SESSION   同一个session创建一个实例
    //
    // 懒加载：
    //      单实例Bean：默认在容器启动的时候创建对象
    //      懒加载：容器启动不创建对象，第一次使用（获取）Bean时创建对象，并初始化

    // 默认单实例
    // @Scope("prototype")
    @Lazy
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加Person");
        return new Person("张三", 25);
    }

    //
    // @Conditional({Condition})：按照一定的条件进行判断，满足条件给容器中注册bean
    // 如果系统是windows，给容器中注册("bill")
    // 如果是linux系统，给容器中注册("linux")
    // @Conditional(WindowsCondition.class)
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linux")
    public Person person02() {
        return new Person("Linus", 48);
    }

    // 给容器中注册组建的方式
    // 1. 包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
    // 2. @Bean [导入的第三方包里面的组件]
    // 3. @Import [快速给容器中导入一个组件]
    //      1) @Import(要导入到容器中的组件)，容器中就会自动组测这个组件，id默认是全类名
    //      2) ImportSelector: 返回需要导入的组建的全类名数组
    //      3) ImportBeanDefinitionRegistrar，手动注册bean到容器中
}
