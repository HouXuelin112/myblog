package it.hxl.myblogadmin1.controller;

import it.hxl.myblogadmin1.entity.Comments;
import it.hxl.myblogadmin1.entity.Diary;
import it.hxl.myblogadmin1.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/diary")
public class DiaryController {


    @Autowired
    private DiaryService diaryService;


    @RequestMapping("/deleteAllChecked")
    private String deleteAllChecked(int[] checkbox){
        String ids = Arrays.toString(checkbox);
        ids = ids.substring(1, ids.length()-1);
        diaryService.deleteMutiComments(ids);
        return "redirect:/diary/1";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteDiary(int id){
        diaryService.deleteDiaryById(id);
        return "ok";
    }

    @GetMapping({"/{current}"})
    public String comment(@PathVariable(value = "current") int current, HttpServletRequest request){
        int pageSize = 10;
        if (current == 0){
            current = 1;
        }
        List<Diary> diarys = diaryService.findCommentByPage(pageSize, current-1);
        request.setAttribute("diarys", diarys);
        request.setAttribute("current", current);
        request.setAttribute("pages", size2Arr((int) Math.ceil(diaryService.getDiaryCount() / (float)pageSize)));
        return "diary";
    }

    private int[] size2Arr(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size; i++){
            arr[i] = i + 1;
        }
        return arr;
    }
}
