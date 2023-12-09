package cn.openjava.mybatis.plus.starter.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.openjava.basic.format.page.Grid;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {
    /**
     * 将mybatis-plus的IPage转为Grid对象
     *
     * @param iPage mybatis-plus的IPage对象
     * @return Grid对象
     */
    public static Grid convertIPage2Grid(IPage iPage) {
        if (iPage == null) {
            return new Grid();
        }
        Grid grid = new Grid();
        return grid.setCurrent(iPage.getCurrent()).setSize(iPage.getSize()).setData(iPage.getRecords()).setTotal(iPage.getTotal());
    }

    /**
     * 将mybatis-plus的IPage转为Grid对象
     *
     * @param iPage mybatis-plus的IPage对象
     * @param clazz 转换后的类
     * @return Grid对象
     */

    public static Grid convertIPage2Grid(IPage iPage, Class clazz) {
        if (iPage == null) {
            return new Grid();
        }
        Grid grid = new Grid();
        List records = iPage.getRecords();
        List data = new ArrayList();
        for (Object t : records) {
            data.add(BeanUtil.toBean(t, clazz));
        }
        return grid.setCurrent(iPage.getCurrent()).setSize(iPage.getSize()).setData(data).setTotal(iPage.getTotal());
    }

    /**
     * 将page-helper的PageInfo对象转为Grid对象
     *
     * @param pageInfo page-helper的PageInfo对象
     * @return Grid对象
     */
    public static Grid convertPageInfo2Grid(PageInfo pageInfo) {
        if (pageInfo == null) {
            return new Grid();
        }
        Grid grid = new Grid();
        return grid.setCurrent(pageInfo.getPageNum()).setSize(pageInfo.getSize()).setData(pageInfo.getList()).setTotal(pageInfo.getTotal());
    }
}
