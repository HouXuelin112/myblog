package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.Blog;

import java.util.List;

public interface BlogService {

    int deleteBlogs(String deleteStr);

    int insertBlog(Blog blog);

    int getBlogCount();

    List<Blog> findAllBlogs();

}
