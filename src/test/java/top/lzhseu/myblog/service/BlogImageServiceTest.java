package top.lzhseu.myblog.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.lzhseu.myblog.BaseTest;
import top.lzhseu.myblog.entity.blog.BlogImage;
import top.lzhseu.myblog.service.blog.BlogImageService;

/**
 * @author lzh
 * @date 2020/5/31 11:41
 */
public class BlogImageServiceTest extends BaseTest {

    @Autowired
    private BlogImageService blogImageService;

    @Test
    public void testInsertImage() {
        BlogImage image = new BlogImage();
        image.setDesc("hhh");
        //blogImageService.insertImage(image);
    }
}
