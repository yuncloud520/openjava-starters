package cn.openjava.basic.format.aspect;


import cn.openjava.basic.format.enums.SystemExceptionResult;
import cn.openjava.basic.format.exception.BaseException;
import cn.openjava.basic.format.exception.SystemException;
import cn.openjava.basic.format.page.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@Slf4j
public class CheckResultAspect {
    /**
     * 校验Result对象
     *
     * @param joinPoint
     */
    @Around("execution(*  cn.openjava..controller.web..*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (signature.getReturnType().getName().equals(Result.class.getName())) {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
//                log.error(throwable.getMessage(), throwable);
                throw (BaseException) throwable;
            }
        } else {
            throw new SystemException(SystemExceptionResult.RESULT_NOT_ALLOWED_EXCEPTION);
        }
    }
}

