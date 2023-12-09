package cn.openjava.yc.nacos.service.impl;

import cn.openjava.yc.nacos.service.INacosService;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.cloud.nacos.registry.NacosServiceRegistry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NacosServiceImpl implements INacosService {

    @Resource
    private NacosRegistration nacosRegistration;

    @Resource
    private NacosServiceRegistry nacosServiceRegistry;

    /**
     * 从nacos中下线
     *
     * @return 是否成功
     */
    @Override
    public boolean deregister() {
        try {
            nacosServiceRegistry.deregister(nacosRegistration);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从nacos中上线
     *
     * @return 是否成功
     */
    @Override
    public boolean register() {
        try {
            nacosServiceRegistry.register(nacosRegistration);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
