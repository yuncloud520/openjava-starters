package cn.openjava.basic.crypto.utils;

import cn.openjava.basic.crypto.factory.YcCrypto;
import cn.openjava.basic.crypto.properties.CryptoProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CryptoUtil implements YcCrypto {
    /**
     * 加密配置
     */
    @Resource
    private CryptoProperties cryptoProperties;


    /**
     * 加密字符串
     *
     * @param plaintext 明文
     * @return
     */
    @Override
    public String encryptString(String plaintext) {
        return CryptoUtilBuilder.build(cryptoProperties.getCryptoType()).encryptString(plaintext);
    }

    /**
     * 解密字符串
     *
     * @param ciphertext 密文
     * @return
     */
    @Override
    public String decryptString(String ciphertext) {
        return CryptoUtilBuilder.build(cryptoProperties.getCryptoType()).decryptString(ciphertext);
    }
}
