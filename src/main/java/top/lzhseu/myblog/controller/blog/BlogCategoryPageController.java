package top.lzhseu.myblog.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.lzhseu.myblog.controller.BaseDataController;
import top.lzhseu.myblog.dto.BlogListItemDto;
import top.lzhseu.myblog.entity.blog.BlogCategory;
import top.lzhseu.myblog.manager.SideBarDataManager;
import top.lzhseu.myblog.manager.properties.BlogProperties;
import top.lzhseu.myblog.service.blog.BlogCategoryService;
import top.lzhseu.myblog.service.blog.BlogService;
import top.lzhseu.myblog.util.constant.BlogConstant;
import top.lzhseu.myblog.util.constant.CommonConstant;
import top.lzhseu.myblog.util.restful.ResultBean;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/5/27 19:33
 */
@Controller
@RequestMapping("/blog/category")
public class BlogCategoryPageController extends BaseDataController {

    @Autowired
    private SideBarDataManager sideBarDataManager;

    @Autowired
    private BlogCategoryService categoryService;

    @Autowired
    private BlogProperties blogProperties;


    @RequestMapping("/page={pageIndex}")
    public ModelAndView getCategoryPageData(@RequestParam("title") String title,
                                            @PathVariable("pageIndex") Integer pageIndex,
                                            HttpServletRequest request) throws UnsupportedEncodingException {

        String decodeTitle = urlDecode(title);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("blog/blog-category");

        // 获取左边栏数据
        Map<String, Object> sideBarData = sideBarDataManager.getSideBarData();
        mv.addObject("bloggerProfile", sideBarData.get("bloggerProfile"));
        mv.addObject("blogCategory", sideBarData.get("blogCategory"));

        // 获取当前目录数据
        BlogCategory currentCategory = categoryService.getBlogCategoryByTitle(decodeTitle);
        if (currentCategory == null) {
            currentCategory = new BlogCategory();
            currentCategory.setTitle("错误类别");
            mv.addObject("currentCategory", currentCategory);
            request.setAttribute("result", new ResultBean(ResultBean.FAIL, "category null"));
            return mv;
        }

        request.setAttribute("result", new ResultBean("success"));

        // 计算博文总数
        int blogCount = blogService.getBlogCountByCategory(currentCategory, BlogConstant.PUBLIC_STATUS);
        int pageSize = blogProperties.getCategoryPageSize();
        int totalPages = calTotalPages(blogCount, pageSize);


        // 获取博文列表数据
        List<BlogListItemDto> blogListItemDtos = getPublicBlogListItemDtos(pageIndex, pageSize, currentCategory.getId());


        mv.addObject("currentCategory", currentCategory);
        mv.addObject("totalPages", totalPages);
        mv.addObject("totalBlogs", blogCount);
        mv.addObject("pageIndex", pageIndex);
        mv.addObject("blogList", blogListItemDtos);

        return mv;
    }
}
