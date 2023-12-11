package cn.openjava.basic.format.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "openjava.basic")
public class BasicProperties {
    /**
     * 模块
     */
    private String module;
}
