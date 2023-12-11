package cn.openjava.resttemplate.starter.config;

import cn.openjava.resttemplate.starter.properties.RestTemplateProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.Duration;

@Import(RestTemplateProperties.class)
public class RestTemplateConfig {
    @Resource
    private RestTemplateProperties properties;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        builder.setConnectTimeout(Duration.ofSeconds(properties.getConnectTimeout()));
        builder.setReadTimeout(Duration.ofSeconds(properties.getReadTimeout()));
        return builder.build();
    }
}

