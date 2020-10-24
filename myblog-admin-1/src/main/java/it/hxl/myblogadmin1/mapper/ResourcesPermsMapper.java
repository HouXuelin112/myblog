package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.ResourcesPerms;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesPermsMapper {

    @Select("select * from perms_resources where res_id=#{resId}")
    @Results(id = "resourcePermsMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "res_id", property = "resId"),
            @Result(column = "perms_id", property = "permsId")
    })
    List<ResourcesPerms> findResourcesPermsByResId(int resId);

}
