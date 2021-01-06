package top.lzhseu.myblog.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.lzhseu.myblog.dto.BlogContentDto;
import top.lzhseu.myblog.entity.blog.Blog;
import top.lzhseu.myblog.entity.blog.BlogCategory;
import top.lzhseu.myblog.manager.SideBarDataManager;
import top.lzhseu.myblog.service.blog.BlogCategoryService;
import top.lzhseu.myblog.service.blog.BlogService;
import top.lzhseu.myblog.util.constant.BlogConstant;

import java.util.Map;

/**
 * @author lzh
 * @date 2020/6/3 8:43
 */
@Controller
@RequestMapping("/blog")
public class BlogContentPageController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/content/{blogId}")
    public ModelAndView blogContentPage(@PathVariable Integer blogId) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("blog/blog-content");

        Integer state = blogService.getBlogState(blogId);

        if (state == BlogConstant.PUBLIC_STATUS || state == BlogConstant.INTERNAL_STATUS) {
            BlogContentDto blogContentDto = blogService.getBlogContentDtoByBlogId(blogId);
            mv.addObject("blogContentDto", blogContentDto);
        } else {
            mv.addObject("blogContentDto", null);
        }

        return mv;
    }
}
