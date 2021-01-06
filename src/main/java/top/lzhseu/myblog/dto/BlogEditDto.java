package top.lzhseu.myblog.dto;

import lombok.Data;

/**
 * @author lzh
 * @date 2020/6/2 17:51
 */
@Data
public class BlogEditDto {

    private Integer blogId;

    private String blogTitle;

//    private String categoryTitle;

    private Integer categoryId;

    private String blogIntro;

    private String contentMd;
}
