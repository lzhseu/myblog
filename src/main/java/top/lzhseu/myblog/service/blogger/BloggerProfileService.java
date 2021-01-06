package top.lzhseu.myblog.service.blogger;

import top.lzhseu.myblog.entity.blogger.BloggerProfile;

/**
 * @author lzh
 * @date 2020/5/26 19:31
 */
public interface BloggerProfileService {

    /**
     * 获取博主个人信息
     * @return 结果
     */
    BloggerProfile getBloggerProfile();

    /**
     * 根据 id 找 profile
     * @param id profile id
     * @return 结果
     */
    BloggerProfile getBloggerProfileById(int id);

    /**
     * 插入博主信息
     * @param profile 博主信息实例
     */
    void insertProfile(BloggerProfile profile);

    /**
     * 更新博主信息
     * @param profile 博主信息
     */
    void updateProfile(BloggerProfile profile);
}
