package cn.openjava.basic.format.result;

public interface IResult {
    /**
     * 类型
     *
     * @return 类型
     */
    ResultType type();

    /**
     * 编码
     *
     * @return 编码
     */
    int code();

    /**
     * 消息
     *
     * @return 消息
     */
    String message();

}
