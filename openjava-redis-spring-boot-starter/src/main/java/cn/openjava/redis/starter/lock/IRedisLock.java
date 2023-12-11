package cn.openjava.redis.starter.lock;

public interface IRedisLock {

    boolean lock();

    boolean lock(long timeoutSec);

    void unlock();
}
