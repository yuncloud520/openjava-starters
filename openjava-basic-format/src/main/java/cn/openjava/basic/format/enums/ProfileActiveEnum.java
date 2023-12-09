package cn.openjava.basic.format.enums;

import cn.openjava.basic.format.utils.IDictEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProfileActiveEnum implements IDictEnum {
    /**
     * 开发环境
     */
    DEV("dev", "开发环境"),
    /**
     * 测试环境
     */
    TEST("test", "测试环境"),
    /**
     * 预生产环境
     */
    UAT("uat", "预生产环境"),
    /**
     * 生产环境
     */
    PROD("prod", "生产环境");

    /**
     * 编码
     */
    private String code;

    /**
     * 文本
     */
    private String text;

    /**
     * 获取文本
     *
     * @return 文本值
     */
    @Override
    public String getText() {
        return this.text;
    }
}
