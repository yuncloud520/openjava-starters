package cn.openjava.oss.starter.error;

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
public enum OssFailResult implements IFailResult {
    NO_SUCH_KEY(ResultType.ERROR, 1001, "没有此编码"),
    NO_SUCH_BUCKET(ResultType.ERROR, 1002, "没有此桶"),
    FAIL(ResultType.ERROR, 1003, "失败");

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
