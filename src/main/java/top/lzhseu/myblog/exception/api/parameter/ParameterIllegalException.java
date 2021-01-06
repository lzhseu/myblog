package top.lzhseu.myblog.exception.api.parameter;

import top.lzhseu.myblog.exception.BaseRuntimeException;
import top.lzhseu.myblog.util.constant.exception.ExceptionCode;

/**
 * @author lzh
 * @date 2020/4/20 10:33
 */
public class ParameterIllegalException extends BaseRuntimeException {

    public static final int CODE = ExceptionCode.PARAMETER_ILLEGAL_EXCEPTION_CODE;

    public ParameterIllegalException() {
        super(CODE);
    }

    public ParameterIllegalException(String message) {
        super(message, CODE);
    }

    public ParameterIllegalException(String message, Throwable cause) {
        super(message, cause, CODE);
    }

}
