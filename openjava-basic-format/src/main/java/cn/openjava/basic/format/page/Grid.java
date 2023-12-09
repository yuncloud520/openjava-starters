package cn.openjava.basic.format.page;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表格对象
 *
 * @param <T> 对象
 */
@Getter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel("表格")
public class Grid<T> {

    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private long current;

    /**
     * 每页的条目数
     */
    @ApiModelProperty("每页大小")
    private long size;

    /**
     * 总条目数
     */
    @ApiModelProperty("总条目数")
    private long total;

    /**
     * 数据
     */
    @ApiModelProperty("数据")
    private List<T> data;

    /**
     * 数据字典
     */
    @ApiModelProperty("字典")
    private Map<String, List<Dict>> dicts = new HashMap();

    /**
     * 总页码数
     */
    @ApiModelProperty("总页码数")
    private long pages;

    /**
     * 构造函数
     */
    public Grid() {

    }

    /**
     * 构造函数
     *
     * @param data 数据
     */
    public Grid(List<T> data) {
        this.data = data;
        this.total = data.size();
        this.pages = 1;
    }

    /**
     * 构造函数
     *
     * @param data  数据
     * @param dicts 数据字典
     */
    public Grid(List<T> data, Map<String, List<Dict>> dicts) {
        this.data = data;
        this.dicts = dicts;
    }

    /**
     * 构造函数
     *
     * @param data  数据
     * @param dicts 数据字典
     */
    public Grid(List<T> data, Map<String, List<Dict>> dicts, long current, long size) {
        this.data = data;
        this.dicts = dicts;
        this.current = current;
        this.size = size;
    }

    /**
     * 构造函数
     *
     * @param data  数据
     * @param dicts 数据字典
     */
    public Grid(List<T> data, Map<String, List<Dict>> dicts, long current, long size, long total) {
        this.data = data;
        this.dicts = dicts;
        this.current = current;
        this.size = size;
        this.setTotal(total);
    }

    /**
     * 构造函数
     *
     * @param data    数据
     * @param dicts   数据字典
     * @param isPaged 是否分页
     */
    public Grid(List<T> data, Map<String, List<Dict>> dicts, boolean isPaged) {
        this.data = data;
        this.dicts = dicts;
        if (!isPaged) {
            this.total = data.size();
        }
    }

    /**
     * setPageSize
     *
     * @param size 每页条目数
     */
    public Grid setSize(long size) {
        this.size = size;
        resetPages();
        return this;
    }

    /**
     * setTotalSize
     *
     * @param total 总数
     */
    public Grid setTotal(long total) {
        this.total = total;
        resetPages();
        return this;
    }

    /**
     * 重置totalPages
     */
    private void resetPages() {
        if (size > 0) {
            long t;
            if (total % size == 0) {
                t = total / size;
            } else {
                t = total / size + 1;
            }
            setPages(t);
        }
    }

    public Grid setCurrent(long current) {
        this.current = current;
        return this;
    }

    public Grid setData(List<T> data) {
        this.data = data;
        return this;
    }

    public Grid setDicts(Map<String, List<Dict>> dicts) {
        this.dicts = dicts;
        return this;
    }

    public Grid setPages(long pages) {
        this.pages = pages;
        return this;
    }

    @Deprecated
    public Grid beanConvert(Class to) {
        List data = new ArrayList();
        for (T t : this.getData()) {
            data.add(BeanUtil.toBean(t, to));
        }
        this.setData(data);
        return this;
    }
}
