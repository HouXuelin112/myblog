package it.hxl.myblogadmin1.controller;

import it.hxl.myblogadmin1.entity.Admin;
import it.hxl.myblogadmin1.entity.AdminLogin;
import it.hxl.myblogadmin1.service.AdminLoginService;
import it.hxl.myblogadmin1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminLoginService adminLoginService;

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }


    @RequestMapping("/validatePassword")
    @ResponseBody
    public String validatePassword(String password, HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null){
            return "error";
        }
        if (!admin.getPassword().equals(password)){
            return "error";
        }
        return "ok";
    }


    @RequestMapping("/updateAdmin")
    public String updateAdmin(Admin admin, String headBase64, HttpSession session) throws IOException {
        if (headBase64 != null && !headBase64.equals("")){
            admin.setHead(new BASE64Decoder().decodeBuffer(headBase64));
        }
        int row = adminService.updateAdmin(admin);
        Admin admin1 = adminService.findAdminById(admin.getId());
        session.setAttribute("admin", admin1);
        return row > 0 ? "redirect:index" : "redirect:error";
    }

    @RequestMapping("/adminLogin")
    public String adminLogin(Admin admin, Model model, HttpServletRequest request){
        Admin admin1 = adminService.validateAdmin(admin);
        if (admin1 == null){
            model.addAttribute("errorMsg", "用户名或密码错误");
            return "login";
        }
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.setAdminId(admin1.getId());
        adminLogin.setLoginIp(request.getRemoteAddr());
        adminLogin.setLastAccessTime(new Date());
        adminLogin.setStatus(1);
        adminLoginService.insertAdminLogin(adminLogin);
        request.getSession().setAttribute("admin", admin1);
        request.getSession().setAttribute("adminlogins", adminLoginService.findAdminLoginById(admin1.getId()));
        return "index";
    }

}
