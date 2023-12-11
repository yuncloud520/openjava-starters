package cn.openjava.redis.starter.annotation;


import cn.openjava.redis.starter.config.RedisConfig;
import cn.openjava.redis.starter.utils.RedisUtil;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RedisConfig.class, RedisUtil.class})
public @interface EnableRedisUtil {

}
