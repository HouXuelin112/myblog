package it.hxl.myblogprod.controller;

import it.hxl.myblogprod.http.WrapMapper;
import it.hxl.myblogprod.http.Wrapper;
import it.hxl.myblogprod.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @RequestMapping("/findAllDiary")
    public Wrapper findAllDiary(){
        return WrapMapper.ok(diaryService.findAllDiary());
    }

}
