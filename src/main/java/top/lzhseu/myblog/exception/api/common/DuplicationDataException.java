package top.lzhseu.myblog.exception.api.common;

import top.lzhseu.myblog.exception.BaseRuntimeException;
import top.lzhseu.myblog.util.constant.exception.ExceptionCode;

/**
 * @author lzh
 * @date 2020/4/23 11:17
 */
public class DuplicationDataException extends BaseRuntimeException {

    public static final int CODE = ExceptionCode.DUPLICATION_DATA_EXCEPTION_CODE;

    public DuplicationDataException() {
        super(CODE);
    }

    public DuplicationDataException(String message) {
        super(message, CODE);
    }

    public DuplicationDataException(String message, Throwable cause) {
        super(message, cause, CODE);
    }

}
