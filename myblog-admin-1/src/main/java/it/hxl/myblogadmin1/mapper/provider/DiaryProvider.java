package it.hxl.myblogadmin1.mapper.provider;

import it.hxl.myblogadmin1.entity.Diary;
import org.apache.ibatis.jdbc.SQL;

public class DiaryProvider {

    public String insertDiary(Diary diary){
        return new SQL(){
            {
                INSERT_INTO("diary");
                if (diary.getTitle() != null){
                    VALUES("title", "#{title}");
                }
                if (diary.getDiaryContent() != null){
                    VALUES("diary_content", "#{diaryContent}");
                }
                if (diary.getImage() != null){
                    VALUES("image", "#{image}");
                }
                if (diary.getIssueDate() != null){
                    VALUES("issueDate", "#{issueDate}");
                }
            }
        }.toString();
    }

}
