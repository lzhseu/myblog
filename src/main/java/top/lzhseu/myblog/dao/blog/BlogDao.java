package top.lzhseu.myblog.dao.blog;

import top.lzhseu.myblog.entity.blog.Blog;
import top.lzhseu.myblog.entity.blog.BlogCategory;

import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/5/26 20:50
 */
public interface BlogDao {

    /**
     * 博文总数量
     * @param blog 将需要的调见参数放入 blog 实例中
     * @return 博文数量
     */
    int count(Blog blog);

    /**
     * 分页查找博文列表
     * @param args 可选参数：pageIndex 页码；
     *                      pageSize 一页条数；
     *                      categoryId 博文所属类别id
     *                      bloggerId 博文所属作者id
     *                      state 博文状态
     * @return 结果
     */
    List<Blog> getBlogListItemsByPage(Map<String, Object> args);


    /**
     * 根据博文 id 找博文
     * @param id 博文 id
     * @return 结果
     */
    Blog getBlogById(Integer id);


    /**
     * 获取博文状态
     * @param id 博文id
     * @return 结果
     */
    Integer getBlogState(Integer id);


    /**
     * 插入博文
     * @param blog 博文实例
     */
    void insert(Blog blog);

    /**
     * 更新博文
     * @param blog 博文实例
     */
    void update(Blog blog);

    /**
     * 删除一条博文
     * @param id 博文id
     */
    void delete(Integer id);

    /**
     * 删除类别下所有博文
     * @param categoryId 类别id
     */
    void deleteByCategoryId(Integer categoryId);

}
