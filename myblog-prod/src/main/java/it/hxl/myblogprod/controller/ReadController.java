package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.entity.Blog;
import it.hxl.myblogprod.entity.Comments;
import it.hxl.myblogprod.service.BlogService;
import it.hxl.myblogprod.service.CommentsService;
import it.hxl.myblogprod.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ReadController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentsService commentsService;

    public String getBlogContent(){
        return null;
    }


    @RequestMapping("/read/{blogId}")
//    @ResponseBody
    public String readNth(@PathVariable("blogId")int id, Model model, HttpServletRequest request) throws IOException {
        String ip = CommonUtils.getIp(request);
        String macAddr = CommonUtils.getMacInWindows(ip); //F8-59-71-03-84-F9
        Blog blog = blogService.findBlogById(id);
        String filename = blog.getFilename();
        String parentPath = "/static/blogs/";
        String suffix = ".md";
        String content = getContentFromPath(parentPath, filename, suffix);
        model.addAttribute("content", content);
        model.addAttribute("blog", blog);
        model.addAttribute("macAddr", macAddr);
        List<Comments> comments = commentsService.findAllCommentsByBlogId(id);
        model.addAttribute("comments", comments);
        return "read";
    }


    private String getContentFromPath(String parentPath, String filename, String suffix) throws IOException {

        InputStream in = ReadController.class.getResourceAsStream(((parentPath == null || parentPath == "") ? "/static/blogs/" : parentPath) + filename + ((suffix == null || suffix == "") ? ".md" : suffix));
        byte[] bytes;
        if (in != null){
            bytes = new byte[in.available()];
            in.read(bytes);
        }else {
            return "error";
        }
        String content = new String(bytes);
        return content;
    }

}
