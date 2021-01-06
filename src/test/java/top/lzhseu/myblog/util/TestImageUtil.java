package top.lzhseu.myblog.util;

import org.junit.Test;
import top.lzhseu.myblog.util.tools.ImageUtil;

/**
 * @author lzh
 * @date 2020/6/7 18:11
 */
public class TestImageUtil{

    @Test
    public void testGetImageMimeType() {
        String type = ImageUtil.getImageMimeType("aaa1.6.png");
        System.out.println(type);
    }
}
