package it.hxl.myblogprod.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CommonInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (!uri.equals("/commentsList") && !uri.equals("/read")){
            request.getSession().setAttribute("history", uri);
        }
        request.setAttribute("base64Encoder", new BASE64Encoder());
        return true;
    }
}
