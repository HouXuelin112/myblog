package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.entity.Users;
import it.hxl.myblogprod.service.UserService;
import it.hxl.myblogprod.utils.CommonUtils;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@Controller
public class LoginAndRegisterController {

    @Autowired
    private UserService userService;

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
        return "ok";
    }

    @RequestMapping("/getValiStr")
    @ResponseBody
    public String getValiStr(String email, HttpSession session) throws EmailException {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++){
             sb.append(random.nextInt(10));
        }
        CommonUtils.sendValiStr(email, sb.toString());
        session.setAttribute("valiStr", sb.toString());
        return "valiStr send successfully";
    }


    @RequestMapping("/register")
//    @ResponseBody
    public String register(Users user, Model model, MultipartFile file) throws IOException {
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

    @RequestMapping("/login")
    public String login(Users user, Model model, HttpSession session){
        Users newUser = userService.validate(user);
        if (newUser == null){
            model.addAttribute("loginError", "用户名或密码错误");
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
