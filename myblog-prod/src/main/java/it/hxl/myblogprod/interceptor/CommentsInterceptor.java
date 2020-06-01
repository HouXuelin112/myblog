package it.hxl.myblogprod.interceptor;

import it.hxl.myblogprod.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CommentsInterceptor implements HandlerInterceptor {

    @Autowired
    private CommentsService commentsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("blogid " + request.getParameter("blogId"));
        request.setAttribute("blogId", Integer.parseInt(request.getParameter("blogId")));
        request.setAttribute("comments", commentsService.findAllCommentsByBlogId(Integer.parseInt(request.getParameter("blogId"))));
        return true;
    }
}
