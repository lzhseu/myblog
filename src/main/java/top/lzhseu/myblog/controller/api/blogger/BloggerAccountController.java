package top.lzhseu.myblog.controller.api.blogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lzhseu.myblog.entity.blogger.BloggerAccount;
import top.lzhseu.myblog.service.blogger.BloggerAccountService;
import top.lzhseu.myblog.util.constant.BloggerConstant;
import top.lzhseu.myblog.util.restful.ResultBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzh
 * @date 2020/5/31 19:16
 */
@Controller
public class BloggerAccountController {

    @Autowired
    private BloggerAccountService accountService;

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "blogger/loginPage";
    }


    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletRequest request) {

        BloggerAccount account = accountService.login(email, password);
        if (account != null) {
            request.getSession().setAttribute(BloggerConstant.BLOGGER_SESSION, account);
            request.getSession().setAttribute(BloggerConstant.LOGIN_SIGNAL, BloggerConstant.LOGIN_SUCCESS);
            return new ResultBean("login ok");
        } else {
            return new ResultBean(ResultBean.FAIL, "error");
        }

    }
}
