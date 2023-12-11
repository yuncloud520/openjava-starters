package cn.openjava.openfeign.interceptor;

import cn.openjava.openfeign.properties.FeignProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * feign
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    /**
     * feign属性
     */
    @Resource
    private FeignProperties properties;

    /**
     * 解析URL
     *
     * @param serviceName 服务名称
     * @param url         基础URL
     * @return 完整的URL
     */
    private static String parseUrl(String serviceName, String url) {
        return "http://" + serviceName + url;
    }

    /**
     * 重新指定URL
     *
     * @param template RequestTemplate对象
     */
    @Override
    public void apply(RequestTemplate template) {
//        if (properties.isDebugEnable()) {
//            Target<?> target = template.feignTarget();
//            template.target(parseUrl(target.name(), properties.getDebugUrl()) + target.url().split(target.name())[1]);
//        }
    }
}
