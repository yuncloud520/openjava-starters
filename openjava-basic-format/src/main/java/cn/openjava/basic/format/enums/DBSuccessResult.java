package cn.openjava.basic.format.enums;

import cn.openjava.basic.format.result.ISuccessResult;
import cn.openjava.basic.format.result.ResultType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum DBSuccessResult implements ISuccessResult {
    /**
     * 成功
     */
    INSERT_SUCCESS(ResultType.SUCCESS, 201, "新增成功"),
    DELETE_SUCCESS(ResultType.SUCCESS, 202, "删除成功"),
    UPDATE_SUCCESS(ResultType.SUCCESS, 203, "修改成功"),
    QUERY_SUCCESS(ResultType.SUCCESS, 204, "查询成功");

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
