package top.lzhseu.myblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.lzhseu.myblog.entity.blogger.BloggerAccount;
import top.lzhseu.myblog.util.constant.BloggerConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lzh
 * @date 2020/5/31 18:27
 */
public class AuthorizedInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /* 获取 session 中的用户 */
        BloggerAccount bloggerAccount = (BloggerAccount) request.getSession().getAttribute(BloggerConstant.BLOGGER_SESSION);
        if (bloggerAccount == null) {
            request.setAttribute("message", "请先登录网站后再访问！");
            request.getRequestDispatcher("/loginPage").forward(request, response);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
