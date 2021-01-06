package top.lzhseu.myblog.manager;

import org.springframework.stereotype.Component;
import top.lzhseu.myblog.dto.BlogContentDto;
import top.lzhseu.myblog.dto.BlogListItemDto;
import top.lzhseu.myblog.dto.BlogEditDto;
import top.lzhseu.myblog.entity.blogger.BloggerProfile;
import top.lzhseu.myblog.entity.blog.Blog;
import top.lzhseu.myblog.entity.blog.BlogCategory;
import top.lzhseu.myblog.util.tools.DataFormatUtil;
import top.lzhseu.myblog.util.tools.StringUtil;

/**
 * 将 entity 转换为 dto 数据
 * @author lzh
 * @date 2020/5/26 22:02
 */
@Component
public class DtoTransferManager {

    public BlogListItemDto blogListItemToDto(Blog blog, BloggerProfile bloggerProfile, BlogCategory blogCategory) {

        BlogListItemDto dto = new BlogListItemDto();

        dto.setNickname(bloggerProfile.getNickname());
        dto.setCategory("#" + blogCategory.getTitle());
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setIntro(blog.getIntro());
        dto.setState(blog.getState());
        dto.setDate(DataFormatUtil.dateToString(blog.getDate()));
        dto.setModifyDate(DataFormatUtil.dateToString(blog.getModifyDate()));
        dto.setWordCount(blog.getWordCount());

        return dto;
    }

    public BlogEditDto toBlogEditDto(Blog blog) {
        BlogEditDto dto = new BlogEditDto();
        dto.setBlogId(blog.getId());
        dto.setBlogTitle(blog.getTitle());
        dto.setBlogIntro(StringUtil.stringToUnicode(blog.getIntro()));
        dto.setContentMd(StringUtil.stringToUnicode(blog.getContentMd()));
        dto.setCategoryId(blog.getCategoryId());
        return dto;
    }


    public BlogContentDto toBlogContentDto(Blog blog, BloggerProfile profile, BlogCategory category) {
        BlogContentDto dto = new BlogContentDto();
        dto.setBlogId(blog.getId());
        dto.setBlogTitle(blog.getTitle());
        dto.setContentMd(StringUtil.stringToUnicode(blog.getContentMd()));
        dto.setDistrDate(DataFormatUtil.dateToString(blog.getDate()));
        dto.setAuthor(profile.getNickname());
        dto.setCategoryTitle(category.getTitle());
        return dto;
    }

}
