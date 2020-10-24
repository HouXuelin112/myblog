package it.hxl.myblogadmin1.controller;

import it.hxl.myblogadmin1.entity.Admin;
import it.hxl.myblogadmin1.entity.AdminLogin;
import it.hxl.myblogadmin1.service.AdminLoginService;
import it.hxl.myblogadmin1.service.AdminService;
import it.hxl.myblogadmin1.utils.CommonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminLoginService adminLoginService;

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping({"/login", "/", ""})
    public String toLogin(){
        Subject subject = SecurityUtils.getSubject(); //获取当前用户
        if (subject.isAuthenticated()){ //如果当前已有用户登陆则直接跳到index
            return "redirect:/index";
        }
        //否则返回 登陆页
        return "login";
    }


    @RequestMapping("/validatePassword")
    @ResponseBody
    public String validatePassword(String password, HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        //为密码加密生成新密码
        String newPassword = new SimpleHash("MD5", password, admin.getSalt(), 2).toString();
        if (admin == null){
            return "error";
        }
        if (!admin.getPassword().equals(newPassword)){
            return "error";
        }
        return "ok";
    }


    @RequestMapping("/updateAdmin")
    public String updateAdmin(Admin admin, String headBase64, HttpSession session) throws IOException {
        if (headBase64 != null && !headBase64.equals("")){
            admin.setHead(new BASE64Decoder().decodeBuffer(headBase64));
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString(); //生成盐值
        admin.setSalt(salt);
        //为密码加密生成新密码
        String password = new SimpleHash("MD5", admin.getPassword(), salt, 2).toString();
        //设置新密码
        admin.setPassword(password);
        int row = adminService.updateAdmin(admin);
        Admin admin1 = adminService.findAdminById(admin.getId());
        session.setAttribute("admin", admin1);
        session.setMaxInactiveInterval(60 * 60 * 24 * 30);
        return row > 0 ? "redirect:index" : "redirect:error";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(Admin admin, Model model, HttpServletRequest request, HttpSession session){
        //检查form表单用户名密码是否为空
        if (!checkAdmin(admin)){
            model.addAttribute("errorMsg", "用户名密码不能为空");
            return "login";
        }
        //使用shiro认证用户登陆
        // 获取Subject
        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()){
            System.out.println("已经验证过了");
            System.out.println(subject.getPrincipal());
            return "index";
        }


        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getAdminName(), admin.getPassword());
        token.setRememberMe(true);
        try{
            //执行login方法就会去执行UserRealm中的认证逻辑
            subject.login(token);
            //若没有出现异常则证明验证成功
            Session session1 = subject.getSession();
            Admin admin1 = adminService.getAdminByName(admin.getAdminName());
            AdminLogin adminLogin = new AdminLogin();
            adminLogin.setAdminId(admin1.getId());
            adminLogin.setLoginIp(CommonUtils.getRealIp(request));
            adminLogin.setLastAccessTime(new Date());
            adminLogin.setStatus(1);
            adminLoginService.insertAdminLogin(adminLogin);
//            session.setAttribute("admin", admin1);
//            session.setAttribute("adminlogins", adminLoginService.findAdminLoginById(admin1.getId()));
            session1.setAttribute("admin", admin1);
            session1.setAttribute("adminlogins", adminLoginService.findAdminLoginById(admin1.getId()));
//            session.setMaxInactiveInterval(3600 * 24 * 30);
            session1.setTimeout(3600 * 24 * 30);
            return "index";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("UnknownAccount");
            //用户名不存在
            model.addAttribute("errorMsg", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            //密码错误
            model.addAttribute("errorMsg", "密码错误");
            return "login";
        }
        /*Admin admin1 = adminService.validateAdmin(admin);
        if (admin1 == null){
            model.addAttribute("errorMsg", "用户名或密码错误");
            return "login";
        }
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.setAdminId(admin1.getId());
        adminLogin.setLoginIp(CommonUtils.getRealIp(request));
        adminLogin.setLastAccessTime(new Date());
        adminLogin.setStatus(1);
        adminLoginService.insertAdminLogin(adminLogin);
        session.setAttribute("admin", admin1);
        session.setAttribute("adminlogins", adminLoginService.findAdminLoginById(admin1.getId()));
        session.setMaxInactiveInterval(3600 * 24 * 30);
        return "index";*/
    }

    @GetMapping("/adminLogin")
    public String getAdminLogin(){
        return "login";
    }

    /**
     * 验证用户名密码是否为空
     * @param admin Admin
     * @return true 不空， false 空
     */
    private boolean checkAdmin(Admin admin){
        String username = admin.getAdminName();
        String password = admin.getPassword();
        return !("".equals(username) || "".equals(password));
    }

}
