package it.hxl.myblogprod.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import it.hxl.myblogprod.entity.Blog;
import it.hxl.myblogprod.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;


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
