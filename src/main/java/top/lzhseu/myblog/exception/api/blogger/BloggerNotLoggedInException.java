package top.lzhseu.myblog.exception.api.blogger;

import top.lzhseu.myblog.exception.BaseRuntimeException;
import top.lzhseu.myblog.util.constant.exception.ExceptionCode;

/**
 * @author lzh
 * @date 2020/6/1 12:42
 */
public class BloggerNotLoggedInException extends BaseRuntimeException {

        public static final int CODE = ExceptionCode.BLOGGER_NOT_LOGGED_IN_EXCEPTION_CODE;

        public BloggerNotLoggedInException() {
            super(CODE);
        }

        public BloggerNotLoggedInException(String message) {
            super(message, CODE);
        }

        public BloggerNotLoggedInException(String message, Throwable cause) {
            super(message, cause, CODE);
        }

        public BloggerNotLoggedInException(Throwable cause) {
            super(cause, CODE);
        }

}
