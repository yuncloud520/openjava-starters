package cn.openjava.mybatis.plus.starter.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrimaryKey {
}
