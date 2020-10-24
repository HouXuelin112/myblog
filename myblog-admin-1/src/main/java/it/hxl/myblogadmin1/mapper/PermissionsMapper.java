package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.Permissions;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsMapper {
    @Select("select * from permissions where id=#{id}")
    Permissions findPermissionsById(@Param("id") int id);

}
