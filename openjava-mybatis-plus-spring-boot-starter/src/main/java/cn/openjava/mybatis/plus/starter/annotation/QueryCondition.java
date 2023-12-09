package cn.openjava.mybatis.plus.starter.annotation;

import cn.openjava.mybatis.plus.starter.enums.ConditionType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 查询条件-加在
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryCondition {
    /**
     * 查询类型
     *
     * @return
     */
    @AliasFor("type") ConditionType value() default ConditionType.EQ;

    @AliasFor("value") ConditionType type() default ConditionType.EQ;
}
