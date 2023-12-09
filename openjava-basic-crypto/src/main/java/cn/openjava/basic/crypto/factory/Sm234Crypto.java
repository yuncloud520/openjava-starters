package cn.openjava.basic.crypto.factory;

import cn.openjava.gmsm.utils.GmTransmissionUtil;

public class Sm234Crypto implements YcCrypto {

    @Override
    public String encryptString(String plaintext) {
        return GmTransmissionUtil.encrypt(plaintext);
    }

    @Override
    public String decryptString(String cipherText) {
        return GmTransmissionUtil.decrypt(cipherText).getCipherText();
    }
}
