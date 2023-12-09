package cn.openjava.mybatis.plus.starter.annotation;

import cn.openjava.mybatis.plus.starter.config.FieldFillConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({FieldFillConfig.class})
public @interface EnableFieldFill {
}
