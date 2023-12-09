package cn.openjava.yc.nacos.service.impl;

import cn.openjava.yc.nacos.handler.DefaultPostStartHandler;
import cn.openjava.yc.nacos.handler.DefaultPreStopHandler;
import cn.openjava.yc.nacos.handler.IPostStartHandler;
import cn.openjava.yc.nacos.handler.IPreStopHandler;
import cn.openjava.yc.nacos.service.IApplicationLifeCycleService;
import cn.openjava.yc.nacos.service.INacosService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ApplicationLifeCycleServiceImpl implements IApplicationLifeCycleService, ApplicationContextAware {

    /**
     * 应用上下文
     */
    private static ApplicationContext applicationContext;
    /**
     * nacos服务
     */
    @Resource
    private INacosService nacosService;
    /**
     * 启动后handler
     */
    private IPostStartHandler postStartHandler;
    /**
     * 停止前handler
     */
    private IPreStopHandler preStopHandler;
    /**
     * 存储IPostStartHandler接口的所有实现类
     */
    private Map<String, IPostStartHandler> postStartHandlerMap;

    /**
     * 存储IPreStopHandler接口的所有实现类
     */
    private Map<String, IPreStopHandler> preStopHandlerMap;

    /**
     * 应用启动后执行
     */
    @Override
    public void postStart() {
        initPostStartHandler();
        postStartHandler.postStart();
    }

    /**
     * 应用停止前执行
     *
     * @param isNacosDeregister 手动进行nacos下线操作
     */
    @Override
    public void preStop(boolean isNacosDeregister) {
        initPreStopHandler();
        if (isNacosDeregister) {
            preStopHandler.beforeNacosDeregister();
            nacosService.deregister();
            preStopHandler.afterNacosDeregister();
        } else {
            preStopHandler.preStop();
        }
    }

    /**
     * 设置应用上下文
     *
     * @param applicationContext 应用上下文
     * @throws BeansException bean异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 通过上下文，根据接口类型返回相应的所有实现类bean
        postStartHandlerMap = applicationContext.getBeansOfType(IPostStartHandler.class);
        preStopHandlerMap = applicationContext.getBeansOfType(IPreStopHandler.class);
        if (this.applicationContext == null) {
            this.applicationContext = applicationContext;
        }
    }

    /**
     * 初始化启动后handler
     */
    private void initPostStartHandler() {
        //初始化启动后handler
        if (postStartHandler == null) {
            if (postStartHandlerMap.size() == 1) {
                postStartHandler = applicationContext.getBean(DefaultPostStartHandler.class);
            } else {
                for (Map.Entry<String, IPostStartHandler> entry : postStartHandlerMap.entrySet()) {
                    if (!entry.getKey().equals(StringUtils.capitalize(DefaultPostStartHandler.class.getName()))) {
                        postStartHandler = applicationContext.getBean(entry.getValue().getClass());
                        break;
                    }
                }
            }
        }
    }

    /**
     * 初始化停止前handler
     */
    private void initPreStopHandler() {
        //初始化停止前handler
        if (preStopHandler == null) {
            if (preStopHandlerMap.size() == 1) {
                preStopHandler = applicationContext.getBean(DefaultPreStopHandler.class);
            } else {
                for (Map.Entry<String, IPreStopHandler> entry : preStopHandlerMap.entrySet()) {
                    if (!entry.getKey().equals(StringUtils.capitalize(DefaultPreStopHandler.class.getName()))) {
                        preStopHandler = applicationContext.getBean(entry.getValue().getClass());
                        break;
                    }
                }
            }
        }
    }
}
