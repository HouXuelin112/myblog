package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.entity.MyEmail;
import it.hxl.myblogprod.entity.Users;
import it.hxl.myblogprod.service.UserService;
import it.hxl.myblogprod.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
public class LoginAndRegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private MyEmail myEmail;

    @RequestMapping("/checkValiStr")
    @ResponseBody
    public String checkValiStr(String valiStr, HttpSession session){
        String iniValiStr = (String) session.getAttribute("valiStr");
        if (iniValiStr == null){
            return "null";
        }
        if (!valiStr.equals(iniValiStr)){
            return "error";
        }
        session.removeAttribute("valiStr");
        return "ok";
    }

    @RequestMapping("/getValiStr")
    @ResponseBody
    public String getValiStr(@RequestParam("email") String email, HttpSession session)  {
        try{
            System.out.println(email);
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++){
                 sb.append(random.nextInt(10));
            }
            CommonUtils.sendValiStr(myEmail, email, sb.toString());
            session.setAttribute("valiStr", sb.toString());
            return "valiStr send successfully";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 验证用户信息
     * 为用户设置登录名
     * @param user
     */
    private Users addUser(Users user, MultipartFile file) throws IOException {
        if (file != null) {
            user.setHead(file.getBytes());
        }else{
            //为user设置默认头像
            InputStream in = UserController.class.getResourceAsStream("/static/head/xiaoxiao.png");
            MemoryCacheImageInputStream imageInputStream = new MemoryCacheImageInputStream(in);
            int length = in.available();
            byte[] bytes = new byte[length];
            imageInputStream.read(bytes);
            user.setHead(bytes);
        }
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        int dotIndex = user.getEmail().indexOf("@");
        user.setUsername(date + user.getEmail().substring(dotIndex - 4, dotIndex));
        user.setNickName("nickname1");
        return user;
    }


    @RequestMapping("/register")
//    @ResponseBody
    public String register(Users user, Model model, MultipartFile file) throws IOException {
        user = addUser(user, file);
        try{
            userService.insertUser(user);
        }catch (Exception e){
            model.addAttribute("registerError", e.getMessage());
            return "login";
        }

        model.addAttribute("success", "注册成功,请登录");
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/login")
    public String toPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(Users user, Model model, HttpSession session){
        model.addAttribute("username", user.getUsername());
        Users newUser = userService.validate(user);
        if (newUser == null){
            model.addAttribute("loginError", "邮箱/用户名或密码错误");
            return "login";
        }
        session.setAttribute("user", newUser);
        String history = (String) session.getAttribute("history");
        return "redirect:" + (history == null ? "/article" : history);
    }

    @GetMapping("/logOut")
    @ResponseBody
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "ok";
    }


}
