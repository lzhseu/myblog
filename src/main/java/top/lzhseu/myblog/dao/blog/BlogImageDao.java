package top.lzhseu.myblog.dao.blog;

import top.lzhseu.myblog.entity.blog.BlogImage;

/**
 * @author lzh
 * @date 2020/5/30 19:34
 */
public interface BlogImageDao {

    /**
     * 根据图片id找图片
     * @param id 图片id
     * @return 结果
     */
    BlogImage getImageById(int id);

    /**
     * 根据图片名称找图片
     * @param title 图片名称
     * @return 结果
     */
    BlogImage getImageByTitle(String title);

    /**
     * 插入图片
     * @param blogImage 图片对象
     */
    void insert(BlogImage blogImage);

    /**
     * 更新图片
     * @param blogImage 图片实例
     */
    void update(BlogImage blogImage);
}
