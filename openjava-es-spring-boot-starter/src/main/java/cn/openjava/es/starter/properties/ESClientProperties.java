package cn.openjava.es.starter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "openjava.es")
@Component
@Getter
@Setter
public class ESClientProperties {
    /**
     * 地址，IP:端口
     */
    private String hostName;
    /**
     * 连接超时时间
     */
    private int connTimeout = 3000;
    /**
     *
     */
    private int socketTimeout = 3000;
    private int connectionRequestTimeout = 3000;
    private String username;
    private String password;
    private int keepAliveStrategy;
}
