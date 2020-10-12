# 组件作用域

- PROTOTYPE 多实例：IOC容器启动并不会调用方法创建对象放在容器中，每次获取才会调用方法创建对象；当用户从IOC容器中拿到创建的对象后，Spring不再对该对象进行管理，由用户自行管理
- SINGLETON 单实例(默认值)：IOC容器启动时会调用方法创建对象并放到IOC容器中，用户需要时每次都是直接从容器中拿；对象的生命周期始终由Spring管理
- REQUEST   同一次请求创建以个实例
- SESSION   同一个session创建一个实例



# 注册组件

给容器中注册组件的方式

1. 包扫描+组件标注注解（@Component、@Service、@Controller、@Repository），[主要是自己写的类]
2. @Bean，[导入的第三方包里面的组件]
3. @Import，[快速给容器中导入组件]，有三种用法
   -  Import（类名），容器中就会自动注册这个组件，id默认是组件的全类名
   -  ImportSelector：返回需要导入的组件的全类名的数组
   -  ImportBeanDefinitionRegistrar：手动注册bean
4. 使用Spring提供的FactoryBean（工厂bean）
   - 默认获取到的是工厂bean调用getObject创建的对
   - 要获取到bean本身，需要给id前面加个&标识



# Bean的生命周期

```java
/**
 * bean的生命周期：
 *      bean创建--初始化--销毁
 * 容器管理bean的生命周期
 * 我们可以自定义初始化和销毁方法：容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * 构造（对象创建）
 *      单实例：在容器启动的时候创建对象
 *      多实例：在每次获取的时候创建对象
 *
 * (BeanPostProcessor.postProcessAfterInitialization)
 * 初始化：
 *      对象创建完成，并赋值，调用初始化方法
 * (BeanPostProcessor.postProcessAfterInitialization)
 *
 * 销毁
 *      单实例：容器关闭的时候
 *      多实例：容器不会管理这个bean，容器不会调用销毁方法
 *
 * // 定义初始化逻辑和销毁逻辑的几种方法：
 * 1. 指定初始化和销毁方法
 *      通过@Bean指定init-method和destory-method
 * 2. 通过让Bean实现InitializingBean（定义初始化逻辑）,DisposableBean(定义销毁逻辑)
 * 3. 可以使用JSR205：
 *      @PostConstruct，在bean创建完成并且属性赋值完成，来执行初始化方法
 *      @PreDestroy，在容器销毁bean之前，通知我们进行清理工作
 * 4. BeanPostProcessor：bean的后置处理器
 *      在bean初始化前后进行一些处理工作
 *      postProcessBeforeInitialization：在初始化之前工作
 *      postProcessAfterInitialization：在初始化之后工作
 *
 *
 * @author Zhaohui
 * @date 2020/10/10
 */
```



# FactoryBean

工厂Bean默认获取的是调用其getObject方法创建的对象，而不是工厂Bean本身。如果需要获取工厂Bean本身，需要在bean id前面加上符号&。

```java
// 获取的是调用getObject方法创建的对象
Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
// 获取工厂Bean本身
Object colorFactoryBean4 = applicationContext.getBean("&colorFactoryBean");
```



# 属性赋值

```
使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量;加载完外部的配置文件后使用${}取出配置文件中的值

// 使用@Value注解赋值
// 1. 基本数值
// 2. 可以写SpEL，#{}
// 3. 可以写${},取出配置文件（properties yml）中的值（在运行的环境变量里面的值）
```



# 自动装配原理



```
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
 *      @Resource: 可以和@Autowired一样，实现自动装配功能，默认是按照组件名称进行装配的，可以指定名称（@Resource(name="beanName")）,
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
 * @author Zhaohui
 * @date 2020/10/12
 */
```



# 注解总结

## 组件注册相关

## 

