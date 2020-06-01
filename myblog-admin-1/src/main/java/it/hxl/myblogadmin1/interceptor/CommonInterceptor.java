package it.hxl.myblogadmin1.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Component
public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("dateFormat", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        request.setAttribute("base64Encoder", new BASE64Encoder());
        return true;
    }
}
