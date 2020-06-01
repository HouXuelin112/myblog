package it.hxl.myblogadmin1.interceptor;

import it.hxl.myblogadmin1.entity.Admin;
import it.hxl.myblogadmin1.entity.AdminLogin;
import it.hxl.myblogadmin1.entity.Blog;
import it.hxl.myblogadmin1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class IndexInterceptor implements HandlerInterceptor {

    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private LinkService linkService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("blogCount", blogService.getBlogCount());
        request.setAttribute("diaryCount", diaryService.getDiaryCount());
        request.setAttribute("messageCount", messageService.getMessageCount());
        request.setAttribute("linkCount", linkService.getLinkCount());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null) {
            request.setAttribute("loginCount", adminLoginService.getLoginCountByAdminId(admin.getId()));
            List<AdminLogin> adminLogins = (List<AdminLogin>) request.getSession().getAttribute("adminlogins");
            request.setAttribute("lastAccessTime", adminLogins.size() > 0 ?adminLogins.get(1).getLastAccessTime():adminLogins.get(0).getLastAccessTime());
            request.setAttribute("loginIp", adminLogins.size() > 0 ?adminLogins.get(1).getLoginIp():adminLogins.get(0).getLoginIp());
        }
    }
}
