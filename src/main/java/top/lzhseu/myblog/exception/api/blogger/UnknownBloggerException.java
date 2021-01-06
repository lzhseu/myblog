package top.lzhseu.myblog.exception.api.blogger;

import top.lzhseu.myblog.exception.BaseRuntimeException;
import top.lzhseu.myblog.util.constant.exception.ExceptionCode;

/**
 * @author lzh
 * @date 2020/6/1 14:55
 */
public class UnknownBloggerException extends BaseRuntimeException {

    public static final int CODE = ExceptionCode.UNKNOWN_BLOGGER_EXCEPTION_CODE;

    public UnknownBloggerException(String message) {
        super(message,CODE);
    }

    public UnknownBloggerException() {
        super(CODE);
    }
}
