package it.hxl.myblogprod.interceptor;

import it.hxl.myblogprod.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LinkInterceptor implements HandlerInterceptor {

    @Autowired
    private LinkService linkService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("links", linkService.findAllPassLinks());
        return true;
    }
}
