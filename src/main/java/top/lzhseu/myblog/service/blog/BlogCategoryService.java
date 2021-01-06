package top.lzhseu.myblog.service.blog;

import top.lzhseu.myblog.entity.blog.BlogCategory;

import java.util.List;

/**
 * @author lzh
 * @date 2020/5/26 19:38
 */
public interface BlogCategoryService {

    /**
     * 获取该博主的所有类别
     * @param bloggerId 博主id
     * @return 获取结果
     */
    List<BlogCategory> getBloggerCategory(int bloggerId);


    /**
     * 根据类别 id 获取类别实例
     * @param id 类别 id
     * @return 结果
     */
    BlogCategory getBlogCategoryById(Integer id);

    /**
     * 获取某类别的类别实例
     * @param title 类别名称
     * @return 结果
     */
    BlogCategory getBlogCategoryByTitle(String title);

    /**
     * 插入类别
     * @param blogCategory 类别
     */
    void insertCategory(BlogCategory blogCategory);

    /**
     * 更新类别
     * @param blogCategory 类别
     */
    void updateCategory(BlogCategory blogCategory);

    /**
     * 删除类别
     * @param id 类别id
     */
    void deleteCategory(Integer id);


    /**
     * 删除类别及该类别下的所有博文
     * @param id 类别 id
     * @param count 该类别下的文章数目
     * @return success 1; fail -1
     */
    int deleteCategoryAndBlogs(Integer id, Integer count);
}
