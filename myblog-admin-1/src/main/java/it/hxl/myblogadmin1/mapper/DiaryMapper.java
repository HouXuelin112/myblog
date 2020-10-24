package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.Diary;
import it.hxl.myblogadmin1.mapper.provider.DiaryProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DiaryMapper {

    @InsertProvider(type = DiaryProvider.class, method = "insertDiary")
    @Transactional
    int insertDiary(Diary diary);

    @Delete("delete from diary where id in (${ids})")
    @Transactional
    int deleteMutiComments(@Param("ids") String ids);

    @Select("select count(*) from diary")
    int getDiaryCount();

    @Select("select top ${pageSize} * from diary where id>=(select max(id) from (select top (${pageSize}*${lastPage}+1) * from diary order by id asc) temp)")
    @Results(id = "diaryMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "diary_content", property = "diaryContent"),
            @Result(column = "issueDate", property = "issueDate"),
            @Result(column = "image", property = "image"),
            @Result(column = "title", property = "title")
    })
    List<Diary> findCommentByPage(@Param("pageSize") int pageSize, @Param("lastPage") int lastPage);

    @Delete("delete from diary where id=#{id}")
    @Transactional
    int deleteDiaryById(int id);

}
