package cn.openjava.mybatis.plus.starter.annotation;

import cn.hutool.core.annotation.Alias;
import cn.openjava.mybatis.plus.starter.enums.PageTool;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoPage {
    /**
     * 分页工具
     *
     * @return
     */
    @Alias("tool")
    PageTool value() default PageTool.PAGE_HELPER;

    @Alias("value")
    PageTool tool() default PageTool.PAGE_HELPER;

    /**
     * 是否拼接where条件
     *
     * @return 默认为false
     */
    boolean splitWhere() default false;
}
