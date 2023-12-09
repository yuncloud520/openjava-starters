package cn.openjava.openapi.starter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * swagger下拉选项
 */
@Getter
@AllArgsConstructor
public enum ApiTypeEnum {

    /**
     * PC端接口
     */
    PC("pc", "1-PC"),
    /**
     * 手机端接口
     */
    APP("app", "2-APP"),
    /**
     * feign接口
     */
    FEIGN("feign", "3-feign"),
    /**
     * api对外接口
     */
    API("api", "4-api"),
    /**
     * 其它接口
     */
    INNER("inner", "5-inner");
    /**
     * 编码
     */
    private String code;

    /**
     * 返回结果描述
     */
    private String text;


}
