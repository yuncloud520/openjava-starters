package cn.openjava.mybatis.plus.starter.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableDefaultMyBatisPlusConfig
@EnableMyBatisPlusCrypto
@Documented
public @interface EnableMybatisPlus {
    /**
     * 是否启用默认的配置
     *
     * @return 默认为true
     */
    boolean useDefaultConfig() default true;
}
