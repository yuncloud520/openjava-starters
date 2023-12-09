package cn.openjava.basic.format.enums;

import cn.openjava.basic.format.result.IFailResult;
import cn.openjava.basic.format.result.ResultType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * 异常会话
 */
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum ParamFailResult implements IFailResult {
    QUERY_PARAM_EXCEPTION(ResultType.WARNING, 300, "参数异常：{0}");

    /**
     * 类型
     */
    private ResultType type;

    /**
     * 消息编码
     */
    private int code;
    /**
     * 消息内容
     */
    private String message;
}
