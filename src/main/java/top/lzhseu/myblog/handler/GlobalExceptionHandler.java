package top.lzhseu.myblog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lzhseu.myblog.exception.BaseRuntimeException;
import top.lzhseu.myblog.util.restful.ResultBean;

/**
 * 统一处理异常的类
 * @author lzh
 * @date 2020/5/31 21:47
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 统一处理 BaseRuntimeException 异常，这些异常需要通知API调用者
     * 注意：注解无法继承，所以子类不允许覆盖这些方法
     */
    @ExceptionHandler(BaseRuntimeException.class)
    @ResponseBody
    public Object baseRuntimeExceptionHandler(BaseRuntimeException e) {
        return new ResultBean(e);
    }
}
