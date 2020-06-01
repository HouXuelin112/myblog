package it.hxl.myblogadmin1.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkMapper {

    @Select("select count(*) from diary")
    int getLinkCount();

}
