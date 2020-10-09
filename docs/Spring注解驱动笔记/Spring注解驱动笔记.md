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



# 注解总结

## 组件注册相关

## 

| 注解         | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| @Bean        | 标注在方法上，给容器中注册一个Bean；类型为返回值的类型，id默认为方法名；可以通过name参数自定义id |
| @Scope       | 标注在方法（与@Bean 配合）或类（与@Component系配合）上，指定Bean的作用域 |
| @Component   | 标注在类上，表示该类是一个组件；Spring可以通过自动扫描的方式将该类注册到ioc容器，id默认为类名首字母小写 |
| @Controller  | 与@Component相同，强调标注的类是一个Controller               |
| @Service     | 与@Component相同，强调标注的类是一个Service                  |
| @Repository  | 与@Component相同，强调标注的类是一个Dao                      |
| @Conditional | 标注在方法或类上；标注在方法上时，与@Bean配合使用，满足条件才会注册Bean；标注在类上时，与@Configuration配合使用，满足条件时，这个配置类中所有bean注册才能生效；value=Class<? extends Condition>[] |
| @Import      | 标注在类上，快速给容器中导入组件，详见组件注册的描述         |
| @Lazy        | 标注在方法（与@Bean 配合）或类（与@Component系配合）上，表示懒加载，即容器启动不创建对象，第一次使用（获取）Bean时创建对象，并初始化；该注解只针对单实例Bean（单实例Bean：默认在容器启动的时候创建对象）； |
|              |                                                              |
|              |                                                              |
|              |                                                              |
|              |                                                              |
|              |                                                              |
|              |                                                              |
|              |                                                              |

## 配置相关

| 注解            | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| @Configuration  | 标注在类上，告诉Spring这是一个配置类，配置类与xml配置文件等价;该注解本身已经标注了@Component，所以，它标注的配置类也是一个组件 |
| @ComponentScan  | 与@Configuration配合使用，标注在类上，说明开启自动扫描功能；value=String[]，指定要扫描的包，扫描时会扫描给定包及其子包下的所有类；excludeFilters = Filter[] 指定扫描的时候按照什么规则排除那些组件（这里的Filter也为注解）；includeFilters = Filter[] 指定扫描的时候只需要包含那些组件（此时需要同时传入useDefaultFilters = false，该配置才可生效）；该注解可重复标记，指定多个扫描策略； |
| @ComponentScans | 与@Configuration配合使用，标注在类上；value=ComponentScan[]，封装多个@ComponentScan |
| @Filter         | 是@ComponentScan内部的注解，封装过滤条件；type = FilterType.xxx，指定过滤类型，classes=class<?>[]（FilterType为ANNOTATION、ASSIGNABLE_TYPE时）， 指定符合条件的类；pattern=String[]（FilterType为ASPECTJ、REGEX时）指定表达式；FilterType为枚举类：FilterType.ANNOTATION 按照注解的方式、FilterType.ASSIGNABLE_TYPE 按照给定的类型、FilterType.ASPECTJ 使用ASPECTJ表表达式、FilterType.REGEX 使用正则表达式、FilterType.CUSTOM 使用自定义规则 |
|                 |                                                              |
|                 |                                                              |
|                 |                                                              |
|                 |                                                              |
|                 |                                                              |
|                 |                                                              |
|                 |                                                              |
|                 |                                                              |
|                 |                                                              |

