package cn.openjava.basic.crypto.properties;

import cn.openjava.basic.crypto.enums.CryptoType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "yc.crypto")
public class CryptoProperties {
    /**
     * 是否开启加密
     */
    boolean enable = true;
    /**
     * 加密类型
     */
    private CryptoType cryptoType;
}
