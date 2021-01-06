package top.lzhseu.myblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lzhseu.myblog.dao.blog.BlogCategoryDao;
import top.lzhseu.myblog.dao.blog.BlogDao;
import top.lzhseu.myblog.entity.blog.Blog;
import top.lzhseu.myblog.entity.blog.BlogCategory;
import top.lzhseu.myblog.service.blog.BlogCategoryService;

import java.util.List;

/**
 * @author lzh
 * @date 2020/5/26 19:41
 */
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryDao categoryDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public List<BlogCategory> getBloggerCategory(int bloggerId) {
        return categoryDao.getBlogCategoryByBloggerId(bloggerId);
    }

    @Override
    public BlogCategory getBlogCategoryById(Integer id) {
        return categoryDao.getBlogCategoryById(id);
    }

    @Override
    public BlogCategory getBlogCategoryByTitle(String title) {
        return categoryDao.getBlogCategoryByTitle(title);
    }

    @Override
    public void insertCategory(BlogCategory blogCategory) {
        categoryDao.insert(blogCategory);
    }

    @Override
    public void updateCategory(BlogCategory blogCategory) {
        categoryDao.update(blogCategory);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryDao.delete(id);
    }

    @Override
    public int deleteCategoryAndBlogs(Integer id, Integer count) {

        Blog blog = new Blog();
        blog.setCategoryId(id);

        if (count == blogDao.count(blog)) {
            blogDao.deleteByCategoryId(id);
            categoryDao.delete(id);
            return 1;
        }
        return -1;
    }
}
