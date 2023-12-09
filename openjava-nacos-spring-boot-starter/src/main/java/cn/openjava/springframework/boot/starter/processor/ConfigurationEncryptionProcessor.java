package cn.openjava.springframework.boot.starter.processor;

import cn.openjava.gmsm.utils.GmConfigurationUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationEncryptionProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        //处理加密内容（获取到原有配置，然后解密放到新的map里面（key保持不变））
        Map<String, Object> map = new HashMap<>();
        for (PropertySource<?> ps : environment.getPropertySources()) {
            if (ps instanceof OriginTrackedMapPropertySource) {
                OriginTrackedMapPropertySource source = (OriginTrackedMapPropertySource) ps;
                for (String name : source.getPropertyNames()) {
                    Object value = source.getProperty(name);
                    if (value instanceof String) {
                        String str = (String) value;
                        //如果字符串以“CBC(”开头并且以“)”结尾，则认为是被加密的字符串，此时需要解密处理
                        if (str.startsWith("CBC(") && str.endsWith(")")) {
                            map.put(name, GmConfigurationUtil.decrypt(str.substring(str.indexOf("(") + 1, str.lastIndexOf(")"))));
                        }
                    }
                }
            }
        }
        //将解密的数据放入环境变量，并处于第一优先级上（这里一定要注意，覆盖其他配置）
        if (!map.isEmpty()) {
            environment.getPropertySources().addFirst(new MapPropertySource("custom-encrypt", map));
        }
    }
}
