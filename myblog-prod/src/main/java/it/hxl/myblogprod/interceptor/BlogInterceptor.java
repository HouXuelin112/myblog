package it.hxl.myblogprod.interceptor;

import it.hxl.myblogprod.service.BlogService;
import it.hxl.myblogprod.service.TagService;
import it.hxl.myblogprod.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BlogInterceptor implements HandlerInterceptor {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入blog拦截器");
        request.setAttribute("notTopBlogs", blogService.findNotTopBlogs());
        request.setAttribute("topBlogs", blogService.findTopBlogs());
        request.setAttribute("top10Blogs", blogService.findTop10Blogs());
        request.setAttribute("hotTop10Blogs", blogService.findHotTopBlogs(10));
        request.setAttribute("recentVisitors", userService.findRecentVisitors());
        request.setAttribute("tags", tagService.findAllTags());
//        request.setAttribute("topBlogs", blogService.findTopBlogs());
        return true;
    }
}
