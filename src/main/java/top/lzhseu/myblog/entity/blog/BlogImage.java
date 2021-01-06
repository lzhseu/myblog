package top.lzhseu.myblog.entity.blog;

import lombok.Data;

/**
 * 博客中插入的图片
 * @author lzh
 * @date 2020/5/30 19:27
 */
@Data
public class BlogImage {

    /**
     * 图片 id
     */
    private Integer id;

    /**
     * 图片名称
     */
    private String title;

    /**
     * 图片描述
     */
    private String desc;

    /**
     * 图片地址
     */
    private String url;
}
