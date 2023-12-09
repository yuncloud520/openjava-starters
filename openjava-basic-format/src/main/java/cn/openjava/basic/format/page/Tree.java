package cn.openjava.basic.format.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 前端树对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Tree {

    /**
     * 唯一标识
     */
    @ApiModelProperty("唯一标识")
    private String id;

    /**
     * 显示值
     */
    @ApiModelProperty("显示值")
    private String text;

    /**
     * 是否末级节点
     */
    @ApiModelProperty("是否末级节点")
    private boolean leaf;

    /**
     * 子节点
     */
    @ApiModelProperty("子节点")
    private List<Tree> children = new ArrayList();

    /**
     * 是否展开
     */
    @ApiModelProperty("是否展开")
    private boolean expanded;

}
