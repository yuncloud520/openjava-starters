package com.alibaba.cloud.nacos.client;

import cn.openjava.gmsm.utils.GmConfigurationUtil;
import com.alibaba.cloud.nacos.NacosPropertySourceRepository;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.StringReader;
import java.util.*;

public class NacosPropertySourceBuilder {
    private static final Logger log = LoggerFactory.getLogger(NacosPropertySourceBuilder.class);
    private static final Properties EMPTY_PROPERTIES = new Properties();
    private ConfigService configService;
    private long timeout;

    public NacosPropertySourceBuilder(ConfigService configService, long timeout) {
        this.configService = configService;
        this.timeout = timeout;
    }

    public long getTimeout() {
        return this.timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public ConfigService getConfigService() {
        return this.configService;
    }

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    NacosPropertySource build(String dataId, String group, String fileExtension, boolean isRefreshable) {
        Properties p = this.loadNacosData(dataId, group, fileExtension);
        NacosPropertySource nacosPropertySource = new NacosPropertySource(group, dataId, this.propertiesToMap(p), new Date(), isRefreshable);
        NacosPropertySourceRepository.collectNacosPropertySources(nacosPropertySource);
        return nacosPropertySource;
    }

    /**
     * 加载nacos的数据
     *
     * @param dataId        id
     * @param group         分组
     * @param fileExtension 文件扩展名
     * @return 配置属性
     */
    private Properties loadNacosData(String dataId, String group, String fileExtension) {
        String data = null;
        try {
            data = this.configService.getConfig(dataId, group, this.timeout);
            if (!StringUtils.isEmpty(data)) {
                log.info(String.format("Loading nacos data, dataId: '%s', group: '%s'", dataId, group));
                if (fileExtension.equalsIgnoreCase("properties")) {
                    Properties properties = new Properties();
                    properties.load(new StringReader(data));
                    return properties;
                }

                if (fileExtension.equalsIgnoreCase("yaml") || fileExtension.equalsIgnoreCase("yml")) {
                    YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
                    yamlFactory.setResources(new Resource[]{new ByteArrayResource(data.getBytes())});
                    return yamlFactory.getObject();
                }
            }
        } catch (NacosException var6) {
            log.error("get data from Nacos error,dataId:{}, ", dataId, var6);
        } catch (Exception var7) {
            log.error("parse data from Nacos error,dataId:{},data:{},", new Object[]{dataId, data, var7});
        }

        return EMPTY_PROPERTIES;
    }

    /**
     * 配置属性转map
     *
     * @param properties 配置属性
     * @return map
     */
    private Map<String, Object> propertiesToMap(Properties properties) {
        Map<String, Object> result = new HashMap(16);
        Enumeration keys = properties.propertyNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = properties.getProperty(key);
            if (value != null) {
                value = value.trim();
            }
            //如果字符串以“CBC(”开头并且以“)”结尾，则认为是被加密的字符串，此时需要解密处理
            if (value.startsWith("CBC(") && value.endsWith(")")) {
                value = GmConfigurationUtil.decrypt(value.substring(value.indexOf("(") + 1, value.lastIndexOf(")")));
            }
            result.put(key, value);
        }
        return result;
    }
}