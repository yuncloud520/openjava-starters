package cn.openjava.basic.format.exception;


import cn.openjava.basic.format.result.IFailResult;

/**
 * 系统异常类
 */
public class SystemException extends BaseException {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -598296931854247784L;

    /**
     * 构造方法
     *
     * @param result 编码
     */
    public SystemException(IFailResult result) {
        super(result);
    }


    /**
     * 构造方法
     *
     * @param code 编码
     * @param msg  消息
     */
    public SystemException(final int code, final String msg) {
        super(code, msg);
    }

    /**
     * 构造方法
     *
     * @param msg
     */
    public SystemException(final String msg) {
        super(500, msg);
    }

    /**
     * 构造方法
     *
     * @param result  编码
     * @param fillStr 文本
     */
    public SystemException(IFailResult result, Object... fillStr) {
        super(result, fillStr);
    }

}
