package cn.openjava.mybatis.plus.starter.service;

import cn.openjava.basic.format.page.Grid;
import cn.openjava.mybatis.plus.starter.mapper.YcBaseMapper;
import cn.openjava.mybatis.plus.starter.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IYcService<T> extends IService<T> {
    /**
     * 获取YcBaseMapper对象
     *
     * @return
     */
    YcBaseMapper<T> getYcBaseMapper();

    <P extends PageVo> Grid<T> selectPage(P conditionVo);


    <P extends PageVo> Grid selectPage(P conditionVo, Class queryVoClass);
}
