package com.zh.config;

import com.zh.aop.LogAspect;
import com.zh.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP：【动态代理】
 *      指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 *
 * 1. 导入AOP模块Spring AOP (spring-aspects)
 * 2. 定义一个业务逻辑类(MathCalculator),在业务逻辑运行的时候，将日志打印
 *      （ 方法之前，方法运行结束，方法出现异常，xxx）
 * 3. 定义一个日志前切面类(LogAspect),切面里的方法需要动态感知MathCalculator.div运行到哪里了
 *      通知方法：
 *          前置通知(@Before)：logStart，在目标方法运行之前运行
 *          后置通知(@After)：logEnd，在目标方法运行结束之后运行(无论方法正常结束还是异常结束都调用)
 *          返回通知(@AfterReturning)：logReturn，在目标方法正常返回之后运行
 *          异常通知(@AfterThrowing)：logException，在目标方法出现异常之后运行
 *          环绕通知(@Around)：动态代理，手动推进目标方法运行(joinPoint.proceed())
 * 4. 给切面类的目标方法标注何时何地运行（通知注解）
 * 5. 将切面类和业务逻辑类（目标方法所在类）都加入到容器中；
 * 6. 必须告诉spring哪个类是切面类（给切面类上加一个注解：@Aspect）
 * 7. 给配置类在加@EnableAspectJAutoProxy 【开启基于注解的aop模式】
 *      在Spring中有很多的Enablexxx，表示开始某项功能
 *
 * 三步：
 * 1.将业务逻辑组件和切面类都加入到容器中，告诉spring那个是切面类（@Aspect）
 * 2.在切面类的每一个通知方法上标注通知注解，告诉spring何时何地运行（切入点表达式）
 * 3.开启基于注解的aop模式：@EnableAspectJAutoProxy
 *
 * AOP原理 【看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么】
 * 1. @EnableAspectJAutoProxy 是什么
 *      @Import(AspectJAutoProxyRegistrar.class) 给容器中导入AspectJAutoProxyRegistrar
 *          利用AspectJAutoProxyRegistrar自定义给容器中注册bean
 *          internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *      给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
 * 2. AnnotationAwareAspectJAutoProxyCreator
 *      AspectJAwareAdvisorAutoProxyCreator
 *          AbstractAdvisorAutoProxyCreator
 *              AbstractAutoProxyCreator
 *                  实现 SmartInstantiationAwareBeanPostProcessor
 *                  实现 BeanFactoryAware
 *
 *
 *                  关注后置处理器（在Bean初始化前后做事情）、自动装配BeanFactory
 *
 * @author Zhaohui
 * @date/10/12
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    // 将业务逻辑类加入到容器中
    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    // 将切面类加入到容器中
    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

}
