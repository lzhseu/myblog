package top.lzhseu.myblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lzhseu.myblog.dao.blogger.BloggerAccountDao;
import top.lzhseu.myblog.dao.blogger.BloggerProfileDao;
import top.lzhseu.myblog.entity.blogger.BloggerAccount;
import top.lzhseu.myblog.entity.blogger.BloggerProfile;
import top.lzhseu.myblog.exception.api.blogger.UnknownBloggerException;
import top.lzhseu.myblog.manager.properties.BloggerProperties;
import top.lzhseu.myblog.service.blogger.BloggerProfileService;
import top.lzhseu.myblog.util.constant.exception.ExceptionMessage;

/**
 * @author lzh
 * @date 2020/5/26 19:40
 */
@Service
public class BloggerProfileServiceImpl implements BloggerProfileService {

    @Autowired
    private BloggerProfileDao profileDao;

    @Autowired
    private BloggerAccountDao accountDao;

    @Autowired
    private BloggerProperties bloggerProperties;

    /**
     * 获取博主信息
     * 这里是以 email 作为标识，email 在数据表中是 UNIQUE KEY
     * @return
     */
    @Override
    public BloggerProfile getBloggerProfile() {

        BloggerAccount account = accountDao.getAccountByUsername(bloggerProperties.getEmail());
        if (account == null) {
            throw new UnknownBloggerException(ExceptionMessage.UNKNOWN_BLOGGER_EXCEPTION_MSG);
        }
        return profileDao.getProfileByBloggerId(account.getId());
    }

    @Override
    public BloggerProfile getBloggerProfileById(int id) {
        //return profileDao.getProfileByBloggerId(id);
        return profileDao.getProfileById(id);
    }


    @Override
    public void insertProfile(BloggerProfile profile) {
        profileDao.insert(profile);
    }

    @Override
    public void updateProfile(BloggerProfile profile) {
        profileDao.update(profile);
    }
}
