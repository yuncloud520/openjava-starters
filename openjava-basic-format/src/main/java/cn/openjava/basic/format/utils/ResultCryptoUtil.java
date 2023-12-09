package cn.openjava.basic.format.utils;

import cn.openjava.basic.crypto.enums.CryptoType;
import cn.openjava.basic.crypto.utils.CryptoUtilBuilder;
import cn.openjava.basic.format.page.Result;

public class ResultCryptoUtil {
    /**
     * 加密结果类
     *
     * @param result
     * @return
     */
    public static Result encryptResult(Result result) {
        return encryptResult(result, CryptoType.defaultType());
    }

    /**
     * 加密结果类
     *
     * @param result
     * @param cryptoType
     * @return
     */
    public static Result encryptResult(Result result, CryptoType cryptoType) {
        return result.setData(CryptoUtilBuilder.build(cryptoType).encryptObject(result.getData())).setCrypto(cryptoType.getCryptoCode());
    }
}
