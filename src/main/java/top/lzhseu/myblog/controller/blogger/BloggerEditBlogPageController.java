package top.lzhseu.myblog.controller.blogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.lzhseu.myblog.dto.BlogEditDto;
import top.lzhseu.myblog.entity.blog.BlogCategory;
import top.lzhseu.myblog.entity.blogger.BloggerAccount;
import top.lzhseu.myblog.service.blog.BlogCategoryService;
import top.lzhseu.myblog.service.blog.BlogService;
import top.lzhseu.myblog.service.blogger.BloggerProfileService;
import top.lzhseu.myblog.util.constant.BloggerConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lzh
 * @date 2020/5/29 14:23
 */
@Controller
@RequestMapping("/manage/blogger")
public class BloggerEditBlogPageController {


    @Autowired
    private BlogCategoryService categoryService;

    @Autowired
    private BloggerProfileService profileService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/edit")
    public ModelAndView editPage(@RequestParam(value = "blogId", required = false) Integer blogId,
                                 HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        HttpSession session = request.getSession();
        BloggerAccount bloggerAccount = (BloggerAccount) session.getAttribute(BloggerConstant.BLOGGER_SESSION);
        String loginSignal = (String) session.getAttribute(BloggerConstant.LOGIN_SIGNAL);
        if (bloggerAccount == null || !BloggerConstant.LOGIN_SUCCESS.equals(loginSignal)) {
            mv.setViewName("blogger/loginPage");
            return mv;
        }

        mv.setViewName("blogger/edit_blog");

        if (blogId != null) {
            BlogEditDto blogEditDto = blogService.getBlogEditDtoByBlogId(blogId);
            mv.addObject("editBlogDto", blogEditDto);
        }

        List<BlogCategory> bloggerCategory = categoryService.getBloggerCategory(bloggerAccount.getId());

        mv.addObject("bloggerCategory", bloggerCategory);
        mv.addObject("bloggerName", profileService.getBloggerProfile().getNickname());


        return mv;
    }



}