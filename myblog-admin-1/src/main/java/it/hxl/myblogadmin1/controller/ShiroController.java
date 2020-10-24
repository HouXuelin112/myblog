package it.hxl.myblogadmin1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shiro")
public class ShiroController {

    @RequestMapping("/unAuthorize")
    public String unAuthorize(){
        return "您没有此权限";
    }

}
