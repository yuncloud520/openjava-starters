package cn.openjava.basic.crypto.factory;


import cn.hutool.core.codec.Base64;

public class Base64Crypto implements YcCrypto {

    @Override
    public String encryptString(String plaintext) {
        return Base64.encode(plaintext);
    }

    @Override
    public String decryptString(String cipherText) {
        return Base64.decodeStr(cipherText);
    }
}
