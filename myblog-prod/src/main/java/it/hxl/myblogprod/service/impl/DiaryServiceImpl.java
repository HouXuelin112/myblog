package it.hxl.myblogprod.service.impl;

import it.hxl.myblogprod.entity.Diary;
import it.hxl.myblogprod.mapper.DiaryMapper;
import it.hxl.myblogprod.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Resource
    private DiaryMapper diaryMapper;

    @Override
    public List<Diary> findAllDiary() {
//        System.out.println(diaryMapper.findAllDiary().get(0).getImage().length);
        return diaryMapper.findAllDiary();
    }
}
