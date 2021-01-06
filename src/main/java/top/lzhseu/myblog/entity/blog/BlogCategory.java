package top.lzhseu.myblog.entity.blog;

import lombok.Data;

/**
 * @author lzh
 * @date 2020/5/26 9:14
 */
@Data
public class BlogCategory {

    /**
     * 类别 id
     */
    private Integer id;

    /**
     * 博主 id
     */
    private Integer bloggerId;

    /**
     * 类别名称
     */
    private String title;

    /**
     * 类别描述
     */
    private String desc;
}
