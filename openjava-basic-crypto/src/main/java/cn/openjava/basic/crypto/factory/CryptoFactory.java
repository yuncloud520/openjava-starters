package cn.openjava.basic.crypto.factory;

import cn.openjava.basic.crypto.enums.CryptoType;

public class CryptoFactory {

    /**
     * 加密对象
     */
    private YcCrypto ycCrypto;

    /**
     * 空的构造方法
     */
    public CryptoFactory() {

    }

    /**
     * 创建实例
     *
     * @param cryptoType 加密类型
     * @return 加密对象
     */
    public YcCrypto newInstance(CryptoType cryptoType) {
        switch (cryptoType) {
            case SM2:
                return new Sm2Crypto();
            case SM234:
                return new Sm234Crypto();
            case BASE64:
                return new Base64Crypto();
        }
        return null;
    }

    /**
     * 获取加密对象
     *
     * @return 加密对象
     */
    public YcCrypto getInstance() {
        if (ycCrypto == null) {
            ycCrypto = newInstance(CryptoType.defaultType());
        }
        return ycCrypto;
    }

    /**
     * 获取加密对象
     *
     * @param cryptoType
     * @return
     */
    public YcCrypto getInstance(CryptoType cryptoType) {
        if (ycCrypto == null) {
            ycCrypto = newInstance(cryptoType);
        }
        return ycCrypto;
    }
}
