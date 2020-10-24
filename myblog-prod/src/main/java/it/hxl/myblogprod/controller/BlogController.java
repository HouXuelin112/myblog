package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.entity.Blog;
import it.hxl.myblogprod.entity.BlogAndTagDTO;
import it.hxl.myblogprod.http.WrapMapper;
import it.hxl.myblogprod.http.Wrapper;
import it.hxl.myblogprod.service.BlogService;
import it.hxl.myblogprod.service.TagService;
import it.hxl.myblogprod.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;

    @RequestMapping("/blog/findHot6Blogs")
    @ResponseBody
    public Wrapper findHot6Blogs(){
        return WrapMapper.ok(blogService.findHotTopBlogs(6));
    }
    @RequestMapping("/blog/findBlogsByTagId")
    @ResponseBody
    public Wrapper findBlogsByTagId(int tagId) {
        List<Blog> blogs = new ArrayList<>();
        blogs.addAll(blogService.findTopBlogsByTagId(tagId));
        blogs.addAll(blogService.findNotTopBlogsByTagId(tagId));
        return WrapMapper.ok(blogs);
    }

    @RequestMapping("/blog/findAllBlogsAndTags")
    @ResponseBody
    public Wrapper findAllBlogsAndTags(int pageId, int pageSize){
        BlogAndTagDTO blogAndTagDTO = BlogAndTagDTO.builder()
                .hotTop10Blogs(blogService.findHotTopBlogs(10))
                .notTopBlogs(blogService.findNotTopBlogs())
                .top10Blogs(blogService.findTop10Blogs())
                .recentVisitors(userService.findRecentVisitors())
                .tags(tagService.findAllTags())
                .topBlogs(blogService.findTopBlogs())
                .count(blogService.getCountBlogs())
                .pageBlogs(blogService.findBlogByPageId(pageId, pageSize))
                .build();
        return WrapMapper.ok(blogAndTagDTO);
    }

    @RequestMapping("/getTips")
    @ResponseBody
    public List<Blog> getTips(String text, HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        return blogService.findBlogsLike(text);
    }

    @RequestMapping("/article/{tagId}")
    public String getArticleByTag(@PathVariable int tagId, HttpServletRequest request){
        request.setAttribute("topBlogs", blogService.findTopBlogsByTagId(tagId));
        request.setAttribute("notTopBlogs", blogService.findNotTopBlogsByTagId(tagId));
        return "article";
    }

}
