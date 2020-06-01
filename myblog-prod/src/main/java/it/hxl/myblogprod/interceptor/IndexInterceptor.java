package it.hxl.myblogprod.interceptor;

import it.hxl.myblogprod.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IndexInterceptor implements HandlerInterceptor {

    @Autowired
    private BlogService blogService;

    public IndexInterceptor(){}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入index拦截器");
        request.setAttribute("top3Blogs", blogService.findHotTopBlogs(6));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("exit interceptor");
    }
}
