package cn.openjava.basic.format.enums;

import cn.openjava.basic.format.result.ISuccessResult;
import cn.openjava.basic.format.result.ResultType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum CommonSuccessResult implements ISuccessResult {
    /**
     * 消息
     */
    INFO(ResultType.INFO, 200, "{0}"),
    /**
     * 成功
     */
    SUCCESS(ResultType.SUCCESS, 200, "成功");
    /**
     *
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
