package top.lzhseu.myblog.dao.blog;

import top.lzhseu.myblog.entity.blog.BlogCategory;

import java.util.List;

/**
 * @author lzh
 * @date 2020/5/26 14:54
 */
public interface BlogCategoryDao {

    /**
     * 查询所有类别信息
     * @return 查询结果
     */
    List<BlogCategory> getAllBlogCategory();

    /**
     * 根据id查询类别
     * @param id 类别id
     * @return 查询结果
     */
    BlogCategory getBlogCategoryById(int id);

    /**
     * 根据博主 id 查询类别
     * @param bloggerId 博主id
     * @return 结果
     */
    List<BlogCategory> getBlogCategoryByBloggerId(int bloggerId);

    /**
     * 根据类别名称获取类别实例
     * @param title 类别名称
     * @return 结果
     */
    BlogCategory getBlogCategoryByTitle(String title);


    /**
     * 插入类别
     * @param category 类别
     */
    void insert(BlogCategory category);

    /**
     * 更新类别
     * @param category 类别
     */
    void update(BlogCategory category);

    /**
     * 删除类别
     * @param id
     */
    void delete(Integer id);
}
