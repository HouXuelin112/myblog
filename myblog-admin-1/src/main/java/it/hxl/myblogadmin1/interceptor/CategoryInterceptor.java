package it.hxl.myblogadmin1.interceptor;

import it.hxl.myblogadmin1.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CategoryInterceptor implements HandlerInterceptor {

    @Autowired
    private TagService tagService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("tags", tagService.findAllTags());
        return true;
    }
}
