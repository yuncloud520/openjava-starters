package cn.openjava.mybatis.plus.starter.enums;

public enum ConditionType {
    /**
     * 等于
     */
    EQ,
    /**
     * 不等于
     */
    NE,
    /**
     * 左右模糊匹配
     */
    LIKE,
    /**
     * 左模糊匹配
     */
    LIKE_LEFT,
    /**
     * 右模糊
     */
    LIKE_RIGHT,
    /**
     * 小于等于
     */
    LE,
    /**
     * 大于等于
     */
    GE,
    /**
     * 小于
     */
    LT,
    /**
     * 大于
     */
    GT;
}
