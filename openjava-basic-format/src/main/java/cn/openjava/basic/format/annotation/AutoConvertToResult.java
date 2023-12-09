package cn.openjava.basic.format.annotation;

import cn.openjava.basic.format.aspect.AutoConvertToResultAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(AutoConvertToResultAspect.class)
public @interface AutoConvertToResult {
}
