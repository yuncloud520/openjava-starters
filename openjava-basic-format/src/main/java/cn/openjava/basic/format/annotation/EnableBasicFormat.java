package cn.openjava.basic.format.annotation;

import cn.openjava.basic.format.advice.ControllerExceptionAdvice;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ControllerExceptionAdvice.class)
public @interface EnableBasicFormat {
}
