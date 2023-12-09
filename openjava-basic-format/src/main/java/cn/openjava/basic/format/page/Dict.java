package cn.openjava.basic.format.page;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典对象
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel("字典")
//@Slf4j
public class Dict implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = 3276192621898953484L;
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
     * 实际值
     */
    @ApiModelProperty("实际值")
    private String value;

    /**
     * 子项
     */
    @ApiModelProperty("子项")
    private List<Dict> children = new ArrayList<>();

    /**
     * 其他属性
     */
    @ApiModelProperty("其他属性")
    private Object properties;

    public Dict(String value, String text) {
        this.value = value;
        this.text = text;
    }


    /**
     * 转换枚举类为字典列表
     *
     * @param clazz 枚举类
     * @return 数据字典列表
     */
    public static <S extends Enum> List<Dict> convertEnumToDict(Class<S> clazz) {
        List<Dict> items = new ArrayList<>();
        try {
            Object[] enumConstants = clazz.getEnumConstants();
            for (Object constant : enumConstants) {
                Object text;
                try {
                    text = clazz.getDeclaredMethod("getText").invoke(constant);
                } catch (NoSuchMethodException e) {
                    text = constant;
                }
                Object value;
                try {
                    value = clazz.getDeclaredMethod("getValue").invoke(constant);
                } catch (NoSuchMethodException e) {
                    value = constant;
                }
                items.add(new Dict(StrUtil.toString(value), StrUtil.toString(text)));
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
//            log.error(e.getMessage(), e);
        } finally {
            return items;
        }
    }

    /**
     * 转换枚举类为字典列表
     *
     * @param key   字典值
     * @param clazz 枚举类
     * @return 字典
     */
    public static Map<String, List<Dict>> convertEnumToDicts(String key, Class clazz) {
        Map<String, List<Dict>> result = new HashMap<>();
        if (StrUtil.isBlank(key) || clazz == null) {
            return result;
        }
        result.put(key, convertEnumToDict(clazz));
        return result;
    }

    /**
     * 转换枚举类为字典列表
     *
     * @param clazz 枚举类
     * @return 字典
     */
    public static Map<String, List<Dict>> convertEnumToDicts(Class clazz) {
        Map<String, List<Dict>> result = new HashMap<>();
        if (clazz == null) {
            return result;
        }
        result.put(StrUtil.lowerFirst(clazz.getSimpleName()), convertEnumToDict(clazz));
        return result;
    }

    /**
     * 转换枚举类为字典列表
     *
     * @param keys    key
     * @param classes 枚举类
     * @return 字典
     */
    public static Map<String, List<Dict>> convertEnumsToDicts(String[] keys, Class[] classes) {
        Map<String, List<Dict>> result = new HashMap<>();
        if (keys == null || classes == null || keys.length != classes.length) {
            return result;
        }
        for (int i = 0; i < keys.length; i++) {
            result.put(keys[i], convertEnumToDict(classes[i]));
        }
        return result;
    }

    /**
     * 转换枚举类为字典列表
     *
     * @param classes 枚举类
     * @return 字典
     */
    public static Map<String, List<Dict>> convertEnumsToDicts(Class[] classes) {
        Map<String, List<Dict>> result = new HashMap<>();
        for (Class clazz : classes) {
            result.put(StrUtil.lowerFirst(clazz.getSimpleName()), convertEnumToDict(clazz));
        }
        return result;
    }
}
