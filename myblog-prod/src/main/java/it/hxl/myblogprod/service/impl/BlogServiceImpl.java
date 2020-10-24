package it.hxl.myblogprod.service.impl;

import it.hxl.myblogprod.entity.Blog;
import it.hxl.myblogprod.mapper.BlogMapper;
import it.hxl.myblogprod.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> findBlogByPageId(int pageId, int pageSize) {
        List<Blog> blogs = blogMapper.findBlogByPageId(pageId, pageSize);
        return blogs != null ? blogs.subList((pageId - 1) * pageSize, blogs.size()) : null;
    }

    @Override
    public int getCountBlogs() {
        return blogMapper.getCountBlogs();
    }

    @Override
    public List<Blog> findTopBlogsByTagId(int tagId) {
        return blogMapper.findTopBlogsByTagId(tagId);
    }

    @Override
    public List<Blog> findNotTopBlogsByTagId(int tagId) {
        return blogMapper.findNotTopBlogsByTagId(tagId);
    }

    @Override
    public List<Blog> findNotTopBlogs() {
        return blogMapper.findNotTopBlogs();
    }

    @Override
    public List<Blog> findHotTopBlogs(int rows) {
        return blogMapper.findHotTopBlogs(rows);
    }

    @Override
    public Blog findBlogById(int id) {
        return blogMapper.findBlogById(id);
    }

    @Override
    public List<Blog> findTopBlogs() {
        return blogMapper.findTopBlogs();
    }

    @Override
    public List<Blog> findTop10Blogs() {
        return blogMapper.findTop10Blogs();
    }

    @Override
    public List<Blog> findBlogsLike(String text) {
        return blogMapper.findBlogsLike(text);
    }

}
