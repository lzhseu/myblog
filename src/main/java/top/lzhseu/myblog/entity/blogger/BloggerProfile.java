package top.lzhseu.myblog.entity.blogger;

import lombok.Data;

/**
 * @author lzh
 * @date 2020/5/26 9:04
 */
@Data
public class BloggerProfile {

    /**
     * 条目id
     */
    private Integer id;

    /**
     * 博主id
     */
    private Integer bloggerId;

    /**
     * 博主昵称
     */
    private String nickname;

    /**
     * 个性标签
     */
    private String label;

    /**
     * 一句话自我介绍
     */
    private String intro;

    /**
     * 博主邮箱
     */
    private String email;

}
