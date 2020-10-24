package it.hxl.myblogprod.controller;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController {

    @RequestMapping("/addBlog")
    public String addBlog(String title, String content, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        String path = ResourceUtils.getURL("classpath:").getPath()+"static/blogs/";
        String realPath = path.replace('/', '\\').substring(1,path.length());
        File file = new File(realPath);
        //判断文件夹是否存在,不存在则创建
        if(!file.exists()){
            file.mkdirs();
        }
        String filename = title + new SimpleDateFormat("yyyyMMdd").format(new Date());
        File blog = new File(realPath, filename + ".md");
        FileOutputStream outputStream = new FileOutputStream(blog);
        outputStream.write(content.getBytes());
        return filename;
    }

    @RequestMapping("/uploadNeedImg")
    public String uploadNeedImg(@RequestParam("upload") MultipartFile upload, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        //上传文件放的位置
        String path = ResourceUtils.getURL("classpath:").getPath()+"static/image/blogs/";
        String realPath = path.replace('/', '\\').substring(1,path.length());
        File file = new File(realPath);
        //判断文件夹是否存在,不存在则创建
        if(!file.exists()){
            file.mkdirs();
        }
        if (upload == null){
            return "cc";
        }
        //MultipartFile的getName()返回的是form表单的name属性,相当于FileItem的getFieldName()
        String filename = upload.getOriginalFilename();
        filename = UUID.randomUUID().toString().replace("-", "") + "_" +filename;
        upload.transferTo(new File(realPath, filename));
        String basePath = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+request.getContextPath()+ "/";
        return basePath + "image/blogs/" + filename;
    }

}
