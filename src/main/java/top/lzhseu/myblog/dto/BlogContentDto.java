package top.lzhseu.myblog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author lzh
 * @date 2020/6/3 8:55
 */
@Data
public class BlogContentDto {

    private Integer blogId;

    private String blogTitle;

    private String categoryTitle;

    private String contentMd;

    private String author;

    private String distrDate;
}
