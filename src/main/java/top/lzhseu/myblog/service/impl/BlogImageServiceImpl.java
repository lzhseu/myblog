package top.lzhseu.myblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lzhseu.myblog.dao.blog.BlogImageDao;
import top.lzhseu.myblog.entity.blog.BlogImage;
import top.lzhseu.myblog.manager.properties.BlogProperties;
import top.lzhseu.myblog.service.blog.BlogImageService;

/**
 * @author lzh
 * @date 2020/5/30 19:39
 */
@Service
public class BlogImageServiceImpl implements BlogImageService {

    @Autowired
    private BlogImageDao imageDao;

    @Autowired
    private BlogProperties blogProperties;

    @Override
    public BlogImage getImageById(int id) {
        return imageDao.getImageById(id);
    }

    @Override
    public BlogImage getImageByTitle(String title) {
        return imageDao.getImageByTitle(title);
    }

    @Override
    public void insertImage(BlogImage image) {
        imageDao.insert(image);
        String url = blogProperties.getBlogImageRootUrl() + "/" + image.getId();
        image.setUrl(url);
        imageDao.update(image);
    }
}
