package cn.openjava.basic.format.annotation;

import cn.openjava.basic.format.aspect.CheckResultAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CheckResultAspect.class)
public @interface EnableCheckResult {
}
