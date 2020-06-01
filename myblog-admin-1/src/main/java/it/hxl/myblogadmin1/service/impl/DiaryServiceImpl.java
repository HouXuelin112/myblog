package it.hxl.myblogadmin1.service.impl;

import it.hxl.myblogadmin1.entity.Diary;
import it.hxl.myblogadmin1.mapper.DiaryMapper;
import it.hxl.myblogadmin1.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    public int getDiaryCount() {
        return diaryMapper.getDiaryCount();
    }

    @Override
    public List<Diary> findCommentByPage(int pageSize, int lastPage) {
        return diaryMapper.findCommentByPage(pageSize, lastPage);
    }

    @Override
    public int deleteDiaryById(int id) {
        return diaryMapper.deleteDiaryById(id);
    }

    @Override
    public int deleteMutiComments(String ids) {
        return diaryMapper.deleteMutiComments(ids);
    }
}
