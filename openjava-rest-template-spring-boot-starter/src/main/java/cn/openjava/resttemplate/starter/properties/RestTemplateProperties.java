package cn.openjava.resttemplate.starter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "openjava.rt")
@Component
@Getter
@Setter
public class RestTemplateProperties {
    /**
     * 连接超时时间
     */
    private long connectTimeout = 3;
    /**
     * 读取超时时间
     */
    private long readTimeout = 5;

}
