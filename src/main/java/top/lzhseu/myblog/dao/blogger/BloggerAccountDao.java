package top.lzhseu.myblog.dao.blogger;

import top.lzhseu.myblog.entity.blogger.BloggerAccount;

/**
 * @author lzh
 * @date 2020/5/26 14:45
 */
public interface BloggerAccountDao {

    /**
     * 根据用户名(邮箱)和密码查询用过
     * @param username 用户名(邮箱)
     * @param password 密码
     * @return 结果
     */
    BloggerAccount getAccountByUsernameAndPassword(String username, String password);


    /**
     * 根据用户名（邮箱）获取用户
     * @param username 用户名（邮箱）
     * @return 结果
     */
    BloggerAccount getAccountByUsername(String username);
}
