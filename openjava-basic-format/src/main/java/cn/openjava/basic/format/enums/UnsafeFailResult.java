package cn.openjava.basic.format.enums;

import cn.openjava.basic.format.result.IFailResult;
import cn.openjava.basic.format.result.ResultType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum UnsafeFailResult implements IFailResult {
    SECURITY_ERROR(ResultType.WARNING, 400, "安全校验失败"),
    SECURITY_NOT_AUTH(ResultType.WARNING, 401, "登录认证未通过"),
    SECURITY_NOT_ALLOWED_HTTP_METHOD(ResultType.WARNING, 402, "不安全的http方法");
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
