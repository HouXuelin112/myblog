package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.Blog;
import it.hxl.myblogadmin1.mapper.BlogMapper;
import it.hxl.myblogadmin1.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int deleteBlogs(String deleteStr) {
        return blogMapper.deleteBlogs(deleteStr);
    }

    @Override
    public int insertBlog(Blog blog) {
        return blogMapper.insertBlog(blog);
    }

    @Override
    public int getBlogCount() {
        return blogMapper.getBlogCount();
    }

    @Override
    public List<Blog> findAllBlogs() {
        return blogMapper.findAllBlogs();
    }
}
