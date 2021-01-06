package top.lzhseu.myblog.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.lzhseu.myblog.entity.blog.BlogCategory;
import top.lzhseu.myblog.entity.blogger.BloggerProfile;
import top.lzhseu.myblog.service.blogger.BloggerProfileService;
import top.lzhseu.myblog.service.blog.BlogCategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/5/27 21:33
 */
@Component
public class SideBarDataManager {

    @Autowired
    private BloggerProfileService profileService;

    @Autowired
    private BlogCategoryService categoryService;


    public Map<String, Object> getSideBarData() {

        Map<String, Object> map = new HashMap<>();

        BloggerProfile bloggerProfile = profileService.getBloggerProfile();

        List<BlogCategory> blogCategory = new ArrayList<>();

        if (bloggerProfile != null) {
            blogCategory = categoryService.getBloggerCategory(bloggerProfile.getBloggerId());
        }

        map.put("bloggerProfile", bloggerProfile);
        map.put("blogCategory", blogCategory);

        return map;
    }
}
