package cn.openjava.openfeign.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * feign属性
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "openjava.feign")
public class FeignProperties {
    /**
     * 是否开启调试模式，用于本地开发时服务调用k8s集群中的pod节点
     */
    private boolean debugEnable = false;

    /**
     * 调试时的url，默认为：服务名+.feign.openjava.cn，如yc-log.feign.openjava.cn
     */
    private String debugUrl = ".feign.openjava.cn";
}
