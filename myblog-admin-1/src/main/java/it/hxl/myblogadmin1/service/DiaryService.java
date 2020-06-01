package it.hxl.myblogadmin1.service;

import it.hxl.myblogadmin1.entity.Diary;

import java.util.List;

public interface DiaryService {
    int getDiaryCount();
    List<Diary> findCommentByPage(int pageSize, int lastPage);
    int deleteDiaryById(int id);
    int deleteMutiComments(String ids);
}
