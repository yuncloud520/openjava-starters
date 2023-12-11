package cn.openjava.rabbitmq.annotation;

import cn.openjava.rabbitmq.config.RabbitMQConfig;
import cn.openjava.rabbitmq.properties.RabbitMQProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RabbitMQConfig.class, RabbitMQProperties.class})
@Documented
public @interface EnableRabbitMQ {

}
