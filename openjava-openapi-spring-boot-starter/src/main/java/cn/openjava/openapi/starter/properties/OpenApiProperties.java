package cn.openjava.openapi.starter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "yc.open-api")
public class OpenApiProperties {
    /**
     * 是否开启
     */
    boolean enable = true;
    /**
     * 标题
     */
    private String title = "云连互通";

    /**
     * 描述
     */
    private String description = "";

    /**
     * 服务URL
     */
    private String serviceUrl = "https://www.yuncloud.cn/";

    /**
     * 作者
     */
    private String author = "cn-yuncloud";

    /**
     * 作者URL
     */
    private String contactUrl = "";

    /**
     * 邮箱地址
     */
    private String email = "";
    /**
     * 版本号
     */
    private String version = "v1.0.0";

    /**
     * 基础包
     */
    private String basePackage = "cn.yuncloud";
}
