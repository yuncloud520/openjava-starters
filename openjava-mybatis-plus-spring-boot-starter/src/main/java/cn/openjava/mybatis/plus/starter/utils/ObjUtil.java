package cn.openjava.mybatis.plus.starter.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class ObjUtil {
    /**
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static boolean isAllFieldsNull(Object obj) {
        try {
            Class<?> aClass = obj.getClass();
            Field[] fs = aClass.getDeclaredFields();
            boolean flag = true;
            for (Field f : fs) {
                f.setAccessible(true);
                Object o = f.get(obj);
                if (o != null) {
                    flag = false;
                }
            }
            return flag;
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}
