package top.lzhseu.myblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import top.lzhseu.myblog.dto.BlogListItemDto;
import top.lzhseu.myblog.service.blog.BlogService;
import top.lzhseu.myblog.util.constant.BlogConstant;
import top.lzhseu.myblog.util.constant.CommonConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/5/28 15:44
 */
public class BaseDataController extends BaseCheckController {

    @Autowired
    protected BlogService blogService;

    /**
     * 计算分页所需的总页数
     * @param blogCount 博文条数
     * @param pageSize 每页大小
     * @return 总页数
     */
    public int calTotalPages(int blogCount, int pageSize) {

        int totalPages = 0;

        if (blogCount <= pageSize) {
            totalPages = 1;
        } else {
            totalPages = blogCount % pageSize == 0 ? blogCount / pageSize : blogCount / pageSize + 1;
        }

        return totalPages;
    }


    /**
     * 获取某类别的博文列表数据，并封装
     * @param pageIndex 页码
     * @param pageSize 一页大小
     * @param categoryId 类别  null 表示所有类别
     * @return 结果
     */
    public List<BlogListItemDto> getPublicBlogListItemDtos(Integer pageIndex, Integer pageSize, Integer categoryId) {
        return getBlogListItemDtos(pageIndex, pageSize, categoryId, BlogConstant.PUBLIC_STATUS);
    }

    /**
     * 获取某类别的博文列表数据，并封装
     * @param pageIndex 页码
     * @param pageSize 一页大小
     * @param categoryId 类别 id
     * @param state 博文状态
     * @return 结果
     */
    public List<BlogListItemDto> getBlogListItemDtos(Integer pageIndex, Integer pageSize, Integer categoryId, Integer state) {
        Map<String, Object> args = new HashMap<>(16);
        args.put(CommonConstant.PAGE_INDEX, pageIndex);
        args.put(CommonConstant.PAGE_SIZE, pageSize);
        args.put("categoryId", categoryId);
        args.put("state", state);
        return blogService.getBlogListByPage(args);
    }


    /**
     * URL 解码
     * @param str 待解码字符串
     * @return 解码后字符串
     * @throws UnsupportedEncodingException 异常
     */
    public String urlDecode(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(str, "UTF-8");
    }

}
