package cn.openjava.resttemplate.starter.annotation;


import cn.openjava.resttemplate.starter.config.RestTemplateConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RestTemplateConfig.class})
public @interface EnableRestTemplate {
}


