package cn.openjava.mybatis.plus.starter.annotation;

import cn.openjava.mybatis.plus.starter.config.DefaultMyBatisPlusConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(DefaultMyBatisPlusConfig.class)
@Documented
public @interface EnableDefaultMyBatisPlusConfig {

}
