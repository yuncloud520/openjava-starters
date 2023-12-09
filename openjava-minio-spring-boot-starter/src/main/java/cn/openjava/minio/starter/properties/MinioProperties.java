package cn.openjava.minio.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "yc.minio")
public class MinioProperties {

    /**
     * minio服务Ip地址
     */
    private String endpoint;
    /**
     * minio接入用户名
     */
    private String accessKey;
    /**
     * secretKey
     */
    private String secretKey;
    /**
     * 通讯方式是否为https
     */
    private boolean secure = false;
    /**
     * 存储桶名
     */
    private String bucketName;
}
