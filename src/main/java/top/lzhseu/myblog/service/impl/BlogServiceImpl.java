package top.lzhseu.myblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lzhseu.myblog.dao.blog.BlogCategoryDao;
import top.lzhseu.myblog.dao.blog.BlogDao;
import top.lzhseu.myblog.dao.blogger.BloggerProfileDao;
import top.lzhseu.myblog.dto.BlogContentDto;
import top.lzhseu.myblog.dto.BlogListItemDto;
import top.lzhseu.myblog.dto.BlogEditDto;
import top.lzhseu.myblog.entity.blog.Blog;
import top.lzhseu.myblog.entity.blog.BlogCategory;
import top.lzhseu.myblog.entity.blogger.BloggerProfile;
import top.lzhseu.myblog.manager.DtoTransferManager;
import top.lzhseu.myblog.service.blog.BlogService;
import top.lzhseu.myblog.util.constant.BlogConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/5/26 21:21
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BloggerProfileDao profileDao;

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private DtoTransferManager dtoTransferManager;

    @Override
    public Integer getBlogState(Integer id) {
        return blogDao.getBlogState(id);
    }

    @Override
    public List<BlogListItemDto> getBlogListByPage(Map<String, Object> args) {

        List<Blog> blogs = blogDao.getBlogListItemsByPage(args);
        BlogCategory blogCategory;
        BloggerProfile bloggerProfile;

        List<BlogListItemDto> blogListItemDtos = new ArrayList<>();

        if (blogs != null) {
            for (Blog blog : blogs) {
                blogCategory =categoryDao.getBlogCategoryById(blog.getCategoryId());
                bloggerProfile = profileDao.getProfileByBloggerId(blog.getBloggerId());
                BlogListItemDto blogListItemDto = dtoTransferManager.blogListItemToDto(blog, bloggerProfile, blogCategory);
                blogListItemDtos.add(blogListItemDto);
            }
        }

        return blogListItemDtos;
    }

    @Override
    public int count() {
        return blogDao.count(null);
    }

    @Override
    public int publicCount() {
        Blog blog = new Blog();
        blog.setState(BlogConstant.PUBLIC_STATUS);
        return blogDao.count(blog);
    }

    @Override
    public int getBlogCountByCategory(BlogCategory blogCategory, Integer state) {
        Blog blog = new Blog();
        blog.setCategoryId(blogCategory.getId());
        blog.setState(state);
        return blogDao.count(blog);
    }

    @Override
    public int blogExist(Integer id) {
        Blog blog = new Blog();
        blog.setId(id);
        return blogDao.count(blog);
    }

    @Override
    public BlogEditDto getBlogEditDtoByBlogId(Integer blogId) {
        Blog blog = blogDao.getBlogById(blogId);
        return dtoTransferManager.toBlogEditDto(blog);
    }

    @Override
    public BlogContentDto getBlogContentDtoByBlogId(Integer blogId) {
        Blog blog = blogDao.getBlogById(blogId);
        BlogCategory category = categoryDao.getBlogCategoryById(blog.getCategoryId());
        BloggerProfile profile = profileDao.getProfileByBloggerId(blog.getBloggerId());
        return dtoTransferManager.toBlogContentDto(blog, profile, category);
    }


    @Override
    public void insertBlog(Blog blog) {
        blogDao.insert(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogDao.update(blog);
    }

    @Override
    public void deleteBlog(Integer id) {
        blogDao.delete(id);
    }
}
