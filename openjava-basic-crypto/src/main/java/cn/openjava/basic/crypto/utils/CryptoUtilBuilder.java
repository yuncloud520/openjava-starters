package cn.openjava.basic.crypto.utils;

import cn.openjava.basic.crypto.enums.CryptoType;
import cn.openjava.basic.crypto.factory.CryptoFactory;
import cn.openjava.basic.crypto.factory.YcCrypto;

public class CryptoUtilBuilder {

    private static CryptoFactory factory = new CryptoFactory();

    /**
     * 构建实例
     *
     * @return 加密对象
     */
    public static YcCrypto build() {
        return build(CryptoType.defaultType());
    }

    /**
     * 构建实例
     *
     * @param cryptoType 加密类型
     * @return 加密对象
     */
    public static YcCrypto build(CryptoType cryptoType) {
        return factory.getInstance(cryptoType);
    }
}
