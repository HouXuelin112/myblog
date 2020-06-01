package it.hxl.myblogadmin1.controller;

import it.hxl.myblogadmin1.entity.Tag;
import it.hxl.myblogadmin1.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteTag(int id){
        tagService.deleteTag(id);
        return "ok";
    }

    @RequestMapping("/update")
    public String updateTag(Tag tag){
        tagService.updateTag(tag);
        return "redirect:/category";
    }


    @RequestMapping("/add")
    public String addTag(Tag tag){
        System.out.println(tag);
        tagService.insertTag(tag);
        return "redirect:/category";
    }

}
