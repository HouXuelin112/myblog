package it.hxl.myblogadmin1.controller;

import com.alibaba.fastjson.JSONObject;
import it.hxl.myblogadmin1.entity.Blog;
import it.hxl.myblogadmin1.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteBlogs(String deleteArr){
        blogService.deleteBlogs(deleteArr);
        return "ok";
    }


    @RequestMapping("/add")
//    @ResponseBody
    public String addBlog(Blog blog, String displayBase64) throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        blog.setDisplayImage(base64Decoder.decodeBuffer(displayBase64));
        blog.setIssueDate(new Date());
        blogService.insertBlog(blog);
        return "article";
    }

    @RequestMapping("/test")
    @ResponseBody
    public Object test(){
        List rows = new ArrayList();
        Map row = new HashMap();
        row.put("id", 1);
        rows.add(row);
        Map res = new HashMap();
        res.put("total", 1);
        res.put("rows", rows);
        return JSONObject.toJSON(res);
    }

    @RequestMapping("/getBlogs")
    @ResponseBody
    public Object getBlogs(HttpServletRequest request) throws FileNotFoundException {
//        System.out.println(basePath);
        List rows = blogService.findAllBlogs();
        int total = rows.size();
        Map res = new HashMap();
        res.put("total", total);
        res.put("rows", rows);
        return JSONObject.toJSON(res);
    }

}
