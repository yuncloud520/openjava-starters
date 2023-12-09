package cn.openjava.yc.nacos.handler;

public interface IPreStopHandler {

    /**
     * nacos下线前执行-此方法只有在isNacosDeregister为true时有效
     */
    void beforeNacosDeregister();

    /**
     * nacos下线后执行-此方法只有在isNacosDeregister为true时有效
     */
    void afterNacosDeregister();

    /**
     * 应用停止前执行-此方法只有在isNacosDeregister为false时有效
     */
    void preStop();
}
