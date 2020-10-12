package com.zh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 切面类
 *
 * @author Zhaohui
 * @Aspect 告诉Spring当前类是一个切面类
 * @date 2020/10/12
 */
@Aspect
public class LogAspect {

    // 抽取公共的切入点表达式
    // 1. 本类引用,直接写方法名+小括号
    // 2. 其他的切面引入,写方法全名+小括号
    @Pointcut("execution(public int com.zh.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    ;

    // @Before在目标方法之前切入；切入点表达式，指定在那个方法切入
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("" + joinPoint.getSignature().getName() + "@Before除法运行。。。参数列表是：{" + Arrays.asList(args) + "}");
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("" + joinPoint.getSignature().getName() + "@After除法结束。。。参数列表是：{}");
    }

    // JointPoint一定要出现在参数表的第一位
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("" + joinPoint.getSignature().getName() + "@AfterReturning除法正常返回。。。运行结果：{" + result + "}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println("" + joinPoint.getSignature().getName() + "@AfterThrowing除法异常，异常信息：{" + exception + "}");
    }
}
