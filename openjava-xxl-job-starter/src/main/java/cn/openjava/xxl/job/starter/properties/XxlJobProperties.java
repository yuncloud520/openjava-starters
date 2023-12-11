package cn.openjava.xxl.job.starter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "yc.xxl-job")
public class XxlJobProperties {

    /**
     * 应用名称，不传则默认为${spring.application.name}，建议不传。
     */
    private String appName;
    /**
     * 服务端地址
     */
    private String adminAddresses;
    /**
     * token
     */
    private String accessToken = "default_token";

    /**
     * 地址
     */
    private String address;

    /**
     * IP
     */
    private String ip;

    /**
     * 端口
     */
    private int port = 9999;

    /**
     * 日志路径
     */
    private String logPath = "/data/applogs/xxl-job/jobhandler";

    /**
     * 日志保留时间
     */
    private int logRetentionDays = 30;
}
