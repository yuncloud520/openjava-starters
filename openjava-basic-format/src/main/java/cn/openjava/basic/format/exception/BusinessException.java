package cn.openjava.basic.format.exception;


import cn.openjava.basic.format.result.IResult;

/**
 * 业务逻辑异常类
 */
public class BusinessException extends BaseException {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -598296931854247784L;

    /**
     * 构造方法
     *
     * @param code 编码
     */
    public BusinessException(IResult code) {
        super(code);
    }


    /**
     * 构造方法
     *
     * @param code 编码
     * @param msg  消息
     */
    public BusinessException(final int code, final String msg) {
        super(code, msg);
    }

    /**
     * 构造方法
     *
     * @param code    编码
     * @param fillStr 文本
     */
    public BusinessException(IResult code, Object... fillStr) {
        super(code, fillStr);
    }

}
