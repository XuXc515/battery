package com.xxc.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author xxc
 * @date 2021/1/12 - 16:18
 */
@Component
@Aspect
public class LogAspect {

    //引入日志配置
    private static final Log LOG = LogFactory.getLog(LogAspect.class);

    /**
     * 定义一个切入点 只拦截controller.
     * 解释下：
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 定义在web包或者子包
     * ~ 第三个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* com.xxc.controller..*.*(..))")
    public void logPointcut() {
    }

    //around(和上面的方法名一样)
    @org.aspectj.lang.annotation.Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("=====================================Method  start====================================");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        long start = System.currentTimeMillis();

        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = date_format.format(System.currentTimeMillis());

        try {
            //切面方法的执行
            Object result = joinPoint.proceed();
            //记录执行完成之后的时间
            long end = System.currentTimeMillis();
            LOG.info("请求地址:" + request.getRequestURI());
            LOG.info("开始时间:" + dateStr);
            LOG.info("用户IP:" + request.getRemoteAddr());
            LOG.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            LOG.info("参数: " + Arrays.toString(joinPoint.getArgs()));
            LOG.info("执行时间: " + (end - start) + " ms!");
            LOG.info("=====================================Method  End====================================");
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            LOG.info("URL:" + request.getRequestURI());
            LOG.info("开始时间:" + start);
            LOG.info("IP:" + request.getRemoteAddr());
            LOG.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            LOG.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
            LOG.info("执行时间: " + (end - start) + " ms!");
            LOG.info("=====================================Method  End====================================");
            throw e;
        }
    }

}
