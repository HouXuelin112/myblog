package it.hxl.myblogadmin1.interceptor;

import it.hxl.myblogadmin1.entity.Admin;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if ("/login".equals(uri)){
            return true;
        }
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null){
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }

        return true;
    }
}