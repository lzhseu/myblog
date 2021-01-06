package top.lzhseu.myblog.entity.blogger;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lzh
 * @date 2020/5/26 9:02
 */
@Data
public class BloggerAccount {

    /**
     * 用户（博主）id
     */
    private Integer id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
