package top.lzhseu.myblog.dao.blogger;

import top.lzhseu.myblog.entity.blogger.BloggerProfile;

/**
 * @author lzh
 * @date 2020/5/26 14:45
 */
public interface BloggerProfileDao {

    /**
     * 根据 id 找 profile
     * @param id profile id
     * @return 结果
     */
    BloggerProfile getProfileById(int id);

    /**
     * 根据博主 id 获取博主属性
     * @param bloggerId 博主 id
     * @return 获取结果
     */
    BloggerProfile getProfileByBloggerId(int bloggerId);

    /**
     * 根据博主 email 获取博主属性
     * @param email 博主 email
     * @return 获取结果
     */
    BloggerProfile getProfileByEmail(String email);


    /**
     * 插入个人资料
     * @param profile 个人资料实例
     */
    void insert(BloggerProfile profile);


    /**
     * 修改个人资料
     * @param profile 个人资料实例
     */
    void update(BloggerProfile profile);
}
