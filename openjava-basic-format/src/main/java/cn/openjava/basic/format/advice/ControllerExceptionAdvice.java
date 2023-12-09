package cn.openjava.basic.format.advice;


import cn.openjava.basic.format.enums.ParamFailResult;
import cn.openjava.basic.format.enums.SystemExceptionResult;
import cn.openjava.basic.format.enums.UnsafeFailResult;
import cn.openjava.basic.format.exception.BaseException;
import cn.openjava.basic.format.exception.BusinessException;
import cn.openjava.basic.format.exception.SystemException;
import cn.openjava.basic.format.page.Result;
import cn.openjava.basic.format.result.IResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    /**
     * 日志
     */
    private static Log log = LogFactory.getLog(ControllerExceptionAdvice.class);

    /**
     * 非法的参数异常
     *
     * @param e 异常信息
     * @return 统一包装信息
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public Result handleIllegalArgument(IllegalArgumentException e) {
        return defHandler(SystemExceptionResult.PARAM_PARSE_EXCEPTION, e);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return defHandler(SystemExceptionResult.PARAM_PARSE_EXCEPTION, e);
    }

    @ExceptionHandler({ServletException.class})
    public Result handleServletException(ServletException e) {
        return defHandler(SystemExceptionResult.PARAM_PARSE_EXCEPTION, e);
    }

    /**
     * 请求处理程序不支持特定请求方法时引发异常。
     *
     * @param e 异常信息
     * @return 统一包装信息
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return defHandler(UnsafeFailResult.SECURITY_NOT_ALLOWED_HTTP_METHOD, e);
    }

    @ExceptionHandler({NullPointerException.class})
    public Result handleNullPointer(NullPointerException e) {
        return defHandler(SystemExceptionResult.NULL_POINTER_EXCEPTION, e);
    }

    @ExceptionHandler({BaseException.class, BusinessException.class, SystemException.class})
    public Result handleBaseException(BaseException e) {
        return Result.warning(e.getErrorMsg(), e.getErrorCode());
    }

    @ExceptionHandler({NumberFormatException.class})
    public Result handleException(NumberFormatException e) {
        return defHandler(SystemExceptionResult.NUMBER_CONVERT_EXCEPTION, e);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public Result handleException(MethodArgumentTypeMismatchException e) {
        return defHandler(SystemExceptionResult.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION, e);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Result handleNoHandlerFoundException(NoHandlerFoundException e) {
        return defHandler(SystemExceptionResult.NULL_POINTER_EXCEPTION, e);
    }

    /**
     * 当应用程序在需要对象的情况下尝试使用null时引发。
     *
     * @param e 异常信息
     * @return 统一包装信息
     */
    @ExceptionHandler({BindException.class})
    public Result handleNullPointer(BindException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError objectError : allErrors) {
            stringBuilder.append("[").append(objectError.getDefaultMessage()).append("]");
        }
        return Result.warning(ParamFailResult.QUERY_PARAM_EXCEPTION, stringBuilder.toString());
    }

    /**
     * 增加参数校验失败的异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError error : errors) {
            stringBuilder.append("[").append(error.getDefaultMessage()).append("]");
        }
        return Result.warning(ParamFailResult.QUERY_PARAM_EXCEPTION, stringBuilder.toString());
    }

    /**
     * 增加参数校验失败的异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({WebExchangeBindException.class})
    public Result handleWebExchangeBindException(WebExchangeBindException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError error : errors) {
            stringBuilder.append("[").append(error.getDefaultMessage()).append("]");
        }
        return Result.warning(ParamFailResult.QUERY_PARAM_EXCEPTION, stringBuilder.toString());
    }

    /**
     * 其他类型的异常的
     *
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public Result handleException(Exception e) {
        e.printStackTrace();
        return defHandler(SystemExceptionResult.EXCEPTION, e);
    }

    /**
     * 异常处理
     *
     * @param dialog 结果类型
     * @param e      异常信息
     * @return 统一结果信息
     */
    private Result defHandler(IResult dialog, Exception e) {
        log.error(dialog.message(), e);
        return Result.warning(dialog);
    }
}
