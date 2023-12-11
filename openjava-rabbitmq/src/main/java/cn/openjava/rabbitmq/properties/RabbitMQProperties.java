package cn.openjava.rabbitmq.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "openjava.rabbitmq")
public class RabbitMQProperties {
    private String a;
}
