package cn.openjava.mybatis.plus.starter.annotation;

import cn.hutool.core.annotation.Alias;
import cn.openjava.basic.crypto.enums.CryptoType;

import java.lang.annotation.*;

/**
 * 加解密字段
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EndecryptField {
    /**
     * 加密方法类型
     *
     * @return
     */
    @Alias("cryptoType")
    CryptoType value() default CryptoType.BASE64;

    @Alias("value")
    CryptoType cryptoType() default CryptoType.BASE64;
}
