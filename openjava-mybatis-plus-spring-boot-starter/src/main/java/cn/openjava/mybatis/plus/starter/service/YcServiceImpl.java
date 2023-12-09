package cn.openjava.mybatis.plus.starter.service;

import cn.openjava.basic.format.page.Grid;
import cn.openjava.mybatis.plus.starter.mapper.YcBaseMapper;
import cn.openjava.mybatis.plus.starter.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

public class YcServiceImpl<M extends YcBaseMapper<T>, T> extends ServiceImpl<M, T> implements IYcService<T> {
    @Resource
    private M ycBaseMapper;

    @Override
    public YcBaseMapper<T> getYcBaseMapper() {
        return ycBaseMapper;
    }

    @Override
    public <P extends PageVo> Grid<T> selectPage(P conditionVo) {
        return ycBaseMapper.selectPage(conditionVo);
    }

    @Override
    public <P extends PageVo> Grid selectPage(P conditionVo, Class queryVoClass) {
        return ycBaseMapper.selectPage(conditionVo, queryVoClass);
    }

}
