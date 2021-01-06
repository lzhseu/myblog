package top.lzhseu.myblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lzhseu.myblog.dao.blogger.BloggerAccountDao;
import top.lzhseu.myblog.entity.blogger.BloggerAccount;
import top.lzhseu.myblog.service.blogger.BloggerAccountService;

/**
 * @author lzh
 * @date 2020/5/31 19:53
 */
@Service
public class BloggerAccountServiceImpl implements BloggerAccountService {

    @Autowired
    private BloggerAccountDao accountDao;

    @Override
    public BloggerAccount login(String email, String password) {
        return accountDao.getAccountByUsernameAndPassword(email, password);
    }
}
