# 组件作用域

- PROTOTYPE 多实例：IOC容器启动并不会调用方法创建对象放在容器中，每次获取才会调用方法创建对象；当用户从IOC容器中拿到创建的对象后，Spring不再对该对象进行管理，由用户自行管理
- SINGLETON 单实例(默认值)：IOC容器启动时会调用方法创建对象并放到IOC容器中，用户需要时每次都是直接从容器中拿；对象的生命周期始终由Spring管理
- REQUEST   同一次请求创建以个实例
- SESSION   同一个session创建一个实例



# 注解总结

## 组件注册相关

## 

| 注解        | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| @Bean       | 标注在方法上，给容器中注册一个Bean；类型为返回值的类型，id默认为方法名；可以通过name参数自定义id |
| @Scope      | 与@Bean配合使用，指定Bean的作用域                            |
| @Component  | 标注在类上，表示该类是一个组件；Spring可以通过自动扫描的方式将该类注册到ioc容器，id默认为类名首字母小写 |
| @Controller | 与@Component相同，强调标注的类是一个Controller               |
| @Service    | 与@Component相同，强调标注的类是一个Service                  |
| @Repository | 与@Component相同，强调标注的类是一个Dao                      |
|             |                                                              |
|             |                                                              |
|             |                                                              |
|             |                                                              |
|             |                                                              |
|             |                                                              |
|             |                                                              |
|             |                                                              |
|             |                                                              |
|             |                                                              |

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

