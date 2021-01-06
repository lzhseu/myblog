package top.lzhseu.myblog.util.constant;

/**
 * @author lzh
 * @date 2020/5/31 12:02
 */
public class BlogConstant {

    /**
     * 空内容
     */
    public static final String EMPTY_CONTENT = "";

    /**
     * 博文状态：公开
     */
    public static final int PUBLIC_STATUS = 2;

    /**
     * 博文状态：公开，但不在文章列表中展示，仅作为内部链接
     */
    public static final int INTERNAL_STATUS = 3;

    /**
     * 博文状态：私密
     */
    public static final int PRIVATE_STATUS = 1;

    /**
     * 博文状态：暂存
     */
    public static final int SAVE_STATUS = 0;

    /**
     * 博文状态：所有状态
     */
    public static final Integer ALL_STATUS = null;

    /**
     * 博文编辑模式：创建博文
     */
    public static final int CREATE_EDIT_MODE = 1;

    /**
     * 博文编辑模式：修改博文
     */
    public static final int UPDATE_EDIT_MODE = 2;

}
