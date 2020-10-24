package it.hxl.myblogprod.mapper;

import it.hxl.myblogprod.entity.Diary;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryMapper {

    @Select("select * from diary order by issueDate desc")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "diary_content", property = "diaryContent"),
            @Result(column = "issueDate", property = "issueDate"),
            @Result(column = "image", property = "image"),
            @Result(column = "title", property = "title"),
            @Result(column = "color", property = "color")
    })
    List<Diary> findAllDiary();

}
