package top.lzhseu.myblog.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.lzhseu.myblog.controller.BaseDataController;
import top.lzhseu.myblog.dto.BlogListItemDto;
import top.lzhseu.myblog.manager.SideBarDataManager;
import top.lzhseu.myblog.manager.properties.BlogProperties;
import top.lzhseu.myblog.service.blog.BlogService;
import top.lzhseu.myblog.util.constant.BlogConstant;
import top.lzhseu.myblog.util.constant.CommonConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/5/26 19:29
 */
@Controller
@RequestMapping("/home")
public class BlogHomePageController extends BaseDataController {

    @Autowired
    private SideBarDataManager sideBarDataManager;

    @Autowired
    private BlogProperties blogProperties;

    /**
     * 博客主页
     * @return
     */
    @RequestMapping("/page={pageIndex}")
    public ModelAndView mainPage(@PathVariable Integer pageIndex) {

        ModelAndView mv = new ModelAndView();

        // 获取左侧边栏数据
        Map<String, Object> sideBarData = sideBarDataManager.getSideBarData();
        mv.addObject("bloggerProfile", sideBarData.get("bloggerProfile"));
        mv.addObject("blogCategory", sideBarData.get("blogCategory"));

        // 计算博文总数，所需分页数
        int blogCount = blogService.publicCount();
        int pageSize = blogProperties.getHomePageSize();
        int totalPages = calTotalPages(blogCount, pageSize);
        mv.addObject("totalPages", totalPages);
        mv.addObject("totalBlogs", blogCount);
        mv.addObject("pageIndex", pageIndex);

        // 获取博文列表数据;
        List<BlogListItemDto> blogListItemDtos = getPublicBlogListItemDtos(pageIndex, pageSize, null);
        mv.addObject("blogList", blogListItemDtos);

        mv.setViewName("blog/main");

        return mv;
    }

}
