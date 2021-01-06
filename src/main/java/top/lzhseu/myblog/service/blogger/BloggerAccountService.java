package top.lzhseu.myblog.service.blogger;

import top.lzhseu.myblog.entity.blogger.BloggerAccount;

/**
 * @author lzh
 * @date 2020/5/31 19:52
 */
public interface BloggerAccountService {

    /**
     * 登录
     * @param email 邮箱（用户名）
     * @param password 密码
     * @return 不存在 ：null
     */
    BloggerAccount login(String email, String password);
}
