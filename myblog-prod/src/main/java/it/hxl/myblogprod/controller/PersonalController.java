package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.entity.Users;
import it.hxl.myblogprod.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 个人信息业务处理
 */
@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private UserService userService;

    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody Users users, HttpSession session){
        Users users1 = (Users) session.getAttribute("user");
        if (users1 == null){
            return "o";
        }
        try{
            userService.updateUser(users);
        }catch (Exception e){
            e.printStackTrace();
            return "o";
        }
        Users users2 = userService.findUserById(users.getId());
        session.setAttribute("user", users2);
        return "ok";
    }

    /**
     * 检查用户输入
     * 不符合返回 error
     * 否则返回 ok
     * @param value
     * @return String
     */
    @RequestMapping("/checkPassword")
    @ResponseBody
    public String checkPassword(String value, HttpSession session){
        Users user = (Users)session.getAttribute("user");
        if (user == null){
            return "noUser";
        }
        String password = user.getPassword();
        return value != null && value.equals(password) ? "ok" : "error";
    }

}
