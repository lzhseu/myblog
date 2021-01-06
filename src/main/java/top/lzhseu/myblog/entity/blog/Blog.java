package top.lzhseu.myblog.entity.blog;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lzh
 * @date 2020/5/26 9:09
 */
@Data
public class Blog {

    /**
     * 博文 id
     */
    private Integer id;

    /**
     * 博主 id
     */
    private Integer bloggerId;

    /**
     * 类别 id
     */
    private Integer categoryId;

    /**
     * 博文状态（公开，私有，草稿）
     */
    private Integer state;

    /**
     * 博文标题
     */
    private String title;

    /**
     * 博文主体内容 HTML 格式
     */
    private String content;

    /**
     * 博文主题内容 markdown 格式
     */
    private String contentMd;

    /**
     * 博文摘要
     */
    private String intro;

    /**
     * 首次发表日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    /**
     * 最后修改日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifyDate;

    /**
     * 博文字数
     */
    private Integer wordCount;
}
