package it.hxl.myblogadmin1.controller;

import it.hxl.myblogadmin1.entity.Users;
import it.hxl.myblogadmin1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping("/updateUser")
    public String updateUser(Users users, @RequestParam(required = false, defaultValue = "null", name = "imgFile") MultipartFile imgFile, HttpSession session) throws IOException {
        if (imgFile != null){
            users.setHead(imgFile.getBytes());
        }
        userService.updateUser(users);
        return "redirect:/user/" + session.getAttribute("page");
    }

    @RequestMapping("/delete")
//    @RequiresPermissions("deleteUser")
    @ResponseBody
    public String deleteUser(int id){
        userService.deleteUserById(id);
        return "ok";
    }

    @RequestMapping("/see")
    @ResponseBody
    public Users seeUser(int id){
        return userService.findUserById(id);
    }

    @RequestMapping("/list/{current}")
    public String userPage(@PathVariable("current") int current, HttpServletRequest request){
        System.out.println("refer-->" + request.getHeader("refer"));
        request.getSession().setAttribute("page", current);
        int pageSize = 10;
        if (current == 0){
            current = 1;
        }
        List<Users> users = userService.findUserByPage(pageSize, current);
        request.setAttribute("users", users);
        request.setAttribute("current", current);
        request.setAttribute("pages", size2Arr((int) Math.ceil(userService.getUserCount() / (float)pageSize)));
        return "manage-user";
    }

    private int[] size2Arr(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++){
            arr[i] = i + 1;
        }
        return arr;
    }

}
