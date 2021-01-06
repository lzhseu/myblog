package top.lzhseu.myblog.exception.api.parameter;

import top.lzhseu.myblog.exception.BaseRuntimeException;
import top.lzhseu.myblog.util.constant.exception.ExceptionCode;

/**
 * @author lzh
 * @date 2020/4/28 10:14
 */
public class ParameterFormatIllegalException extends BaseRuntimeException {

    public static final int CODE = ExceptionCode.PARAMETER_FORMAT_ILLEGAL_EXCEPTION_CODE;

    public ParameterFormatIllegalException() {
        super(CODE);
    }

    public ParameterFormatIllegalException(String message) {
        super(message, CODE);
    }

    public ParameterFormatIllegalException(String message, Throwable cause) {
        super(message, cause, CODE);
    }

    public ParameterFormatIllegalException(Throwable cause) {
        super(cause, CODE);
    }
}
