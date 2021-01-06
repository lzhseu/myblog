package top.lzhseu.myblog.service.blog;

import top.lzhseu.myblog.dto.BlogContentDto;
import top.lzhseu.myblog.dto.BlogListItemDto;
import top.lzhseu.myblog.dto.BlogEditDto;
import top.lzhseu.myblog.entity.blog.Blog;
import top.lzhseu.myblog.entity.blog.BlogCategory;

import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/5/26 21:18
 */
public interface BlogService {


    /**
     * 获取博文状态
     * @param id 博文 id
     * @return 状态结果
     */
    Integer getBlogState(Integer id);

    /**
     * 分页获取文章列表
     * @param args 参数
     * @return 结果
     */
    List<BlogListItemDto> getBlogListByPage(Map<String, Object> args);


    /**
     * 获取博文总数
     * @return 博文总数
     */
    int count();


    /**
     * 获取公开的博文总数
     * @return
     */
    int publicCount();


    /**
     * 获取某一类别的文章条数
     * @param blogCategory 博文类别
     * @param state 博文状态  null 表示所有
     * @return 文章数目
     */
    int getBlogCountByCategory(BlogCategory blogCategory, Integer state);


    /**
     * 博文是否存在
     * @param id 博文 id
     * @return >0 存在
     */
    int blogExist(Integer id);


    /**
     * 获取编辑博文页面所需的内容
     * @param blogId 博文 id
     * @return 结果
     */
    BlogEditDto getBlogEditDtoByBlogId(Integer blogId);


    /**
     * 获取展示的博文内容页面所需的内容
     * @param blogId 博文 id
     * @return 结果
     */
    BlogContentDto getBlogContentDtoByBlogId(Integer blogId);

    /**
     * 插入博文到数据库
     * @param blog 博文实例
     */
    void insertBlog(Blog blog);

    /**
     * 更新博文
     * @param blog 博文实例
     */
    void updateBlog(Blog blog);

    /**
     * 删除博文
     * @param id 博文 id
     */
    void deleteBlog(Integer id);
}
