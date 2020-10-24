package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.Resources;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesMapper {

    @Select("select * from resources")
    List<Resources> findAllResources();

}
