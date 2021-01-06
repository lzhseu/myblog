package top.lzhseu.myblog.controller.api.blogger;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.lzhseu.myblog.controller.BaseCheckController;
import top.lzhseu.myblog.entity.blog.Blog;
import top.lzhseu.myblog.entity.blog.BlogImage;
import top.lzhseu.myblog.manager.properties.BlogProperties;
import top.lzhseu.myblog.service.blog.BlogImageService;
import top.lzhseu.myblog.service.blog.BlogService;
import top.lzhseu.myblog.util.constant.BlogConstant;
import top.lzhseu.myblog.util.restful.ResultBean;
import top.lzhseu.myblog.util.tools.ImageUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author lzh
 * @date 2020/6/1 11:52
 */
@Controller
@RequestMapping("manage/blogger")
public class BloggerEditBlogController extends BaseCheckController {

    @Autowired
    private BlogService blogService;


    @PostMapping("/edit/save")
    @ResponseBody
    public Object saveBlog(Blog blog,
                           HttpServletRequest request) {

        saveBlog(blog, BlogConstant.SAVE_STATUS, request);

        //返回blogID
        return new ResultBean(blog.getId());
    }


    @PostMapping("/edit/distribute")
    @ResponseBody
    public Object distributeBlog(Blog blog,
                                 HttpServletRequest request) {

        saveBlog(blog, BlogConstant.PUBLIC_STATUS, request);
        return new ResultBean(blog.getId());
    }

    @PostMapping("/edit/internal")
    @ResponseBody
    public Object saveInternalBlog(Blog blog,
                                   HttpServletRequest request) {

        saveBlog(blog, BlogConstant.INTERNAL_STATUS, request);
        return new ResultBean(blog.getId());
    }

    private void saveBlog(Blog blog, int status, HttpServletRequest request) {

        int bloggerId = handleBloggerLogin(request);
        blog.setBloggerId(bloggerId);

        Integer blogId = blog.getId();

        blog.setState(status);

        // 暂时没做字数统计的功能
        blog.setWordCount(0);

        if (blogId == null || blogService.blogExist(blogId) < 1) {
            blogService.insertBlog(blog);
        } else {
            // 更新的话不改变首次发布时间
            blog.setDate(null);
            blogService.updateBlog(blog);
        }
    }

}
