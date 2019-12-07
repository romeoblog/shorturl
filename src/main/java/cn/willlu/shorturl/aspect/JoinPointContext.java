package cn.willlu.shorturl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author willlu.zheng
 * @date 2019-12-07
 */
public class JoinPointContext {

    private final JoinPoint joinPoint;

    private final Class<?> targetClass;

    private final Method method;

    private final Object[] args;

    public JoinPointContext(JoinPoint joinPoint) {
        this.joinPoint = joinPoint;
        targetClass = joinPoint.getTarget().getClass();
        method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        args = joinPoint.getArgs();
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }
}
