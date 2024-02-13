package com.jfc.superheroes.utils.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;

@Aspect
public class AswLogAspectConfig {
    public static final String ANNOTATION = "com.jfc.superheroes.utils.log.AswLog";
    public static final String AROUND_CONDITION = "@within(com.jfc.superheroes.utils.log.AswLog) || @annotation(com.jfc.superheroes.utils.log.AswLog)";

    public AswLogAspectConfig() {
    }

    private AswLog getAnnotation(Class<?> cls, Method method) {
        AswLog annotation = (AswLog)method.getAnnotation(AswLog.class);
        if (annotation == null) {
            annotation = (AswLog)cls.getAnnotation(AswLog.class);
        }

        return annotation != null && annotation.value() ? annotation : null;
    }

    private Logger getLogger(AswLogLevel aswLogLevel, Class<?> cls) {
        return AswLogLevel.OFF != aswLogLevel ? LoggerFactory.getLogger(cls) : null;
    }

    private void doLogging(AswLogLevel aswLogLevel, Logger logger, StringBuilder buffer) {
        if (AswLogLevel.ERROR == aswLogLevel && logger.isErrorEnabled()) {
            logger.error(buffer.toString());
        } else if (AswLogLevel.WARN == aswLogLevel && logger.isWarnEnabled()) {
            logger.warn(buffer.toString());
        } else if (AswLogLevel.INFO == aswLogLevel && logger.isInfoEnabled()) {
            logger.info(buffer.toString());
        } else if (AswLogLevel.DEBUG == aswLogLevel && logger.isDebugEnabled()) {
            logger.debug(buffer.toString());
        } else if (AswLogLevel.TRACE == aswLogLevel && logger.isTraceEnabled()) {
            logger.trace(buffer.toString());
        } else if (AswLogLevel.ALL == aswLogLevel && logger.isTraceEnabled()) {
            logger.trace(buffer.toString());
        }

    }

    protected void logObject(Object obj, StringBuilder buffer) {
        if (obj != null && obj.getClass() == byte[].class) {
            buffer.append("ByteArray[").append(Array.getLength(obj)).append("]");
        } else if (obj != null && obj.getClass() == String.class) {
            buffer.append('"').append(obj).append('"');
        } else {
            buffer.append(obj);
        }

    }

    protected void logArgs(Object[] args, StringBuilder buffer) {
        if (args != null) {
            for(int i = 0; i < args.length; ++i) {
                if (i > 0) {
                    buffer.append(",");
                }

                this.logObject(args[i], buffer);
            }
        }

    }

    protected void methodBegin(AswLog annotation, Logger logger, Method method, Object[] args) {
        StringBuilder buffer = new StringBuilder("Method Begin: ");
        buffer.append(method.getName());
        buffer.append("(");
        this.logArgs(args, buffer);
        buffer.append(")");
        this.doLogging(annotation.logLevel(), logger, buffer);
    }

    private Integer getCollectionSize(Object result) {
        Integer size = null;
        if (result != null) {
            if (result.getClass().isArray()) {
                size = Array.getLength(result);
            } else if (result instanceof Collection) {
                size = ((Collection)result).size();
            }
        }

        return size;
    }

    protected void methodEnd(AswLog annotation, Logger logger, Method method, Object result, long time) {
        StringBuilder buffer = new StringBuilder("Method End: ");
        buffer.append(method.getName());
        buffer.append(" ");
        buffer.append("(");
        buffer.append(time).append(" ms");
        buffer.append(")");
        buffer.append(" => ");
        Integer numberOfElements = this.getCollectionSize(result);
        if (numberOfElements != null) {
            buffer.append("(");
            buffer.append(numberOfElements);
            buffer.append(numberOfElements == 1 ? " element" : " elements");
            buffer.append(")");
            if (numberOfElements <= annotation.resultCollectionMaxSize()) {
                buffer.append(": ");
                this.logObject(result, buffer);
            }
        } else if (method.getReturnType() != Void.TYPE && method.getReturnType() != Void.class) {
            this.logObject(result, buffer);
        } else {
            buffer.append("void");
        }

        this.doLogging(annotation.logLevel(), logger, buffer);
    }

    private void methodError(AswLog annotation, Logger logger, Method method, Object[] args, Throwable th) {
        while(th.getCause() != null) {
            th = th.getCause();
        }

        StringBuilder buffer = new StringBuilder("Error in Method !!!: ");
        buffer.append(method.getName());
        buffer.append("(");
        this.logArgs(args, buffer);
        buffer.append(")");
        buffer.append(" => ");
        buffer.append(th.getMessage());
        logger.error(buffer.toString());
    }

    @Around("@within(com.jfc.superheroes.utils.log.AswLog) || @annotation(com.jfc.superheroes.utils.log.AswLog)")
    public Object methodEntry(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Class<?> resultType = ((MethodSignature)joinPoint.getSignature()).getReturnType();
        Class<?>[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getParameterTypes();
        Object target = joinPoint.getThis();
        Object[] args = joinPoint.getArgs();
        Class<?> cls = AopUtils.getTargetClass(target);
        AswLog annotation = this.getAnnotation(cls, method);
        if (annotation != null) {
            Logger logger = this.getLogger(annotation.logLevel(), cls);
            if (logger != null) {
                this.methodBegin(annotation, logger, method, args);
                long begin = System.currentTimeMillis();

                Object result;
                try {
                    result = joinPoint.proceed();
                } catch (Throwable var15) {
                    this.methodError(annotation, logger, method, args, var15);
                    throw var15;
                }

                long end = System.currentTimeMillis();
                this.methodEnd(annotation, logger, method, result, end - begin);
                return result;
            }
        }

        return joinPoint.proceed();
    }
}