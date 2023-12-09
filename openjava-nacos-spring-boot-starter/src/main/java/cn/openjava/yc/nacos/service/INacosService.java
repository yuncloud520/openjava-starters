package cn.openjava.yc.nacos.service;

public interface INacosService {
    /**
     * 下线
     *
     * @return 成功或失败
     */
    boolean deregister();

    /**
     * 上线
     *
     * @return 成功或失败
     */
    boolean register();
}
