package top.lzhseu.myblog.exception.api.common;

import top.lzhseu.myblog.exception.BaseRuntimeException;
import top.lzhseu.myblog.util.constant.exception.ExceptionCode;

/**
 * @author lzh
 * @date 2020/6/7 14:04
 */
public class OperateFailException extends BaseRuntimeException {

    public static final int code = ExceptionCode.OPERATE_FAIL_EXCEPTION_CODE;

    public OperateFailException() {
        super(code);
    }

    public OperateFailException(String message) {
        super(message, code);
    }

    public OperateFailException(String message, Throwable e) {
        super(message, e, code);
    }
}
