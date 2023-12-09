package cn.openjava.basic.format.annotation;


import cn.openjava.basic.crypto.enums.CryptoType;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptResult {
    /**
     * 加密类型
     *
     * @return 类型
     */
    CryptoType type() default CryptoType.SM234;
}
