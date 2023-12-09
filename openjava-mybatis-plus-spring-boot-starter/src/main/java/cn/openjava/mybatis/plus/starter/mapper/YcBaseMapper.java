package cn.openjava.mybatis.plus.starter.mapper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.openjava.basic.format.enums.DBFailResult;
import cn.openjava.basic.format.enums.DBSuccessResult;
import cn.openjava.basic.format.enums.SystemExceptionResult;
import cn.openjava.basic.format.exception.BusinessException;
import cn.openjava.basic.format.exception.SystemException;
import cn.openjava.basic.format.page.Form;
import cn.openjava.basic.format.page.Grid;
import cn.openjava.basic.format.page.Result;
import cn.openjava.mybatis.plus.starter.annotation.PrimaryKey;
import cn.openjava.mybatis.plus.starter.annotation.QueryCondition;
import cn.openjava.mybatis.plus.starter.utils.ConvertUtil;
import cn.openjava.mybatis.plus.starter.utils.ObjUtil;
import cn.openjava.mybatis.plus.starter.vo.PageVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface YcBaseMapper<T> extends BaseMapper<T> {
    Log log = LogFactory.getLog(YcBaseMapper.class);

    String serialVersionUID = "serialVersionUID";

    /**
     * 分页查询
     *
     * @param current      当前页
     * @param size         每页条目数
     * @param queryWrapper 查询条件
     * @return Grid对象
     */
    default Grid selectPage(long current, long size, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper, Class queryVoClass) {
        if (queryVoClass == null) {
            return ConvertUtil.convertIPage2Grid(selectPage(new Page(current, size), queryWrapper));
        } else {
            return ConvertUtil.convertIPage2Grid(selectPage(new Page(current, size), queryWrapper), queryVoClass);
        }
    }

    default Form selectById(Serializable id, Class queryVoClass) {
        T t = selectById(id);
        if (t == null) {
            throw new BusinessException(DBFailResult.QUERY_FAIL_NO_DATA);
        } else {
            return new Form().beanConvert(t, queryVoClass);
        }
    }

    /**
     * 分页查询
     *
     * @param conditionVo 继承了PageVo的对象
     * @return 分页结果
     */
    default <P extends PageVo> Grid<T> selectPage(P conditionVo) {
        return selectPage(conditionVo.getCurrent(), conditionVo.getSize(), conditionVo2Wrapper(conditionVo, null), null);
    }

    /**
     * 分页查询
     *
     * @param conditionVo  继承了PageVo的对象
     * @param queryVoClass 查询VO
     * @param <P>
     * @return
     */
    default <P extends PageVo> Grid selectPage(P conditionVo, Class queryVoClass) {
        return selectPage(conditionVo.getCurrent(), conditionVo.getSize(), conditionVo2Wrapper(conditionVo, queryVoClass), queryVoClass);
    }

    /**
     * conditionVo转queryWrapper对象
     *
     * @param conditionVo 条件VO
     * @param <P>         任意对象
     * @return 分页结果
     */
    default <P extends PageVo> QueryWrapper conditionVo2Wrapper(P conditionVo, Class queryVoClass) {
        QueryWrapper<P> queryWrapper = new QueryWrapper<>();
        try {
            if (queryVoClass != null) {
                Field[] voFields = queryVoClass.getFields();
                if (voFields != null) {
                    List<String> args = new ArrayList();
                    for (Field field : voFields) {
                        //排除序列化参数
                        if (!this.serialVersionUID.equalsIgnoreCase(field.getName())) {
                            args.add(StrUtil.toUnderlineCase(field.getName()));
                        }
                    }
                    queryWrapper = queryWrapper.select(args.toArray(new String[args.size()]));
                }
            }
            Field[] fields = conditionVo.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (serialVersionUID.equals(field.getName())) {
                    continue;
                }
                QueryCondition annotation = field.getDeclaredAnnotation(QueryCondition.class);
                Method method = conditionVo.getClass().getDeclaredMethod("get" + StrUtil.upperFirst(field.getName()));
                Object value = method.invoke(conditionVo);
                if (StrUtil.isEmptyIfStr(value)) {
                    continue;
                }
                String columnName = StrUtil.toUnderlineCase(field.getName());
                if (annotation == null) {
                    queryWrapper.eq(columnName, value);
                } else {
                    Method wrapperMethod = queryWrapper.getClass().getMethod(StrUtil.toCamelCase(annotation.value().toString().toLowerCase()), Object.class, Object.class);
                    wrapperMethod.invoke(queryWrapper, columnName, value);
                }
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
            throw new SystemException(SystemExceptionResult.REFLECT_HANDLER_EXCEPTION);
        }
        return queryWrapper;
    }

    /**
     * conditionVo转queryWrapper对象
     *
     * @param deleteVo 条件VO
     * @param <P>      任意对象
     * @return 分页结果
     */
    default <P> QueryWrapper vo2Wrapper(P deleteVo) {
        QueryWrapper<P> queryWrapper = new QueryWrapper<>();
        try {
            Field[] fields = deleteVo.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (this.serialVersionUID.equals(field.getName())) {
                    continue;
                }
                Method method = deleteVo.getClass().getDeclaredMethod("get" + StrUtil.upperFirst(field.getName()));
                Object value = method.invoke(deleteVo);
                if (StrUtil.isEmptyIfStr(value)) {
                    continue;
                }
                String columnName = StrUtil.toUnderlineCase(field.getName());
                queryWrapper.eq(columnName, value);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
            throw new SystemException(SystemExceptionResult.REFLECT_HANDLER_EXCEPTION);
        }
        return queryWrapper;
    }

    /**
     * 根据ID进行删除
     *
     * @param id
     * @return
     */
    default Result<String> deleteByPk(Serializable id) {
        try {
            int i = deleteById(id);
            return i > 0 ? Result.message(DBSuccessResult.DELETE_SUCCESS) : Result.message(DBFailResult.DELETE_FAIL_NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException(DBFailResult.DELETE_FAIL_EXCEPTION);
        }
    }

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    default Result<String> deleteByPks(Collection<?> idList) {
        try {
            if (idList.size() == 0) {
                return Result.message(DBFailResult.DELETE_FAIL_NO_ID);
            }
            int i = deleteBatchIds(idList);
            if (i == 0) {
                return Result.message(DBFailResult.DELETE_FAIL_NOT_FOUND);
            } else if (i == idList.size()) {
                return Result.message(DBSuccessResult.DELETE_SUCCESS);
            } else {
                return Result.message(DBFailResult.DELETE_SUCCESS_AND_FAIL, idList.size(), i, idList.size() - i);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException(DBFailResult.DELETE_FAIL_EXCEPTION);
        }
    }

    /**
     * 删除
     *
     * @param deleteVo 删除VO
     * @param <P>
     * @return
     */
    default <P> Result<String> deleteByVo(P deleteVo) {
        try {
            QueryWrapper wrapper = vo2Wrapper(deleteVo);
            if (ObjUtil.isAllFieldsNull(deleteVo)) {
                return Result.message(DBFailResult.DELETE_FAIL_NO_CONDITION);
            }
            int i = delete(wrapper);
            return i > 0 ? Result.message(DBSuccessResult.DELETE_SUCCESS) : Result.message(DBFailResult.DELETE_FAIL_NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException(DBFailResult.DELETE_FAIL_EXCEPTION);
        }
    }

    /**
     * 新增
     *
     * @param addDto 新增DTO对象
     * @param clazz  实体类对象
     * @param <P>
     * @return 是或者否
     */
    default <P> Result<String> addOne(P addDto, Class clazz) {
        try {
            int i = insert((T) BeanUtil.toBean(addDto, clazz));
            return i > 0 ? Result.message(DBSuccessResult.INSERT_SUCCESS) : Result.message(DBFailResult.INSERT_FAIL);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException(DBFailResult.INSERT_FAIL_EXCEPTION);
        }
    }

    /**
     * 新增
     *
     * @param addDto 新增DTO对象
     * @param clazz  实体类对象
     * @param <P>
     * @return 是或者否
     */
    default <P> int add(P addDto, Class clazz) {
        try {
            return insert((T) BeanUtil.toBean(addDto, clazz));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException(DBFailResult.INSERT_FAIL_EXCEPTION);
        }
    }

    /**
     * 修改
     *
     * @param updateVo 修改Vo
     * @param clazz
     * @param <P>
     * @return
     */
    default <P> Result<String> update(P updateVo, Class clazz) {
        try {
            int i = updateById((T) BeanUtil.toBean(updateVo, clazz));
            return i > 0 ? Result.message(DBSuccessResult.UPDATE_SUCCESS) : Result.message(DBFailResult.UPDATE_FAIL);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SystemException(DBFailResult.UPDATE_FAIL_EXCEPTION);
        }
    }

    /**
     * 获取主键值
     *
     * @param vo
     * @param <P>
     * @return
     */
    default <P> Serializable getPkFromVo(P vo) {
        try {
            Field[] fields = vo.getClass().getDeclaredFields();
            for (Field field : fields) {
                PrimaryKey annotation = field.getDeclaredAnnotation(PrimaryKey.class);
                if (annotation != null) {
                    Method method = vo.getClass().getDeclaredMethod("get" + StrUtil.upperFirst(field.getName()));
                    return (Serializable) method.invoke(vo);
                }
            }
            return null;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
