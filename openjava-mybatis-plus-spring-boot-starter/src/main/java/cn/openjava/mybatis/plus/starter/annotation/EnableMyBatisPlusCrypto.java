package cn.openjava.mybatis.plus.starter.annotation;

import cn.openjava.mybatis.plus.starter.interceptor.DBCryptoInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(DBCryptoInterceptor.class)
@Documented
public @interface EnableMyBatisPlusCrypto {
}
