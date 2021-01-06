package top.lzhseu.myblog.util.restful;

import lombok.Getter;
import lombok.Setter;
import top.lzhseu.myblog.exception.BaseRuntimeException;

import java.io.Serializable;

/**
 * restful 风格 API 返回结果固定结构
 * @author lzh
 * @date 2020/5/26 10:20
 */
@Getter
@Setter
public class ResultBean implements Serializable {

    /**
     * 结果状态为成功
     */
    public static final int SUCCESS = 0;

    /**
     * 结果状态为失败
     */
    public static final int FAIL = -1;

    public int code = SUCCESS;
    private String msg = "success";
    private Object data;

    /**
     * 返回成功的构造函数
     * @param data
     */
    public ResultBean(Object data) {
        this.data = data;
    }

    /**
     * 返回错误（获取数据错误）的构造函数
     * @param e
     */
    public ResultBean(BaseRuntimeException e) {
        this.code = e.getCode();
        this.msg = e.getMessage();
    }

    /**
     * 自定义信息和代码
     * @param code
     * @param msg
     */
    public ResultBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
