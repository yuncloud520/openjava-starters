package cn.openjava.basic.crypto.enums;

public enum CryptoType {
    /**
     * sm2算法
     */
    SM2(10),
    /**
     * sm2+sm3+sm4
     */
    SM234(11),
    /**
     * base64
     */
    BASE64(20);
    /**
     * 加密类型编码
     */
    int cryptoCode;

    CryptoType(int cryptoCode) {
        this.cryptoCode = cryptoCode;
    }

    /**
     * 定义默认值
     *
     * @return 默认加密类型
     */
    public static CryptoType defaultType() {
        return BASE64;
    }

    public int getCryptoCode() {
        return this.cryptoCode;
    }
}
