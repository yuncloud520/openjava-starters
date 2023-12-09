package cn.openjava.basic.format.page;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表单对象
 *
 * @param <T> 对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel("表单")
public class Form<T> {

    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private T data;

    /**
     * 数据字典
     */
    @ApiModelProperty("字典")
    private Map<String, List<Dict>> dicts = new HashMap();

    /**
     * 构造函数
     */
    public Form() {

    }

    /**
     * 构造函数
     *
     * @param data 数据
     */
    public Form(T data) {
        this.data = data;
    }

    /**
     * 构造函数
     *
     * @param data  数据
     * @param dicts 数据字典
     */
    public Form(T data, Map<String, List<Dict>> dicts) {
        this.data = data;
        this.dicts = dicts;
    }

    public Form<T> beanConvert(Object object, Class clazz) {
        return this.setData((T) BeanUtil.toBean(object, clazz));
    }

}
