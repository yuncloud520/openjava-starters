package cn.openjava.basic.format.aspect;


import cn.openjava.basic.crypto.properties.CryptoProperties;
import cn.openjava.basic.format.annotation.EncryptResult;
import cn.openjava.basic.format.enums.SystemExceptionResult;
import cn.openjava.basic.format.page.Result;
import cn.openjava.basic.format.utils.ResultCryptoUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
public class EncryptResultAspect {
    @Resource
    private CryptoProperties cryptoProperties;

    /**
     * 加密响应结果
     *
     * @param joinPoint
     * @return
     */
    @Around("@annotation(cn.openjava.basic.format.annotation.EncryptResult)")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        Object res;
        try {
            EncryptResult encryptResult = this.getEncryptResult(joinPoint);
            res = joinPoint.proceed();
            if (encryptResult == null || !cryptoProperties.isEnable()) {
                return res;
            } else {
                if (cryptoProperties.getCryptoType() == null) {
                    return ResultCryptoUtil.encryptResult((Result) res, encryptResult.type());
                } else {
                    return ResultCryptoUtil.encryptResult((Result) res, cryptoProperties.getCryptoType());
                }
            }
        } catch (Throwable throwable) {
            return Result.warning(SystemExceptionResult.EXCEPTION);
        }
    }

    /**
     * 获取注解
     *
     * @param joinPoint
     * @return
     */
    private EncryptResult getEncryptResult(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method.getAnnotation(EncryptResult.class);
    }


}

