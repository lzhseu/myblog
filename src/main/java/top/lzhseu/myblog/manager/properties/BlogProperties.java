package top.lzhseu.myblog.manager.properties;

import lombok.Data;

/**
 * @author lzh
 * @date 2020/5/27 12:22
 */
@Data
public class BlogProperties {

    private Integer homePageSize;

    private Integer categoryPageSize;

    private String blogImageLocation;

    private String blogImageRootUrl;
}
