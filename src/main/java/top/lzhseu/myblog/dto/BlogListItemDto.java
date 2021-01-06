package top.lzhseu.myblog.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lzh
 * @date 2020/5/26 21:55
 */
@Data
public class BlogListItemDto implements Serializable {

    private String nickname;

    private String category;

    private Integer id;

    private String title;

    private String intro;

    private Integer state;

    private String date;

    private String modifyDate;

    private int wordCount;
}
