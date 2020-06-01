package it.hxl.myblogadmin1.mapper;

import it.hxl.myblogadmin1.entity.AdminLogin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AdminLoginMapper {

    @Insert("insert into adminlogin(admin_id, loginIp, lastAccessTime, status) values(#{adminId}, #{loginIp}, #{lastAccessTime}, #{status})")
    @Transactional
    int insertAdminLogin(AdminLogin adminLogin);

    @Select("select top 10 * from adminlogin where admin_id=#{id} order by lastAccessTime desc")
    @Results(id = "adminLoginMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "admin_id", property = "adminId"),
            @Result(column = "loginIp", property = "loginIp"),
            @Result(column = "lastAccessTime", property = "lastAccessTime"),
            @Result(column = "status", property = "status"),
    })
    List<AdminLogin> findAdminLoginsByAdminId(int id);

    @Select("select count(*) from adminlogin where admin_id=#{id}")
    int getLoginCountByAdminId(int id);

}
