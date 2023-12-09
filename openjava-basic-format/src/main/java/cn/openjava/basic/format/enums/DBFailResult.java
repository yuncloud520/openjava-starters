package cn.openjava.basic.format.enums;

import cn.openjava.basic.format.result.IFailResult;
import cn.openjava.basic.format.result.ResultType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum DBFailResult implements IFailResult {
    /**
     * 失败
     */
    DELETE_FAIL_NOT_FOUND(ResultType.WARNING, 202, "删除失败，数据不存在"),
    /**
     * 失败
     */
    DELETE_FAIL_EXCEPTION(ResultType.ERROR, 203, "删除失败，发生异常"),
    /**
     * 失败
     */
    DELETE_FAIL_NO_CONDITION(ResultType.WARNING, 204, "删除失败，不允许不加条件进行删除"),

    /**
     * 部分失败
     */
    DELETE_SUCCESS_AND_FAIL(ResultType.WARNING, 202, "共有{0}条数据，成功{1}条，失败{2}条"),

    /**
     * 失败
     */
    INSERT_FAIL(ResultType.WARNING, 205, "新增失败"),
    /**
     * 失败
     */
    INSERT_FAIL_EXCEPTION(ResultType.ERROR, 207, "新增失败，发生异常"),
    /**
     * 失败
     */
    UPDATE_FAIL(ResultType.WARNING, 208, "修改失败，数据不存在"),
    /**
     * 失败
     */
    UPDATE_FAIL_EXCEPTION(ResultType.WARNING, 205, "修改失败，发生异常"),
    /**
     * 查询失败-无数据
     */
    QUERY_FAIL_NO_DATA(ResultType.WARNING, 208, "查询失败，无此数据"),
    /**
     * 删除失败
     */
    DELETE_FAIL_NO_ID(ResultType.WARNING, 208, "删除失败，未传入ID");
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
