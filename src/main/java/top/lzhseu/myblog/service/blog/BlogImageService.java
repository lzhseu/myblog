package top.lzhseu.myblog.service.blog;

import top.lzhseu.myblog.entity.blog.BlogImage;

/**
 * @author lzh
 * @date 2020/5/30 19:37
 */
public interface BlogImageService {

    BlogImage getImageById(int id);

    /**
     * 根据图片名称找图片
     * @param title 图片名称
     * @return 结果
     */
    BlogImage getImageByTitle(String title);

    /**
     * 插入图片
     * @param image 图片实例
     */
    void insertImage(BlogImage image);
}
