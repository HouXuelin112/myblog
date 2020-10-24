package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.AdminPerms;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminPermissionMapper {

    @Select("select * from admin_perms where admin_id=#{adminId}")
    @Results(id = "adminPermsMapper", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "admin_id", property = "adminId"),
            @Result(column = "perms_id", property = "permsId")
    })
    List<AdminPerms> findAdminPermsByAdminId(int adminId);

}
