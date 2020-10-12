package com.zh.config;

import com.zh.bean.Car;
import com.zh.bean.Color;
import com.zh.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 * 自动装配：
 *      Spring利用依赖自动注入（DI）,完成对ioc容器中各个组件的依赖关系赋值；
 *  1. @Autowired 自动注入
 *      1) 默认优先按照类型区容器中找对应的组件：applicationContext.getBean(BookDao.class);找到就赋值
 *      2）如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找applicationContext.getBean("bookDao")
 *      3）@Qualifier("bookDao"):使用@Qualifer注解指定需要装配的组件id，而不是使用属性名
 *      4）自动装配默认一定要将属性赋值好，没有就会报错
 *         可以使用@Autowired(required = false);
 *      5）@Primary，让Spring进行自动装配的时候，默认使用首选的Bean;也可以继续使用@Qualifer指定需要装配的bean的名字
 *      BookService {
 *          @Autowired
 *          BookDao bookDao;
 *      }
 * 2. Spring还支持@Resource(JSR250)和@Inject(JSR330)[java规范的注解]
 *      @Resource: 和@Autowired一样，实现自动装配功能，默认是按照组件名称进行装配的，可以指定名称（@Resource(name="beanName")）,
 *                 不支持@Primary功能，不支持@Autowired(required=false)功能
 *      @Inject: 需要导入javax.inject依赖，和@Autiwired一样，支持@Primary，没有required=false的功能
 *      @Autowired 是spring定义的，@Resource、@Inject都是Java规范
 *
 * AutowiredAnnotationBeanPosterProcessor:解析完成自动装配功能
 *
 * 3. @Autowired可以标注的位置：构造器、参数、方法、字段；都是从容器中过去参数组件的值
 *      1）[标注在方法位置上]:@Bean+方法参数，参数从容器中获取；默认不写@Autowired，效果都是一样的，都能自动装配
 *      2）[标注在构造器上]:如果组件只有一个有参构造器，这个有参构造器的@Autowired注解可以省略，参数位置的嘴贱还是可以自动从容器中获取
 *      3）标注在方法或构造器的参数前面
 *
 * 4. 自定义组件需要使用Spring容器底层的一些组件（ApplicationContext, BeanFactory, xxx）
 *     只需要让自定义组件实现xxxAware接口；在创建组件的时候，会调用接口规定的方法注入相关组件
 *     把Spring底层的一些组件注入到自定义的Bean中
 *     xxxAware: 功能使用xxxAwareProcessor来处理
 *      ApplicationContextAware ==> ApplicationContextAwareProcessor
 *
 *
 *
 * @author Zhaohui
 * @date 2020/10/12
 */
@Configuration
@ComponentScan({"com.zh.service", "com.zh.dao", "com.zh.controller", "com.zh.bean"})
public class MainConfigAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }

    /**
     * @Bean 标注的方法创建对象的时候，方法参数的值从容器中获取
     * 参数前面的@Autowired可以标，也可以省略
     * @param car
     * @return
     */
    @Bean
    public Color color(Car car) {
        Color color = new Color();
        color.setCar(car);
        return color;
    }

}