| 注解           | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| @Bean          | 标注在方法上，给容器中注册一个Bean；类型为返回值的类型，id默认为方法名；可以通过name参数自定义id |
| @Scope         | 标注在方法（与@Bean 配合）或类（与@Component系配合）上，指定Bean的作用域 |
| @Component     | 标注在类上，表示该类是一个组件；Spring可以通过自动扫描的方式将该类注册到ioc容器，id默认为类名首字母小写 |
| @Controller    | 与@Component相同，强调标注的类是一个Controller               |
| @Service       | 与@Component相同，强调标注的类是一个Service                  |
| @Repository    | 与@Component相同，强调标注的类是一个Dao                      |
| @Conditional   | 标注在方法或类上；标注在方法上时，与@Bean配合使用，满足条件才会注册Bean；标注在类上时，与@Configuration配合使用，满足条件时，这个配置类中所有bean注册才能生效；value=Class<? extends Condition>[] |
| @Import        | 标注在类上，快速给容器中导入组件，详见组件注册的描述         |
| @Lazy          | 标注在方法（与@Bean 配合）或类（与@Component系配合）上，表示懒加载，即容器启动不创建对象，第一次使用（获取）Bean时创建对象，并初始化；该注解只针对单实例Bean（单实例Bean：默认在容器启动的时候创建对象）； |
| @PostConstruct | 标注在方法上，Bean对象创建并赋值之后调用                     |
| @PreDestroy    | 标注在方法上，容器移除对象之前                               |
| @Autowired     | 可以标注的位置：构造器、参数、方法、字段；默认优先按照类型去容器中找对应的组件；如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找； |
| @Qualifier     | 标注位置与@Autowired相同，与@Autowired搭配使用，指定需要装配的组件id，而不是按照默认规则装配（按照类型或属性名装配） |
| @Resource      | 和@Autowired一样，可以实现自动装配功能，默认是按照组件名称进行装配的，可以指定名称（@Resource(name="beanName")）,不支持@Primary功能，不支持@Autowired(required=false)功能 |
| @Inject        | 需要导入javax.inject依赖，和@Autiwired一样，支持@Primary，没有required=false的功能 |
| @Value         | 标注在属性上，从运行环境加载环境变量的值（加载配置文件中的key/value），并进行赋值属性 |

## 配置相关

| 注解             | 说明                                                         |
| ---------------- | ------------------------------------------------------------ |
| @Configuration   | 标注在类上，告诉Spring这是一个配置类，配置类与xml配置文件等价;该注解本身已经标注了@Component，所以，它标注的配置类也是一个组件 |
| @ComponentScan   | 与@Configuration配合使用，标注在类上，说明开启自动扫描功能；value=String[]，指定要扫描的包，扫描时会扫描给定包及其子包下的所有类；excludeFilters = Filter[] 指定扫描的时候按照什么规则排除那些组件（这里的Filter也为注解）；includeFilters = Filter[] 指定扫描的时候只需要包含那些组件（此时需要同时传入useDefaultFilters = false，该配置才可生效）；该注解可重复标记，指定多个扫描策略； |
| @ComponentScans  | 与@Configuration配合使用，标注在类上；value=ComponentScan[]，封装多个@ComponentScan |
| @Filter          | 是@ComponentScan内部的注解，封装过滤条件；type = FilterType.xxx，指定过滤类型，classes=class<?>[]（FilterType为ANNOTATION、ASSIGNABLE_TYPE时）， 指定符合条件的类；pattern=String[]（FilterType为ASPECTJ、REGEX时）指定表达式；FilterType为枚举类：FilterType.ANNOTATION 按照注解的方式、FilterType.ASSIGNABLE_TYPE 按照给定的类型、FilterType.ASPECTJ 使用ASPECTJ表表达式、FilterType.REGEX 使用正则表达式、FilterType.CUSTOM 使用自定义规则 |
| @PropertySource  | 标注在配置类上，将配置文件中的key/value加载到运行时的环境变量中，加载完外部的配置文件后可以使用${}取出配置文件中的值 |
| @PropertySources | 标注在配置类上，封装多个@PropertySource                      |
|                  |                                                              |
|                  |                                                              |
|                  |                                                              |
|                  |                                                              |
|                  |                                                              |
|                  |                                                              |
|                  |                                                              |

