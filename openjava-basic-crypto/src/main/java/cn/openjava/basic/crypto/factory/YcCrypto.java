package cn.openjava.basic.crypto.factory;

import cn.hutool.json.JSONUtil;

public interface YcCrypto {
    /**
     * 加密字符串
     *
     * @param plaintext 明文
     * @return 字符串
     */
    String encryptString(String plaintext);

    /**
     * 解密字符串
     *
     * @param cipherText 密文
     * @return 字符串
     */
    String decryptString(String cipherText);

    /**
     * 加密对象
     *
     * @param object 对象
     * @return 字符串
     */
    default String encryptObject(Object object) {
        return encryptString(JSONUtil.toJsonStr(object));
    }

    /**
     * 解密对象
     *
     * @param cipherText 密文
     * @param clazz      类
     * @param <T>        对象
     * @return 对象
     */
    default <T> T decryptObject(String cipherText, Class<T> clazz) {
        String string = decryptString(cipherText);
        return JSONUtil.toBean(string, clazz);
    }
}
