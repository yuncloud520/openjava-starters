package cn.openjava.openapi.starter.annotation;


import cn.openjava.openapi.starter.enums.ApiTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiType {
    /**
     * 接口版本号(对应swagger中的group)
     *
     * @return String[]
     */
    ApiTypeEnum[] value() default ApiTypeEnum.PC;

}