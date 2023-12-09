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
public enum SystemExceptionResult implements IFailResult {
    EXCEPTION(ResultType.ERROR, 600, "系统异常"),
    PARAM_PARSE_EXCEPTION(ResultType.ERROR, 601, "参数解析异常"),
    NULL_POINTER_EXCEPTION(ResultType.ERROR, 602, "空指针异常"),
    RESULT_NOT_ALLOWED_EXCEPTION(ResultType.ERROR, 603, "不允许返回此类型"),
    URL_NOT_FOUND(ResultType.ERROR, 604, "请求地址不存在"),
    THROWABLE_EXCEPTION(ResultType.ERROR, 605, "异常抛出"),
    NUMBER_CONVERT_EXCEPTION(ResultType.ERROR, 606, "数字转换异常"),
    METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(ResultType.ERROR, 607, "参数类型不匹配异常"),
    REFLECT_HANDLER_EXCEPTION(ResultType.ERROR, 608, "反射处理异常"),
    DOWNLOAD_FILE_EXCEPTION(ResultType.ERROR, 609, "文件下载异常"),
    USER_DEFINED_EXCEPTION(ResultType.ERROR, 699, "{0}");

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
