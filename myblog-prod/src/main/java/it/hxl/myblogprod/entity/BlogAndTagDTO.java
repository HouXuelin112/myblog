package it.hxl.myblogprod.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogAndTagDTO implements Serializable {
    // 博客总条数
    private int count;
    private List<Blog> pageBlogs;
    // 置顶博文
    private List<Blog> topBlogs;
    // 非置顶博文
    private List<Blog> notTopBlogs;
    // 置顶推荐
    private List<Blog> top10Blogs;
    // 热门推荐
    private List<Blog> hotTop10Blogs;
    // 最近访客
    private List<Users> recentVisitors;
    // 标签
    private List<Tag> tags;

}
