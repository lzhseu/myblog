package top.lzhseu.myblog.controller.api.blog;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.lzhseu.myblog.controller.BaseDataController;
import top.lzhseu.myblog.dto.BlogListItemDto;
import top.lzhseu.myblog.entity.blog.BlogCategory;
import top.lzhseu.myblog.exception.api.common.EmptyResultException;
import top.lzhseu.myblog.exception.api.parameter.ParameterIllegalException;
import top.lzhseu.myblog.service.blog.BlogCategoryService;
import top.lzhseu.myblog.service.blog.BlogService;
import top.lzhseu.myblog.util.constant.BlogConstant;
import top.lzhseu.myblog.util.constant.exception.ExceptionMessage;
import top.lzhseu.myblog.util.restful.ResultBean;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author lzh
 * @date 2020/6/1 20:09
 */
@RestController
@RequestMapping("/manage/blog")
public class BlogController extends BaseDataController {

    @Autowired
    private BlogCategoryService categoryService;

    @RequestMapping("/getBlogListAllByCategoryTitle")
    public Object getBlogListAllByCategoryTitle(@RequestParam String title,
                                                @RequestParam Integer pageIndex,
                                                @RequestParam Integer pageSize) throws UnsupportedEncodingException {

        handleParameterCheck(title, pageIndex, pageSize);
        if (pageIndex < 1 || pageSize < 1) {
            handleIllegalParameter();
        }

        title = urlDecode(title);

        BlogCategory category = categoryService.getBlogCategoryByTitle(title);
        if (category == null || category.getId() == null) {
            throw new EmptyResultException(ExceptionMessage.EMPTY_RESULT_EXCEPTION_MSG);
        }

        List<BlogListItemDto> blogListItemDtos = getBlogListItemDtos(pageIndex, pageSize, category.getId(), BlogConstant.ALL_STATUS);

        int blogCount = blogService.getBlogCountByCategory(category, BlogConstant.ALL_STATUS);
        int totalPages = calTotalPages(blogCount, pageSize);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("blogListItemDtos", blogListItemDtos);
        jsonObject.put("blogCount", blogCount);
        jsonObject.put("totalPages", totalPages);

        return new ResultBean(jsonObject);
    }

    @RequestMapping("/delete")
    public Object deleteBlogById(@RequestParam Integer id) {

        if (id == null) {
            throw new ParameterIllegalException(ExceptionMessage.PARAMETER_ILLEGAL_EXCEPTION_MSG);
        }

        blogService.deleteBlog(id);
        return new ResultBean("delete ok");
    }

}
