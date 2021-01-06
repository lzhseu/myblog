package top.lzhseu.myblog.controller.api.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lzhseu.myblog.controller.BaseCheckController;
import top.lzhseu.myblog.entity.blog.BlogCategory;
import top.lzhseu.myblog.entity.blogger.BloggerAccount;
import top.lzhseu.myblog.exception.api.common.DuplicationDataException;
import top.lzhseu.myblog.service.blog.BlogCategoryService;
import top.lzhseu.myblog.service.blog.BlogService;
import top.lzhseu.myblog.util.constant.BloggerConstant;
import top.lzhseu.myblog.util.constant.exception.ExceptionMessage;
import top.lzhseu.myblog.util.restful.ResultBean;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author lzh
 * @date 2020/6/1 11:55
 */
@RestController
@RequestMapping("/manage/category")
public class BlogCategoryController extends BaseCheckController {

    @Autowired
    private BlogCategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/getCategoryByTitle")
    public Object getCategoryByTitle(@RequestParam("title") String title) throws UnsupportedEncodingException {

        if (title == null) {
            handleIllegalParameter();
        }

        title = URLDecoder.decode(title, "UTF-8");

        BlogCategory category = categoryService.getBlogCategoryByTitle(title);
        return new ResultBean(category);
    }


    @RequestMapping("/getAllCategory")
    public Object getAllCategory(HttpServletRequest request) {

        int bloggerId = handleBloggerLogin(request);
        List<BlogCategory> bloggerCategory = categoryService.getBloggerCategory(bloggerId);
        return new ResultBean(bloggerCategory);
    }

    @PostMapping("/update")
    public Object updateCategory(BlogCategory category,
                                 HttpServletRequest request) {

        if (category == null) {
            handleIllegalParameter();
        }

        int bloggerId = handleBloggerLogin(request);
        category.setBloggerId(bloggerId);

        if (category.getId() == null) {
            BlogCategory existCategory = categoryService.getBlogCategoryByTitle(category.getTitle());
            if (existCategory != null) {
                throw new DuplicationDataException(ExceptionMessage.DUPLICATION_DATA_EXCEPTION_MSG);
            }
            categoryService.insertCategory(category);
        } else {
            categoryService.updateCategory(category);
        }

        return new ResultBean(category);
    }

    @PostMapping("/delete")
    public Object deleteCategory(@RequestParam("id") Integer id) {

        if (id == null) {
            handleIllegalParameter();
        }

        BlogCategory category = new BlogCategory();
        category.setId(id);
        int count = blogService.getBlogCountByCategory(category, null);
        if (count < 1) {
            categoryService.deleteCategory(id);
            return new ResultBean("delete ok");
        } else {
            // 如果该类别下有博文，返回博文条数，进一步确认后再删除
            return new ResultBean(count);
        }
    }

    @PostMapping("/confirmDelete")
    public Object deleteCategoryAndBlogs(@RequestParam("id") Integer id,
                                         @RequestParam("count") Integer count) {

        if (id == null || count == null) {
            handleIllegalParameter();
        }

        int flag = categoryService.deleteCategoryAndBlogs(id, count);
        if (flag > 0) {
            return new ResultBean("delete ok");
        }
        return new ResultBean(ResultBean.FAIL, "delete fail");
    }
}
