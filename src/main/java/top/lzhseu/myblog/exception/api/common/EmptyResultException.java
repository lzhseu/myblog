package top.lzhseu.myblog.exception.api.common;

import top.lzhseu.myblog.exception.BaseRuntimeException;
import top.lzhseu.myblog.util.constant.exception.ExceptionCode;

/**
 * @author lzh
 * @date 2020/4/28 11:13
 */
public class EmptyResultException extends BaseRuntimeException {

    public static final int CODE = ExceptionCode.EMPTY_RESULT_EXCEPTION_CODE;

    public EmptyResultException() {
        super(CODE);
    }

    public EmptyResultException(String message) {
        super(message, CODE);
    }

    public EmptyResultException(String message, Throwable cause) {
        super(message, cause, CODE);
    }

    public EmptyResultException(Throwable cause) {
        super(cause, CODE);
    }
}
