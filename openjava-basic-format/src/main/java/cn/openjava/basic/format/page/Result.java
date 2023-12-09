package cn.openjava.basic.format.page;

import cn.openjava.basic.format.enums.CommonSuccessResult;
import cn.openjava.basic.format.enums.SystemExceptionResult;
import cn.openjava.basic.format.result.IResult;
import cn.openjava.basic.format.result.ISuccessResult;
import cn.openjava.basic.format.result.ResultType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.MessageFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 响应结果
 *
 * @param <T> 任意类型
 */
@Getter
@Builder
@ApiModel(value = "响应结果", description = "响应结果")
public class Result<T> {
    /**
     * UUID
     */
    @ApiModelProperty("结果ID")
    private String uuid;

    /**
     * 结果类型
     */
    @ApiModelProperty("结果类型")
    private ResultType type;

    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
    private int code;


    /**
     * 结果值
     */
    @ApiModelProperty("结果值")
    private T data;

    /**
     * 时间戳
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("时间戳")
    private Date time;

    /**
     * 加密
     */
    @ApiModelProperty("加密标识")
    private int crypto;

    /**
     * 成功
     *
     * @param successResult 数据
     * @return 操作结果
     */
    public static <T> Result success(ISuccessResult successResult) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(successResult.message()).code(CommonSuccessResult.SUCCESS.code()).type(ResultType.SUCCESS).build();
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return 操作结果
     */
    public static <T> Result success(T data) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(data).code(CommonSuccessResult.SUCCESS.code()).type(ResultType.SUCCESS).build();
    }

    public static <T> Result success(T data, int crypto) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(data).code(CommonSuccessResult.SUCCESS.code()).crypto(crypto).type(ResultType.SUCCESS).build();
    }

    /**
     * 消息-返回数据
     *
     * @param data 数据
     * @param <T>  泛型，任意类型
     * @return result对象
     */
    public static <T> Result info(T data) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(data).code(CommonSuccessResult.INFO.code()).type(ResultType.INFO).build();
    }

    /**
     * 失败
     *
     * @param exMessage 错误消息
     * @return 结果
     */
    public static Result warning(String exMessage) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(exMessage).code(SystemExceptionResult.EXCEPTION.code()).type(ResultType.WARNING).build();
    }

    /**
     * 失败
     *
     * @param iResult 页面会话
     * @return 结果
     */
    public static Result warning(IResult iResult) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(iResult.message()).code(iResult.code()).type(ResultType.WARNING).build();
    }

    public static Result warning(IResult iResult, Object... fillStr) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(MessageFormat.format(iResult.message(), fillStr)).code(iResult.code()).type(ResultType.WARNING).build();
    }

    /**
     * 失败
     *
     * @param eMsg 错误消息
     * @param code 错误码
     * @return 结果
     */
    public static Result warning(String eMsg, int code) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(eMsg).code(code).type(ResultType.WARNING).build();
    }

    /**
     * 失败
     *
     * @param exMessage 错误消息
     * @return 结果
     */
    public static Result error(String exMessage) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(exMessage).code(SystemExceptionResult.EXCEPTION.code()).type(ResultType.ERROR).build();
    }

    /**
     * 失败
     *
     * @param iResult 页面会话
     * @return 结果
     */
    public static Result error(IResult iResult) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(iResult.message()).code(iResult.code()).type(ResultType.ERROR).build();
    }

    public static Result error(IResult iResult, Object... fillStr) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(MessageFormat.format(iResult.message(), fillStr)).code(iResult.code()).type(ResultType.ERROR).build();
    }

    /**
     * 消息-不确定是SUCCESS还是WARNING还是ERROR
     *
     * @param iResult
     * @return
     */
    public static Result message(IResult iResult) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(iResult.message()).code(iResult.code()).type(iResult.type()).build();
    }

    /**
     * 消息-不确定是SUCCESS还是WARNING还是ERROR
     *
     * @param iResult
     * @return
     */
    public static Result message(IResult iResult, Object... fillStr) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(MessageFormat.format(iResult.message(), fillStr)).code(iResult.code()).type(iResult.type()).build();
    }

    /**
     * 失败
     *
     * @param eMsg 错误消息
     * @param code 错误码
     * @return 结果
     */
    public static Result error(String eMsg, int code) {
        return Result.builder().uuid(UUID.randomUUID().toString()).time(new Date()).data(eMsg).code(code).type(ResultType.ERROR).build();
    }

    public Result setType(ResultType type) {
        this.type = type;
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public Result setCrypto(int crypto) {
        this.crypto = crypto;
        return this;
    }
}
