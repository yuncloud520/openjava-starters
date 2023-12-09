package cn.openjava.mybatis.plus.starter.annotation;

import cn.hutool.core.annotation.Alias;
import cn.openjava.basic.crypto.enums.CryptoType;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DecryptField {
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
