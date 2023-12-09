package cn.openjava.mybatis.plus.starter.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 基础分页VO类
 */
@Getter
@Setter
@ToString
public class PageVo {
    /**
     * 当前页
     */
    @ApiModelProperty("当前页码")
    @Min(value = 1, message = "当前页码不能为空且不能小于1")
    private int current;

    /**
     * 每页条目数
     */
    @ApiModelProperty("每页条目数")
    @Min(value = 1, message = "每页条目数不能为空且不能小于1")
    @Max(value = 100, message = "每页条目数不能超过100")
    private int size;
}