package cn.openjava.nacos.service;

public interface IApplicationLifeCycleService {
    /**
     * 应用启动后执行
     */
    void postStart();

    /**
     * 应用停止前执行
     *
     * @param isNacosDeregister 手动进行nacos下线操作
     */
    void preStop(boolean isNacosDeregister);
}
