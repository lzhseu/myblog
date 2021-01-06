package top.lzhseu.myblog.controller;

import org.springframework.web.servlet.support.RequestContext;
import top.lzhseu.myblog.entity.blogger.BloggerAccount;
import top.lzhseu.myblog.exception.api.common.OperateFailException;
import top.lzhseu.myblog.exception.api.parameter.ParameterIllegalException;
import top.lzhseu.myblog.exception.api.blogger.BloggerNotLoggedInException;
import top.lzhseu.myblog.util.constant.BloggerConstant;
import top.lzhseu.myblog.util.constant.exception.ExceptionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lzh
 * @date 2020/5/31 21:29
 */
public class BaseCheckController {


    /**
     * 检查用户是否登录
     * @param request 请求
     * @return 用户 id
     */
    public int handleBloggerLogin(HttpServletRequest request) {

        HttpSession session = request.getSession();
        BloggerAccount bloggerAccount = (BloggerAccount) session.getAttribute(BloggerConstant.BLOGGER_SESSION);
        String loginSignal = (String) session.getAttribute(BloggerConstant.LOGIN_SIGNAL);

        if (bloggerAccount == null || !BloggerConstant.LOGIN_SUCCESS.equals(loginSignal)) {
            throw new BloggerNotLoggedInException(ExceptionMessage.BLOGGER_NOT_LOGGED_IN_EXCEPTION_MSG);
        }

        return bloggerAccount.getId();
    }

    public void handleIllegalParameter() {
        throw new ParameterIllegalException(ExceptionMessage.PARAMETER_ILLEGAL_EXCEPTION_MSG);
    }

    public void handleParameterCheck(Object... params) {
        for (Object param : params) {
            if (param == null) {
                throw new ParameterIllegalException(ExceptionMessage.PARAMETER_ILLEGAL_EXCEPTION_MSG);
            }
        }
    }

    /**
     * 处理操作失败的情况
     */
    protected void handlerOperateFail() {
        throw new OperateFailException(ExceptionMessage.OPERATE_FAIL_EXCEPTIONMSG);
    }

    /**
     * 处理操作失败的情况
     */
    protected void handlerOperateFail(Throwable e) {
        throw new OperateFailException(ExceptionMessage.OPERATE_FAIL_EXCEPTIONMSG, e);
    }


}
