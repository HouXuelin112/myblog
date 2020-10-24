package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.entity.Link;
import it.hxl.myblogprod.http.WrapMapper;
import it.hxl.myblogprod.http.Wrapper;
import it.hxl.myblogprod.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LinkController {

    @Autowired
    private LinkService linkService;

    @RequestMapping("/applyFriendLink")
    public String applyFriendLink(Link link) throws IOException {
        try{
            linkService.insertLink(link);
        }catch (Exception e){
            return "提交失败:" + e.getMessage();
        }
        return "申请提交成功";
    }

    @RequestMapping("/flink/apply")
    public Wrapper applyFlink(@RequestBody Link link) {
        try{
            linkService.insertLink(link);
        }catch (Exception e){
            return WrapMapper.error("提交失败:" + e.getMessage());
        }
        return WrapMapper.ok("申请提交成功");
    }

    @RequestMapping("/flink/findAllFlink")
    public Wrapper findAllFlink(){
        return WrapMapper.ok(linkService.findAllPassLinks());
    }

}
