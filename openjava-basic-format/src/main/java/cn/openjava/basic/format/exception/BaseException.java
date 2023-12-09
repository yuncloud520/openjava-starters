package cn.openjava.basic.format.exception;

import cn.openjava.basic.format.result.IResult;

import java.io.Serializable;
import java.text.MessageFormat;


/**
 * 异常的基类
 */
public class BaseException extends RuntimeException implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 4684909395782753496L;

    /**
     * 错误码
     */
    private final int errorCode;

    /**
     * 错误描述
     */
    private final String errorMsg;

    /**
     * 详情
     */
    private final String msgDetail;

    /**
     * 构造方法
     *
     * @param iResult 编码
     * @param fillStr 文本
     */
    public BaseException(IResult iResult, Object... fillStr) {
        this.errorCode = iResult.code();
        this.errorMsg = MessageFormat.format(iResult.message(), fillStr);
        this.msgDetail = "";
    }

    /**
     * 构造方法
     *
     * @param code 编码
     * @param msg  信息
     */
    public BaseException(final int code, final String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
        this.msgDetail = "";
    }

    /**
     * 构造方法
     *
     * @param e         异常
     * @param errorCode 编码
     * @param errorMsg  信息
     * @param msgDetail 详情
     */
    public BaseException(Throwable e, int errorCode, String errorMsg, String msgDetail) {
        super(e);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.msgDetail = msgDetail;
    }

    /**
     * 功能描述：重写基类toString()方法
     */
    @Override
    public String toString() {
        StringBuilder sbf = new StringBuilder();
        sbf.append("错误码:[ ");
        sbf.append(this.getErrorCode());
        sbf.append(" ] ,错误信息:[ ");
        sbf.append(this.getErrorMsg());
        sbf.append(" ] ," + "错误详情:[ ");
        sbf.append(this.getMsgDetail());
        sbf.append("]");
        return sbf.toString();
    }

    /**
     * 获取错误编码
     *
     * @return 编码
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 获取错误详情
     *
     * @return 错误详情
     */
    public String getMsgDetail() {
        return msgDetail;
    }

}
